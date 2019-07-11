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
    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GB2312
                String s = encode;
                return s; //是的话，返回“GB2312“，以下代码同理
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是ISO-8859-1
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是UTF-8
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GBK
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "gg";
    }

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
    public String adminModifyDist(@RequestBody JSONObject data, HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) throws UnsupportedEncodingException {
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies == null) {
            return "{\"status\": \"not login in\"}";
        } else {
            JSONObject store = new JSONObject();
            for (Cookie cc : cookies) {
                if (cc.getName().equals("distName")) {
                    String encode = getEncoding(cc.getValue());
                    System.out.printf(encode);
                    if(encode == "UTF-8"  || encode == "GB2312") {
                        store.accumulate("distName", cc.getValue());
                        break;
                    }
                    else{
                        String name = convertEncodingFormat(cc.getValue(), encode, "UTF-8");
                        store.accumulate("distName", name);
                        break;
                    }
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
