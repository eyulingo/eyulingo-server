package io.github.eyulingo.Controller.Admins;


import io.github.eyulingo.Service.AdminService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class AdminModifyStore {
    @Autowired
    private AdminService adminService;


    @RequestMapping(value = "/admin/modifystore",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String adminModitifyStores(@RequestBody JSONObject data, HttpServletRequest httpRequest){
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
            return this.adminService.modifyStores(data);
        }
        return  "{\"status\": \"wrong\"}";
    }
}
