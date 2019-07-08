package io.github.eyulingo.Controller;


import io.github.eyulingo.Service.UserService;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class GetCheckCodeController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getcode",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    JSONObject userGetCheckCode(@RequestBody JSONObject data){
        if(data.getString("phone_nu").length()!=11){
            JSONObject item = new JSONObject();
            item.accumulate("status","bad-phone");
        }
        return this.userService.getCheckCode(data);
    }

}
