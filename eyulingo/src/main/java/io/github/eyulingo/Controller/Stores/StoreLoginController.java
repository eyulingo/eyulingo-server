package io.github.eyulingo.Controller.Stores;



import io.github.eyulingo.Service.StoreService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class StoreLoginController {
    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "/store/login",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String storeLogin(@RequestBody JSONObject data, HttpServletResponse httpServletResponse){
        String store = this.storeService.distLogin(data);
        if(store.equals("{\"status\": \"ok\"}")){
            Cookie ck0 = new Cookie("distName", data.getString("distName"));
            Cookie ck1 = new Cookie("distPassword", data.getString("password"));
            ck0.setMaxAge(1800);
            ck1.setMaxAge(1800);
            ck0.setPath("/");
            ck1.setPath("/");
            httpServletResponse.addCookie(ck0);
            httpServletResponse.addCookie(ck1);
            return store;
        }
        return store;
    }
}
