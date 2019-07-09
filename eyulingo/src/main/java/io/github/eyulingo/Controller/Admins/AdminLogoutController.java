package io.github.eyulingo.Controller.Admins;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AdminLogoutController {
    @RequestMapping(value = "/admin/logout",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String adminLogout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cc:cookies){
            if (cc.getName().equals("adminName")){
                cc.setMaxAge(0);
                httpServletResponse.addCookie(cc);
            }
            if(cc.getName().equals("adminPassword")){
                cc.setMaxAge(0);
                httpServletResponse.addCookie(cc);
            }
        }
        return "{\"status\": \"logout success\"}";
    }
}
