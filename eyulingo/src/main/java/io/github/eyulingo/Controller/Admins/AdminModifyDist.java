package io.github.eyulingo.Controller.Admins;


import io.github.eyulingo.Service.AdminService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class AdminModifyDist {
    @Autowired
    private AdminService adminService;


    @RequestMapping(value = "/admin/modifydist",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String adminModifyDist(@RequestBody JSONObject data, HttpServletRequest httpRequest){
        Cookie[] cookies = httpRequest.getCookies();
        JSONObject admin =new JSONObject();
        for (Cookie cc:cookies){
            if (cc.getName().equals("adminName")){
                admin.accumulate("adminName",cc.getValue());
            }
            if(cc.getName().equals("adminPassword")){
                admin.accumulate("password",cc.getValue());
            }

        }
        if(admin.size()!=2){
            return  "{\"status\": \"not login as admin\"}";
        }
        if(adminService.adminLogin(admin).equals("{\"status\": \"ok\"}")){
            return this.adminService.modifyDist(data);
        }
        return  "{\"status\": \"wrong\"}";
    }
}
