package io.github.eyulingo.Controller.Stores;

import io.github.eyulingo.Service.StoreService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@RestController
public class StoreGetStoreController {
    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "/store/mystoreinfo",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    JSONObject storeGetStore(HttpServletRequest httpRequest){
        Cookie[] cookies = httpRequest.getCookies();
        JSONObject store =new JSONObject();
        for (Cookie cc:cookies){
            if (cc.getName().equals("distName")){
                store.accumulate("distName",cc.getValue());
                break;
            }

        }
        for (Cookie cc:cookies) {
            if (cc.getName().equals("distPassword")) {
                store.accumulate("password", cc.getValue());
                break;
            }
        }
        if(store.size()!=2){
            JSONObject item = new JSONObject();
            item.accumulate("status","not login");
            return  item;
        }
        if(storeService.distLogin(store).equals("{\"status\": \"ok\"}")){
            return this.storeService.getMyStore(store.getString("distName"));
        }
        JSONObject item = new JSONObject();
        item.accumulate("status","not login");
        return  item;

    }
}
