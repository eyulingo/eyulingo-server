package io.github.eyulingo.Controller;


import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.eyulingo.Service.AdminService;


@RestController
public class AdminLoginController {
    @Autowired
    private AdminService adminService;


    @RequestMapping(value = "/admin/login",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String adminLogin(@RequestBody JSONObject data){
        return this.adminService.adminLogin(data);
    }
}
