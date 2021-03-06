//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.apereo.cas.adaptors.jdbc;

import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.sql.DataSource;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apereo.cas.authentication.HandlerResult;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.authentication.exceptions.AccountDisabledException;
import org.apereo.cas.authentication.exceptions.AccountPasswordMustChangeException;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.apereo.cas.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

/**
 * @author Zengzhx
 * @date 2018/7/9 下午5:10
 */
public class QueryDatabaseAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryDatabaseAuthenticationHandler.class);
    private final String sql;
    private final String fieldPassword;
    private final String fieldExpired;
    private final String fieldDisabled;
    private final String uid;
    private final Map<String, Collection<String>> principalAttributeMap;

    public QueryDatabaseAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order, DataSource dataSource, String sql, String fieldPassword, String fieldExpired, String fieldDisabled, String uid, Map<String, Collection<String>> attributes) {
        super(name, servicesManager, principalFactory, order, dataSource);
        this.sql = sql;
        this.fieldPassword = fieldPassword;
        this.fieldExpired = fieldExpired;
        this.fieldDisabled = fieldDisabled;
        this.principalAttributeMap = attributes;
        this.uid = uid;
    }

    @Override
    protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential, String originalPassword) throws GeneralSecurityException, PreventedException {
        if (!StringUtils.isBlank(this.sql) && this.getJdbcTemplate() != null) {
            Map<String, Object> attributes = new LinkedHashMap(this.principalAttributeMap.size());
            String company = credential.getCompany();
            String username = credential.getUsername();
            String password = credential.getPassword();
            try {
                Map<String, Object> dbFields = this.getJdbcTemplate().queryForMap(this.sql, new Object[]{username, company});
                String dbPassword = (String)dbFields.get(this.fieldPassword);
                if (StringUtils.isNotBlank(originalPassword) && !this.matches(originalPassword, dbPassword) || StringUtils.isBlank(originalPassword) && !StringUtils.equals(password, dbPassword)) {
                    throw new FailedLoginException("Password does not match value on record.");
                }

                Object dbExpired;
                if (StringUtils.isNotBlank(this.fieldDisabled)) {
                    dbExpired = dbFields.get(this.fieldDisabled);
                    if (dbExpired != null && (Boolean.TRUE.equals(BooleanUtils.toBoolean(dbExpired.toString())) || dbExpired.equals(Integer.valueOf(1)))) {
                        throw new AccountDisabledException("Account has been disabled");
                    }
                }

                if (StringUtils.isNotBlank(this.fieldExpired)) {
                    dbExpired = dbFields.get(this.fieldExpired);
                    if (dbExpired != null && (Boolean.TRUE.equals(BooleanUtils.toBoolean(dbExpired.toString())) || dbExpired.equals(Integer.valueOf(1)))) {
                        throw new AccountPasswordMustChangeException("Password has expired");
                    }
                }

                if (StringUtils.isNotBlank(this.uid)) {
                    dbExpired = dbFields.get(this.uid);
                    if (dbExpired != null) {
                        credential.setUid(Integer.valueOf(dbFields.get(this.uid).toString()));
                    }
                }


                this.principalAttributeMap.forEach((key, attributeNames) -> {
                    Object attribute = dbFields.get(key);
                    if (attribute != null) {
                        LOGGER.debug("Found attribute [{}] from the query results", key);
                        attributeNames.forEach((s) -> {
                            LOGGER.debug("Principal attribute [{}] is virtually remapped/renamed to [{}]", key, s);
                            attributes.put(s, CollectionUtils.wrap(attribute.toString()));
                        });
                    } else {
                        LOGGER.warn("Requested attribute [{}] could not be found in the query results", key);
                    }

                });
            } catch (IncorrectResultSizeDataAccessException var9) {
                if (var9.getActualSize() == 0) {
                    throw new AccountNotFoundException(username + " not found with SQL query");
                }

                throw new FailedLoginException("Multiple records found for " + username);
            } catch (DataAccessException var10) {
                throw new PreventedException("SQL exception while executing query for " + username, var10);
            }
            LOGGER.warn("******* username [{}] ---- Principal [{}] *********" , username,attributes.toString());
            return this.createHandlerResult(credential, this.principalFactory.createPrincipal(username, attributes), (List)null);
        } else {
            throw new GeneralSecurityException("Authentication handler is not configured correctly. No SQL statement or JDBC template is found.");
        }
    }
}
