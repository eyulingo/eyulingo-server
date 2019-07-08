package io.github.eyulingo.Controller;

import io.github.eyulingo.Service.StoreService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@RestController
public class StoreChangeDistImageController {
    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "/store/avatar",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String userChangeImage(HttpServletRequest httpRequest,@RequestBody JSONObject data){
        String cookie=httpRequest.getHeader("cookie");
        Cookie[] cookies = httpRequest.getCookies();
        if(cookies != null) {
            for (Cookie cc : cookies) {
                if (cc.getName().equals("distName")) {

                    return this.storeService.ChangeDistImage(cc.getValue(), data);
                }
            }
        }

        return "{\"status\": \"internal_error\"}";
    }
}
