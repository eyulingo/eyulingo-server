package io.github.eyulingo.Controller.Stores;



import io.github.eyulingo.Service.StoreService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String adminModifyDist(@RequestBody JSONObject data, HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) {
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies == null) {
            return "{\"status\": \"not login in\"}";
        } else {
            JSONObject store = new JSONObject();
            for (Cookie cc : cookies) {
                if (cc.getName().equals("distName")) {
                    String name = convertEncodingFormat(cc.getValue(), "iso-8859-1", "UTF-8");
                    store.accumulate("distName", name);
                    break;
                }
            }
            for (Cookie cc : cookies) {
                if (cc.getName().equals("distPassword")) {
                    store.accumulate("password", cc.getValue());
                    break;
                }
            }
            if (store.size() != 2) {
                return "{\"status\": \"not login in\"}";
            }
            if (storeService.distLogin(store).equals("{\"status\": \"ok\"}")) {
                String isright = this.storeService.modifyDist(store.getString("distName"), data);
                if (isright.equals("{\"status\": \"ok\"}")) {
                    Cookie ck0 = new Cookie("distName", data.getString("truename"));
                    Cookie ck1 = new Cookie("distPassword", data.getString("password"));
                    httpServletResponse.addCookie(ck0);
                    httpServletResponse.addCookie(ck1);
                    return isright;
                }
                return isright;
            }
            return "{\"status\": \"not login \"}";
        }
    }
}
