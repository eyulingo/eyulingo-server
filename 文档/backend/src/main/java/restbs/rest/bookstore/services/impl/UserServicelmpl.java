package restbs.rest.bookstore.services.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restbs.rest.bookstore.model.role;
import restbs.rest.bookstore.dao.roleRepository;
import restbs.rest.bookstore.services.UserService;

import java.util.List;

@Service
public class UserServicelmpl implements UserService {


    @Autowired
    protected roleRepository rRepo;


    public String register(JSONObject data){
        List<role> rlist = rRepo.findByEmail(data.getString("email"));
        if (!rlist.isEmpty()){
            return "Email is existed";
        }
        rlist = rRepo.findByNickname(data.getString("nickname"));
        if (!rlist.isEmpty()){
            return "Nickname is existed";
        }
        if (data.getString("nickname").indexOf("@")!=-1){
            return "Nickname invalid char:@";
        }
        role r = new role();
        r.setEmail(data.getString("email"));
        r.setNickname(data.getString("nickname"));
        r.setPassword(data.getString("password"));
        r.setAddress(data.getString("residence"));
        r.setPhone(data.getString("phone"));
        r.setWebsite(data.getString("website"));
        r.setType("user");
        r.setValid(1);
        rRepo.save(r);
        return "register success";
    }

    public String login(JSONObject data){
        List<role> rlist = rRepo.findByEmail(data.getString("userName"));
        if (rlist.isEmpty()){
            rlist = rRepo.findByNickname(data.getString("userName"));
        }
        if (rlist.isEmpty()){
            return "Username not exists";
        }
        role r = rlist.get(0);
        if (r.getValid()==0){
            return "Invalid account";
        }
        System.out.println(r.toString());
        if (r.getPassword().equals(data.getString("password"))){
            return r.getType();
        }else{
            return "Incorrect password";
        }
    }

    public String rememberLogin(String username,String password){
        List<role> rlist = rRepo.findByEmail(username);
        if (rlist.isEmpty()){
            rlist = rRepo.findByNickname(username);
        }
        if (rlist.isEmpty()){
            return "NotLogin";
        }
        role r = rlist.get(0);
        if (r.getPassword().equals(password)){
            return r.getType();
        }
        return "NotLogin";
    }

    public JSONObject getUserInfo(String username){
        List<role> rlist = rRepo.findByEmail(username);
        if (rlist.isEmpty()){
            rlist = rRepo.findByNickname(username);
        }
        if (rlist.isEmpty()){
            return null;
        }
        role r = rlist.get(0);
        JSONObject res = new JSONObject();
        res.put("email",r.getEmail());
        res.put("nickname",r.getNickname());
        res.put("phone",r.getPhone());
        res.put("website",r.getWebsite());
        res.put("usertype",r.getType());
        return res;
    }

    public JSONArray getAllUsers(){
        Iterable<role> rlist = rRepo.findAll();
        JSONArray res = new JSONArray();
        int count = 0;
        for(role r:rlist){
            count +=1;
            JSONObject item = new JSONObject();
            item.accumulate("ID",count);
            item.accumulate("userid",r.getId());
            item.accumulate("nickname",r.getNickname());
            item.accumulate("email",r.getEmail());
            item.accumulate("password",r.getPassword());
            item.accumulate("type",r.getType());
            item.accumulate("website",r.getWebsite());
            item.accumulate("address",r.getAddress());
            item.accumulate("phone",r.getPhone());
            item.accumulate("valid",r.getValid());
            res.add(item);

        }
        return res;
    }

    public String modifyUser(JSONObject data){
        List<role> rlist = rRepo.findByEmail(data.getString("email"));
        if (rlist.isEmpty()){
            return "Error";
        }
        role r = rlist.get(0);
        r.setPhone(data.getString("phone"));
        r.setWebsite(data.getString("website"));
        rRepo.save(r);
        return "Success";
    }

    public String modifyUserPwd(JSONObject data){
        List<role> rlist = rRepo.findByEmail(data.getString("email"));
        if (rlist.isEmpty()){
            return "Error:identify username";
        }
        role r = rlist.get(0);
        if (!data.getString("oldpwd").equals(r.getPassword())){
            return "Error:incorrect password";
        }
        if (!data.getString("newpwd1").equals(data.getString("newpwd2"))){
            return "Error:two different passwords";
        }
        r.setPassword(data.getString("newpwd1"));
        rRepo.save(r);
        return "Success";
    }
}
