package io.github.eyulingo.Controller;



import io.github.eyulingo.Service.StoreService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;


@RestController
public class StoreDistModifyController {
    @Autowired
    private StoreService storeService;

    public static String convertEncodingFormat(String str, String formatFrom, String FormatTo) {
        String result = null;
        if (!(str == null || str.length() == 0)) {
            try {
                result = new String(str.getBytes(formatFrom), FormatTo);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @RequestMapping(value = "/store/modifydist",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String adminModifyDist(@RequestBody JSONObject data, HttpServletRequest httpRequest){

        Cookie[] cookies = httpRequest.getCookies();
        if(cookies != null) {
            for (Cookie cc : cookies) {
                if (cc.getName().equals("distName")) {
                    String name = convertEncodingFormat(cc.getValue(), "iso-8859-1", "UTF-8");
                    return this.storeService.modifyDist(name, data);
                }
            }
        }

        return "{\"status\": \"internal_error\"}";
    }
}
