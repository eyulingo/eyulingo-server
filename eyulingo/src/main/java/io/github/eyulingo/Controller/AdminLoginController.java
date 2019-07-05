package io.github.eyulingo.Controller;


import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.eyulingo.Service.AdminService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
public class AdminLoginController {

    @Autowired
    private AdminService adminService;


    @RequestMapping(value = "/admin/login",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String adminLogin(@RequestBody JSONObject data, HttpServletResponse httpServletResponse){
        Cookie ck0 = new Cookie("adminName",data.getString("adminName"));
        Cookie ck1 = new Cookie("adminPassword",data.getString("password"));
        httpServletResponse.addCookie(ck0);
        httpServletResponse.addCookie(ck1);
        return this.adminService.adminLogin(data);
    }
}
