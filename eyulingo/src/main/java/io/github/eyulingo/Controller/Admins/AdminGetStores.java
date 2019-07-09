package io.github.eyulingo.Controller.Admins;


import io.github.eyulingo.Service.AdminService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class AdminGetStores {
    @Autowired
    private AdminService adminService;


    @RequestMapping(value = "/admin/getstore",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    JSONArray adminGetAllStore(HttpServletRequest httpRequest){
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
        String test = adminService.adminLogin(admin);
        if(test.equals("{\"status\": \"ok\"}")){
            return this.adminService.getAllStores();
        }
        return  null;
    }
}
