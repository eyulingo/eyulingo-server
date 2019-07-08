package io.github.eyulingo.Controller;


import io.github.eyulingo.Service.StoreService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class StoreGetDistController {
    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "/store/profile",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    JSONObject storeGetDist(HttpServletRequest httpRequest){
        Cookie[] cookies = httpRequest.getCookies();
        for (Cookie cc:cookies){
            if (cc.getName().equals("distName")){

                return this.storeService.getDist(cc.getValue());
            }
        }
        return null;

    }
}
