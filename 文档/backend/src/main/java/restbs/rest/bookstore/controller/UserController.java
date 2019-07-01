package restbs.rest.bookstore.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import restbs.rest.bookstore.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
public class UserController {

    protected static Logger logger=LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService db;




    @RequestMapping(value = "/getallusers",method = RequestMethod.GET)
    public
    @ResponseBody
    JSONArray getallusers(){
        return this.db.getAllUsers();
    }
    @RequestMapping(value = "/services/register",method = RequestMethod.POST)
    public
    @ResponseBody
    String register(@RequestBody JSONObject data) {
        return this.db.register(data);
    }

    @RequestMapping(value = "/services/login",method = RequestMethod.POST)
    public
    @ResponseBody
    String login(@RequestBody JSONObject data, HttpServletResponse httpServletResponse) {
        Cookie ck0 = new Cookie("username",data.getString("userName"));
        Cookie ck1 = new Cookie("password",data.getString("password"));
        httpServletResponse.addCookie(ck0);
        httpServletResponse.addCookie(ck1);
        return this.db.login(data);
    }

    @RequestMapping(value = "/services/remember",method = RequestMethod.GET)
    public String remember(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse){
        Cookie[] cookies = httpRequest.getCookies();
        String name="";
        String password="";
        for (Cookie cc:cookies){
            System.out.println(cc.getName());
            System.out.println(cc.getValue());
            if (cc.getName().equals("username")){
                name = cc.getValue();
            }
            if (cc.getName().equals("password")){
                password = cc.getValue();
            }
        }
        return this.db.rememberLogin(name,password);
    }





    @RequestMapping(value = "/services/getuserinfo",method = RequestMethod.GET)
    public
    @ResponseBody
    JSONObject getuserinfo(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) {
        Cookie[] cookies = httpRequest.getCookies();
        for (Cookie cc:cookies){
            if (cc.getName().equals("username")){
                System.out.println("getusername");
                return this.db.getUserInfo(cc.getValue());
            }
        }
        return null;
    }

    @RequestMapping(value = "/cookies",method = RequestMethod.GET)
    public String cookies(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse){
        Cookie[] cookies = httpRequest.getCookies();
        for (Cookie cc:cookies){
            System.out.println(cc.getName());
            System.out.println(cc.getValue());
        }
        Cookie ck = new Cookie("test","hello");
        ck.setMaxAge(10);
        httpServletResponse.addCookie(ck);
        return "OK";
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public
    @ResponseBody
    JSONArray getLadder(@RequestParam String w1,@RequestParam String w2) {
        logger.debug("访问!");
        JSONObject obj = new JSONObject();
        obj.accumulate("key1",111);
        obj.accumulate("key2",222);
        JSONArray res = new JSONArray();
        res.add(obj);
        res.add(obj);

        return res;
    }
}