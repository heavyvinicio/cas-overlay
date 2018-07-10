package org.apereo.cas.authentication;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apereo.cas.authentication.principal.Principal;
import org.apereo.cas.util.CollectionUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains information about a successful authentication produced by an {@link AuthenticationHandler}.
 * Handler results are naturally immutable since they contain sensitive information that should not be modified outside
 * the {@link AuthenticationHandler} that produced it.
 *
 * @author Marvin S. Addison
 * @since 4.0.0
 */
public class DefaultHandlerResult implements HandlerResult {

    /**
     * Serialization support.
     */
    private static final long serialVersionUID = -3113998493287982485L;

    /**
     * The name of the authentication handler that successfully authenticated a credential.
     */
    private String handlerName;


    private Credential handlerCredential;

    /**
     * Credential meta data.
     */
    private CredentialMetaData credentialMetaData;

    /**
     * Resolved principal for authenticated credential.
     */
    private Principal principal;

    /**
     * List of warnings issued by the authentication source while authenticating the credential.
     */
    private List<MessageDescriptor> warnings;

    /**
     * No-arg constructor for serialization support.
     */
    private DefaultHandlerResult() {
    }

    /**
     * Instantiates a new handler result.
     *
     * @param source   the source
     * @param metaData the meta data
     */
    public DefaultHandlerResult(final AuthenticationHandler source, final Credential credential, final CredentialMetaData metaData) {
        this(source, credential, metaData, null, null);
    }

    /**
     * Instantiates a new handler result.
     *
     * @param source   the source
     * @param metaData the meta data
     * @param p        the p
     */
    public DefaultHandlerResult(final AuthenticationHandler source, final Credential credential,final CredentialMetaData metaData, final Principal p) {
        this(source, credential, metaData, p, null);
    }

    /**
     * Instantiates a new handler result.
     *
     * @param source   the source
     * @param metaData the meta data
     * @param warnings the warnings
     */
    public DefaultHandlerResult(final AuthenticationHandler source,
                                final Credential credential,
                                final CredentialMetaData metaData,
                                final List<MessageDescriptor> warnings) {
        this(source, credential, metaData, null, warnings);
    }

    /**
     * Instantiates a new handler result.
     *
     * @param source   the source
     * @param metaData the meta data
     * @param p        the p
     * @param warnings the warnings
     */
    public DefaultHandlerResult(
            final AuthenticationHandler source,
            final Credential credential,
            final CredentialMetaData metaData,
            final Principal p,
            final List<MessageDescriptor> warnings) {
        this(StringUtils.isBlank(source.getName()) ? source.getClass().getSimpleName() : source.getName(),
                credential,metaData, p, warnings);
    }

    /**
     * Instantiates a new Default handler result.
     *
     * @param handlerName the handler name
     * @param metaData    the meta data
     * @param p           the p
     * @param warnings    the warnings
     */
    public DefaultHandlerResult(
            final String handlerName,
            final Credential credential,
            final CredentialMetaData metaData,
            final Principal p, final List<MessageDescriptor> warnings) {
        Assert.notNull(metaData, "Credential metadata cannot be null.");
        this.handlerName = handlerName;
        this.handlerCredential = credential;
        this.credentialMetaData = metaData;
        this.principal = p;
        this.warnings = warnings;
    }

    @Override
    public String getHandlerName() {
        return this.handlerName;
    }

    @Override
    public Credential getHandlerCredential() {
        return this.handlerCredential;
    }

    @Override
    public CredentialMetaData getCredentialMetaData() {
        return this.credentialMetaData;
    }

    @Override
    public Principal getPrincipal() {
        return this.principal;
    }

    @Override
    public List<MessageDescriptor> getWarnings() {
        return this.warnings == null
                ? new ArrayList<>(0)
                : new ArrayList<>(this.warnings);
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder(109, 31);
        builder.append(this.handlerName);
        builder.append(this.credentialMetaData);
        builder.append(this.principal);
        builder.append(this.warnings);
        return builder.toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof DefaultHandlerResult)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        final DefaultHandlerResult other = (DefaultHandlerResult) obj;
        final EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.handlerName, other.handlerName);
        builder.append(this.credentialMetaData, other.credentialMetaData);
        builder.append(this.principal, other.principal);
        builder.append(CollectionUtils.wrap(this.warnings), CollectionUtils.wrap(other.warnings));
        return builder.isEquals();
    }

    @Override
    public String toString() {
        return this.handlerName + ':' + this.credentialMetaData;
    }
}
