package io.github.eyulingo.ServiceImpl;

import io.github.eyulingo.Dao.CheckCodeRepository;
import io.github.eyulingo.Dao.UserRepository;
import io.github.eyulingo.Entity.CheckCodes;
import io.github.eyulingo.Entity.Users;
import io.github.eyulingo.Service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.sql.Timestamp;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CheckCodeRepository checkCodeRepository;

    public JSONObject getCheckCode(JSONObject data){
        String email = data.getString("email");
        JSONObject item = new JSONObject();
        Users usertest = userRepository.findByUserEmail(email);
        if(usertest != null){
            item.accumulate("status","exist email");
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
            checkCodes.setUserEmail(email);
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
        String email = data.getString("email");
        String username = data.getString("username");
        String password = data.getString("password");
        String confirm_password = data.getString("confirm_password");
        String confirm_code = data.getString("confirm_code");
        Users usertest1 = userRepository.findByUserEmail(email);
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
            List<CheckCodes> LCode = checkCodeRepository.findByUserEmail(email);
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
                    newuser.setUserEmail(email);
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
            item.accumulate("email", currentUser.getUserEmail());
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

    public String changePassword(JSONObject data){

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Users currentUser = userRepository.findByUserName(userDetails.getUsername());
            if(data.getString("origin_password").equals(currentUser.getPassword())){
                if(data.getString("new_password").equals(data.getString("confirm_new_password"))){
                    if(!data.getString("new_password").equals(data.getString("origin_password"))){
                        return "{\"status\": \"origin_name is same with new_password\"}";
                    }
                    else {
                        currentUser.setPassword(data.getString("new_password"));
                        userRepository.save(currentUser);
                        return "{\"status\": \"ok\"}";
                    }
                }
                else{
                    return "{\"status\": \"bad_confirm\"}";
                }
            }
            else{
                return "{\"status\": \"wrong_origin_password\"}";
            }
    }

    public String changeEmail(JSONObject data){
        String new_email = data.getString("new_email");
        String check_code = data.getString("confirm_code");
        Date now = new Date();
        Date date = new Date(now.getTime()-180000);
        Timestamp registerdate = new Timestamp(date.getTime());
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = userRepository.findByUserName(userDetails.getUsername());
        List<CheckCodes> LCode = checkCodeRepository.findByUserEmail(currentUser.getUserEmail());
        if (LCode.size() != 0) {
            CheckCodes newestCode = LCode.get(0);
            for (CheckCodes code : LCode) {
                if (code.getTime().compareTo(newestCode.getTime()) <= 0) {
                    newestCode = code;
                }

            }
            if (newestCode.getTime().compareTo(registerdate) >= 0 && newestCode.getCheckCode().equals(check_code)) {
                currentUser.setUserEmail(new_email);
                userRepository.save(currentUser);
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
