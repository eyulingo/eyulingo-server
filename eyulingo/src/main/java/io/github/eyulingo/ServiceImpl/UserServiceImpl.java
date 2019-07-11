package io.github.eyulingo.ServiceImpl;

import io.github.eyulingo.Dao.CheckCodeRepository;
import io.github.eyulingo.Dao.UserAddressRepository;
import io.github.eyulingo.Dao.UserRepository;
import io.github.eyulingo.Entity.*;
import io.github.eyulingo.Service.UserService;
import io.github.eyulingo.Utilities.CodeSender;
import net.sf.json.JSONArray;
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

    @Autowired
    UserAddressRepository userAddressRepository;

    public JSONObject getCheckCode(JSONObject data){
        Date now = new Date();
        Date beforeDate = new Date(now.getTime()-180000);
        Timestamp getDate = new Timestamp(beforeDate.getTime());
        String email = data.getString("email");
        JSONObject item = new JSONObject();
        Users usertest = userRepository.findByUserEmail(email);
        List<CheckCodes> emalitset = checkCodeRepository.findByUserEmail(email);
        CheckCodes newestCode = new CheckCodes();
        if(emalitset.size() !=0 ) {
            newestCode = emalitset.get(0);
            for (CheckCodes code : emalitset) {
                if (code.getTime().compareTo(newestCode.getTime()) <= 0) {
                    newestCode = code;
                }
            }
        }
        Timestamp lastGetTime = newestCode.getTime();
        if(!email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"))
        {
            item.accumulate("status","bad_email");
            return item;
        }
        else if(usertest != null){
            item.accumulate("status","exist email");
            return item;

        }
        else if(emalitset.size() != 0 && lastGetTime.compareTo(getDate) >= 0){
            item.accumulate("status","Two applications should be three minutes apart ");
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
            CodeSender vCS = new CodeSender();

            try {
                vCS.sendCode(email, code);
//                item.accumulate("code", code);
                item.accumulate("status","ok");
                return item;
            } catch (Exception ex) {
                ex.printStackTrace();
                item.accumulate("status", "failed_to_send");
                return item;
            }
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
        if(username.isEmpty() || password.isEmpty() || confirm_code.isEmpty() || confirm_password.isEmpty() || email.isEmpty()){
            return  "{\"status\": \"Your message can't be empty\"}";
        }
        else if(!password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{8,20}$")){
            return  "{\"status\": \"bad_form_password\"}";
        }
        else if(!password.equals(confirm_password)) {
            return  "{\"status\": \"bad_confirm_password\"}";
        }
        else if(usertest1 != null){
            return  "{\"status\": \"email_exist\"}";
        }
        else if(usertest2 != null){
            return  "{\"status\": \"username_exist\"}";
        }
        else if(!email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"))
        {

            return "{\"status\": \"bad_email\"}";
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
                    newuser.setImageId("5d25569d6344590006015f02");
                    userRepository.save(newuser);
                    return "{\"status\": \"ok\"}";
                } else {
                    return "{\"status\": \"bad_confirm_code\"}";
                }
            }
            else {
                return "{\"status\": \"not_get_confirm_code\"}";
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
        if(data.getString("new_password").isEmpty() || data.getString("origin_password").isEmpty() || data.getString("confirm_new_password").isEmpty()){
            return "{\"status\": \"password can't be empty\"}";
        }
        if(!data.getString("new_password").matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{8,20}$")){
            return  "{\"status\": \"bad_form_password\"}";
        }
        if(data.getString("origin_password").equals(currentUser.getPassword())){
            if(data.getString("new_password").equals(data.getString("confirm_new_password"))){
                if(data.getString("new_password").equals(data.getString("origin_password"))){
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
        if(!new_email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"))
        {
            return "{\"status\": \"bad_email\"}";
        }
        String check_code = data.getString("confirm_code");
        Date now = new Date();
        Date date = new Date(now.getTime()-180000);
        Timestamp registerdate = new Timestamp(date.getTime());
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = userRepository.findByUserName(userDetails.getUsername());
        List<CheckCodes> LCode = checkCodeRepository.findByUserEmail(new_email);
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

    public JSONObject findPasswordGetCode(JSONObject data){
        String username = data.getString("username");
        Users missUser = userRepository.findByUserName(username);
        if (missUser == null){
            JSONObject item = new JSONObject();
            item.accumulate("status","no_such_user");
            return  item;
        }
        else{
            String email = missUser.getUserEmail();
            Date now = new Date();
            Date beforeDate = new Date(now.getTime()-180000);
            Timestamp getDate = new Timestamp(beforeDate.getTime());
            JSONObject item = new JSONObject();
            List<CheckCodes> emalitset = checkCodeRepository.findByUserEmail(email);
            CheckCodes newestCode = new CheckCodes();
            if(emalitset.size() !=0 ) {
                newestCode = emalitset.get(0);
                for (CheckCodes code : emalitset) {
                    if (code.getTime().compareTo(newestCode.getTime()) <= 0) {
                        newestCode = code;
                    }
                }
            }
            Timestamp lastGetTime = newestCode.getTime();
            if(!email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"))
            {
                item.accumulate("status","bad_email");
                return item;
            }
            else if(lastGetTime.compareTo(getDate) >= 0){
                item.accumulate("status","Two applications should be three minutes apart ");
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
                newestCode.setCheckCode(code);
                newestCode.setTime(nowdate);
                checkCodeRepository.save(newestCode);
                System.out.printf(code);
                CodeSender vCS = new CodeSender();

                try {
                    vCS.sendCode(email, code);
//                item.accumulate("code", code);
                    item.accumulate("status","ok");
                    return item;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    item.accumulate("status", "failed_to_send");
                    return item;
                }
            }
        }
    }

    public String findPassword(JSONObject data){
        String username = data.getString("username");
        String password = data.getString("new_password");
        String confirm = data.getString("confirm_password");
        String confirm_code = data.getString("check_code");
        Users missUser = userRepository.findByUserName(username);
        Date now = new Date();
        Date date = new Date(now.getTime()-180000);
        Timestamp finddate = new Timestamp(date.getTime());
        if(!password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{8,20}$")){
            return  "{\"status\": \"bad_form_password\"}";
        }
        else if(!password.equals(confirm)){
            return "{\"status\": \"bad_confirm\"}";
        }
        else if (missUser == null){
            return "{\"status\": \"no_such_user\"}";
        }
        else {
            String email = missUser.getUserEmail();
            List<CheckCodes> LCode = checkCodeRepository.findByUserEmail(email);
            if (LCode.size() != 0) {
                CheckCodes newestCode = LCode.get(0);
                for (CheckCodes code : LCode) {
                    if (code.getTime().compareTo(newestCode.getTime()) <= 0) {
                        newestCode = code;
                    }

                }
                if (newestCode.getTime().compareTo(finddate) >= 0 && newestCode.getCheckCode().equals(confirm_code)) {
                    missUser.setPassword(password);
                    missUser.setUserName(username);
                    userRepository.save(missUser);
                    return "{\"status\": \"ok\"}";
                } else {
                    return "{\"status\": \"bad_confirm_code\"}";
                }
            }
            else {
                return "{\"status\": \"not_get_confirm_code\"}";
            }
        }

    }

    public JSONObject getMyAddress(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = userRepository.findByUserName(userDetails.getUsername());
        JSONObject item = new JSONObject();
        List<UserAddress> addressList = userAddressRepository.findByUserId(currentUser.getUserId()) ;
        JSONArray addresses =new JSONArray();
        for(UserAddress address:addressList){
            JSONObject addressitem = new JSONObject();
            addressitem.accumulate("receive_name",address.getReceiverName());
            addressitem.accumulate("receive_phone",address.getRecevierPhone());
            addressitem.accumulate("receive_address", address.getReceiverAddress());
            addresses.add(addressitem);
        }
        item.accumulate("values",addresses);
        item.accumulate("status","ok");
        return item;
    }


    public String addAddress(JSONObject data) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = userRepository.findByUserName(userDetails.getUsername());
        String name = data.getString("receive_name");
        String phone = data.getString("receive_phone");
        String new_address = data.getString("receive_address");
        if(name.isEmpty() || phone.isEmpty() || new_address.isEmpty()){
            return "{\"status\": \"address can't have empty part\"}";
        }
        List<UserAddress> addressList = userAddressRepository.findByUserId(currentUser.getUserId()) ;
        for(UserAddress old_address:addressList){
            if(old_address.getReceiverAddress().equals(new_address) && old_address.getReceiverName().equals(name) && old_address.getRecevierPhone().equals(phone)){
                return "{\"status\": \"added address\"}";
            }
        }
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(currentUser.getUserId());
        userAddress.setRecevierPhone(phone);
        userAddress.setReceiverAddress(new_address);
        userAddress.setReceiverName(name);
        userAddressRepository.save(userAddress);
        return "{\"status\": \"ok\"}";
    }
}
