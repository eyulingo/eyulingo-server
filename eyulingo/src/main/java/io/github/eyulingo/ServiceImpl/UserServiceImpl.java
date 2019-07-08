package io.github.eyulingo.ServiceImpl;

import io.github.eyulingo.Dao.CheckCodeRepository;
import io.github.eyulingo.Dao.UserRepository;
import io.github.eyulingo.Entity.CheckCodes;
import io.github.eyulingo.Entity.Users;
import io.github.eyulingo.Service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.sql.Timestamp;
import io.github.eyulingo.Entity.Users;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CheckCodeRepository checkCodeRepository;

    public JSONObject getCheckCode(JSONObject data){
        String phone = data.getString("phone_nu");
        JSONObject item = new JSONObject();
        Users usertest = userRepository.findByUserPhone(phone);
        if(usertest != null){
            item.accumulate("status","internal_error");
            return item;

        }
        else {
            Date date = new Date();
            Timestamp nowdate = new Timestamp(date.getTime());
            String chars = "0123456789";
            String code = new String();
            for (int i = 0; i < 6; i++) {
                code = chars.charAt((int) (Math.random() * 10)) + code;
            }
            CheckCodes checkCodes = new CheckCodes();
            checkCodes.setPhoneNum(phone);
            checkCodes.setCheckCode(code);
            checkCodes.setTime(nowdate);
            checkCodeRepository.save(checkCodes);
            System.out.printf(code);
            item.accumulate("code",code);
            item.accumulate("status","ok");
            return  item;
        }
    }


    public String register(JSONObject data) {
        String phone_nu = data.getString("phone_nu");
        String username = data.getString("username");
        String password = data.getString("password");
        String confirm_password = data.getString("confirm_password");
        String confirm_code = data.getString("confirm_code");
        Users usertest1 = userRepository.findByUserPhone(phone_nu);
        Users usertest2 = userRepository.findByUserName(username);
        Date now = new Date();
        Date date = new Date(now.getTime()-180000);
        Timestamp registerdate = new Timestamp(date.getTime());
        if(!password.equals(confirm_password)) {
            return  "{\"status\": \"bad_confirm\"}";
        }
        else if(usertest1 != null){
            return  "{\"status\": \"bad_phone_nu\"}";
        }
        else if(usertest2 != null){
            return  "{\"status\": \"bad_username\"}";
        }
        else {
            List<CheckCodes> LCode = checkCodeRepository.findByPhoneNum(phone_nu);
            if (LCode.size() != 0) {
                CheckCodes newestCode = LCode.get(0);
                for (CheckCodes code : LCode) {
                    if (code.getTime().compareTo(newestCode.getTime()) <= 0) {
                        newestCode = code;
                    }

                }
                if (newestCode.getTime().compareTo(registerdate) >= 0 && newestCode.getCheckCode().equals(confirm_code)) {
                    Users newuser = new Users();
                    newuser.setPassword(password);
                    newuser.setUserName(username);
                    newuser.setUserPhone(phone_nu);
                    newuser.setImageId("0");
                    userRepository.save(newuser);
                    return "{\"status\": \"ok\"}";
                } else {
                    return "{\"status\": \"bad_confirm_code\"}";
                }
            }
            else {
                return "{\"status\": \"bad_confirm_code\"}";
            }
        }
    }


    public JSONObject getMe() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Users currentUser = userRepository.findByUserName(userDetails.getUsername());
            JSONObject item = new JSONObject();
            item.accumulate("userid", currentUser.getUserId());
            item.accumulate("username", currentUser.getUsername());
            item.accumulate("user_phone", currentUser.getUserPhone());
            item.accumulate("avatar", currentUser.getImageId());
            item.accumulate("status", "ok");
            return item;
        }
        catch (Exception ex){
            JSONObject item = new JSONObject();
            item.accumulate("status", "internal_error");
            return item;
        }
    }

    public String changeImage(JSONObject data){
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Users currentUser = userRepository.findByUserName(userDetails.getUsername());
            currentUser.setImageId(data.getString("avatar_id"));
            userRepository.save(currentUser);
            return "{\"status\": \"ok\"}";
        }
        catch (Exception ex){
            return "{\"status\": \"internal_error\"}";
        }
    }


}
