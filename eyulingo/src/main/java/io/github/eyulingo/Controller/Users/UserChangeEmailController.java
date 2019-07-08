package io.github.eyulingo.Controller.Users;

import io.github.eyulingo.Service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserChangePhoneController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/changephoneno",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String userChangePhone(@RequestBody JSONObject data){
        if(data.getString("new_phone_nu").length() != 11){
            return "{\"status\": \"bad_phone\"}";
        }
        else {
            return this.userService.changePhone(data);
        }
    }
}