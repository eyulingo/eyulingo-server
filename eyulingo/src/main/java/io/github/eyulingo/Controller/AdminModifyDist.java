package io.github.eyulingo.Controller;


import io.github.eyulingo.Service.AdminService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminModifyDist {
    @Autowired
    private AdminService adminService;


    @RequestMapping(value = "/admin/modifydist",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String adminModifyDist(@RequestBody JSONObject data){
        return this.adminService.modifyDist(data);
    }
}
