package io.github.eyulingo.Controller.Stores;

import io.github.eyulingo.Service.StoreService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class StoreGetStoreController {
    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "/store/mystoreinfo",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    JSONObject storeGetStore() {
        return this.storeService.getMyStore();
    }
}
