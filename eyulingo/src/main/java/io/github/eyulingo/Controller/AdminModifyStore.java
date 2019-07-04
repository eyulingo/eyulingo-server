package io.github.eyulingo.Controller;


import io.github.eyulingo.Service.AdminService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminModifityStore {
    @Autowired
    private AdminService adminService;


    @RequestMapping(value = "/admin/moditifystore",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String adminModitifyStores(@RequestBody JSONObject data){
        return this.adminService.moditifyStores(data);
    }

}
