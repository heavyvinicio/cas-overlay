package com.uusafe.cas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Zengzhx
 * @date 2018/7/5 下午3:50
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {


    @RequestMapping(value = "/add")
    public String addUser()
    {
        return "";
    }

    @RequestMapping(value = "/edit")
    public String editUser()
    {
        return "table-data-table";
    }

    @RequestMapping(value = "/delete")
    public String deleteUser()
    {
        return "";
    }

}
