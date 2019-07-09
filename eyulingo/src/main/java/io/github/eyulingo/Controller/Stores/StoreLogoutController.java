package io.github.eyulingo.Controller.Stores;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class StoreLogoutController {
    @RequestMapping(value = "/store/logout",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String storeLogout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cc:cookies){
            if (cc.getName().equals("distName")){
                cc.setMaxAge(0);
                httpServletResponse.addCookie(cc);
            }
            if(cc.getName().equals("distPassword")){
                cc.setMaxAge(0);
                httpServletResponse.addCookie(cc);
            }
        }
        return "{\"status\": \"logout success\"}";
    }
}
