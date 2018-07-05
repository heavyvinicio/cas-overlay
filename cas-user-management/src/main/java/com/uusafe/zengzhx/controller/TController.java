package com.uusafe.zengzhx.controller;

import com.uusafe.zengzhx.ChangeFunction.DBConnector;
import com.uusafe.zengzhx.bean.Animal;
import com.uusafe.zengzhx.bean.Persion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zengzhx
 * @date 2018/6/17 下午12:46
 */
@RestController
public class TController {

    @Autowired
    private Persion persion;

    @Autowired
    private Animal animal;

    @Autowired
    private DBConnector connector;

    @RequestMapping(value = "/hello")
    public String getValue()
    {
        System.out.println(String.format("persion : {%s}:{%s}", persion.getName(),persion.getAge()));
        System.out.println(String.format("animal : {%s}:{%s}", animal.getName(),animal.getAge()));

        connector.getDB();
        return "hell11122o";
    }
}
