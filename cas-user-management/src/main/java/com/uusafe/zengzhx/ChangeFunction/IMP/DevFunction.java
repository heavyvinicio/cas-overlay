package com.uusafe.zengzhx.ChangeFunction.IMP;

import com.uusafe.zengzhx.ChangeFunction.DBConnector;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Zengzhx
 * @date 2018/6/17 下午2:01
 */
@Component
@Profile("dev")
public class DevFunction implements DBConnector {

    @Override
    public void getDB() {
        System.out.println("dev~!!!!");
    }
}
