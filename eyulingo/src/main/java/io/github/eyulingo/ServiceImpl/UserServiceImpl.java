package io.github.eyulingo.ServiceImpl;

import io.github.eyulingo.Dao.*;
import io.github.eyulingo.Entity.*;
import io.github.eyulingo.Service.UserService;
import io.github.eyulingo.Utilities.CodeSender;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    TagsRepository tagsRepository;

    @Autowired
    GoodCommentsRepository goodCommentsRepository;

    @Autowired
    StoreCommentsRepository storeCommentsRepository;

    @Autowired
    CartRepository cartRepository;

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
            item.accumulate("status","Two applications should be three minutes apart");
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

    public String removeAddress(JSONObject data){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = userRepository.findByUserName(userDetails.getUsername());
        String name = data.getString("receive_name");
        String phone = data.getString("receive_phone");
        String new_address = data.getString("receive_address");
        List<UserAddress> addressList = userAddressRepository.findByUserId(currentUser.getUserId()) ;
        for(UserAddress old_address:addressList){
            if(old_address.getReceiverAddress().equals(new_address) && old_address.getReceiverName().equals(name) && old_address.getRecevierPhone().equals(phone)){
                userAddressRepository.delete(old_address);
                return "{\"status\": \"ok\"}";
            }
        }
        return "{\"status\": \"no such address\"}";
    }

    public String changeAddress(JSONObject data){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = userRepository.findByUserName(userDetails.getUsername());
        String name = data.getString("new_receive_name");
        String phone = data.getString("new_receive_phone");
        String new_address = data.getString("new_receive_address");
        String remove_name = data.getString("old_receive_name");
        String remove_phone = data.getString("old_receive_phone");
        String remove_address = data.getString("old_receive_address");
        List<UserAddress> addressList = userAddressRepository.findByUserId(currentUser.getUserId()) ;
        for(UserAddress old_address:addressList){
            if(old_address.getReceiverAddress().equals(new_address) && old_address.getReceiverName().equals(name) && old_address.getRecevierPhone().equals(phone)){
                return "{\"status\": \"exist address\"}";
            }
            if(old_address.getReceiverAddress().equals(remove_address) && old_address.getReceiverName().equals(remove_name) && old_address.getRecevierPhone().equals(remove_phone)){
                userAddressRepository.delete(old_address);
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

    public JSONObject searchGoods(String data){
        List<Goods> goodsList =  goodsRepository.findAll();
        List<JSONObject> okGoods = new ArrayList();
        for(Goods good:goodsList){
            String goodname = good.getGoodName();
            if(goodname.contains(data) && good.getHidden() == false){
                JSONObject item = new JSONObject();
                item.accumulate("id",good.getGoodId());
                item.accumulate("name",good.getGoodName());
                item.accumulate("store",storeRepository.findByStoreId(good.getStoreId()).getStoreName());
                item.accumulate("store_id",good.getStoreId());
                item.accumulate("price",good.getPrice());
                item.accumulate("coupon_price",good.getDiscount());
                item.accumulate("storage",good.getStorage());
                item.accumulate("description",good.getDescription());
                item.accumulate("image_id",good.getGoodImageId());
                List<Tags> tags = tagsRepository.findByGoodId(good.getGoodId());
                List<String> taglist = new ArrayList<String>();
                for(Tags tag:tags){
                    taglist.add(tag.getTagName());
                }
                item.accumulate("tags",taglist);
                okGoods.add(item);
            }
        }
        JSONObject result = new JSONObject();
        result.accumulate("status","ok");
        result.accumulate("values",okGoods);
        return  result;
    }

    public JSONObject searchStore(String data){
        List<Stores> storesList =  storeRepository.findAll();
        List<JSONObject> okStores = new ArrayList();
        for(Stores store:storesList){
            String storename = store.getStoreName();
            if(storename.contains(data)){
                JSONObject item = new JSONObject();
                item.accumulate("id",store.getStoreId());
                item.accumulate("name",store.getStoreName());
                item.accumulate("address",store.getStoreAddress());
                item.accumulate("starttime",store.getStartTime());
                item.accumulate("endtime",store.getEndTime());
                item.accumulate("image_id",store.getCoverId());
                okStores.add(item);
            }
        }
        JSONObject result = new JSONObject();
        result.accumulate("status","ok");
        result.accumulate("values",okStores);
        return  result;
    }

    public JSONObject goodDetail(Long id){
        Goods good = goodsRepository.findByGoodId(id);
        JSONObject item = new JSONObject();
        item.accumulate("id",id);
        item.accumulate("name",good.getGoodName());
        item.accumulate("store",storeRepository.findByStoreId(good.getStoreId()).getStoreName());
        item.accumulate("store_id",good.getStoreId());
        item.accumulate("price",good.getPrice());
        item.accumulate("coupon_price",good.getDiscount());
        item.accumulate("storage",good.getStorage());
        item.accumulate("description",good.getDescription());
        item.accumulate("image_id",good.getGoodImageId());
        List<GoodComments> commentsList = goodCommentsRepository.findByGoodId(id) ;
        JSONArray comments =new JSONArray();
        for(GoodComments goodComments:commentsList){
            JSONObject commentsitem = new JSONObject();
            Long userId = goodComments.getUserId();
            Users user= userRepository.findByUserId(userId);
            commentsitem.accumulate("username",user.getUsername());
            commentsitem.accumulate("comment_content",goodComments.getGooodComment() );
            commentsitem.accumulate("star_count",goodComments.getStar() );
            comments.add(commentsitem);
        }
        item.accumulate("comments",comments);
        item.accumulate("status","ok");
        return item;
    }

    public JSONObject storeDetail(Long id){
        Stores store = storeRepository.findByStoreId(id);
        JSONObject item = new JSONObject();
        if(store != null) {
            item.accumulate("id", id);
            String name = store.getStoreName();
            if (!name.isEmpty()) {
                item.accumulate("name", name);
            }

            String address = store.getStoreAddress();
            if (!address.isEmpty()) {
                item.accumulate("address", address);
            }

            String starttime = store.getStartTime();
            if (!starttime.isEmpty()) {
                item.accumulate("starttime", starttime);
            }

            String endtime = store.getEndTime();
            if (!endtime.isEmpty()) {
                item.accumulate("endtime", endtime);
            }

            String store_image_id = store.getCoverId();
            if (!store_image_id.isEmpty()) {
                item.accumulate("image_id", store_image_id);
            }


            String store_phone_nu = store.getStorePhone();
            if (!store_phone_nu.isEmpty()) {
                item.accumulate("phone_nu", store_phone_nu);
            }

            String dist_name = store.getDistName();
            if (!dist_name.isEmpty()) {
                item.accumulate("provider", dist_name);
            }

            String dist_image = store.getDistImageId();
            if (!dist_name.isEmpty()) {
                item.accumulate("provider_avatar", dist_image);
            }

            List<StoreComments> commentsList = storeCommentsRepository.findByStoreId(store.getStoreId());
            JSONArray comments = new JSONArray();
            for (StoreComments storeComments : commentsList) {
                System.out.printf("Get one comment %s\n", storeComments.getStoreComments());
                JSONObject commentsitem = new JSONObject();
                Long userId = storeComments.getUserId();
                Users user = userRepository.findByUserId(userId);
                System.out.printf("Found username %s by %d\n", user.getUsername(), user.getUserId());
                commentsitem.accumulate("username", user.getUsername());
                commentsitem.accumulate("comment_content", storeComments.getStoreComments());
                commentsitem.accumulate("star_count", storeComments.getStar());
                comments.add(commentsitem);
            }
            item.accumulate("comments", comments);

            List<Goods> GoodsList = goodsRepository.findByStoreId(id);
            JSONArray goods = new JSONArray();
            for (Goods good : GoodsList) {
                if (good.getHidden() == false) {
                    JSONObject items = new JSONObject();
                    items.accumulate("id", good.getGoodId());
                    String goodName = good.getGoodName();
                    if (!name.isEmpty()) {
                        items.accumulate("name", goodName);
                    }

                    BigDecimal price = good.getPrice();
                    if (price != null) {
                        items.accumulate("price", price);
                    }

                    BigDecimal count_price = good.getDiscount();
                    if (count_price != null) {
                        items.accumulate("coupon_price", count_price);
                    }

                    Long storage = good.getStorage();
                    if (storage != null) {
                        items.accumulate("storage", storage);
                    }

                    String description = good.getDescription();
                    if (!description.isEmpty()) {
                        items.accumulate("description", description);
                    }

                    String image_id = good.getGoodImageId();
                    if (!image_id.isEmpty()) {
                        items.accumulate("image_id", image_id);
                    }

                    goods.add(items);
                }
            }
            item.accumulate("values", goods);
            item.accumulate("status", "ok");
            return item;
        }
        else {
            item.accumulate("status", "not exist");
            return item;
        }
    }

    public String addToCart(JSONObject data){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = userRepository.findByUserName(userDetails.getUsername());
        Carts cart = new Carts();
        cart.setUserId(currentUser.getUserId());
        cart.setAmount(data.getLong("amount"));
        cart.setGoodId(data.getLong("id"));
        cartRepository.save(cart);
        return "{\"status\": \"ok\"}";
    }

    public JSONObject myCart(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = userRepository.findByUserName(userDetails.getUsername());
        List<Carts> cartList = cartRepository.findByUserId(currentUser.getUserId());
        JSONArray items = new JSONArray();
        for(Carts cart:cartList){
            Goods good = goodsRepository.findByGoodId(cart.getGoodId());
            if(good.getHidden() == false){
                JSONObject item = new JSONObject();
                item.accumulate("id",good.getGoodId());
                item.accumulate("name",good.getGoodName());
                item.accumulate("image_id",good.getGoodImageId());
                item.accumulate("price",good.getDiscount());
                item.accumulate("amount",cart.getAmount());
                items.add(item);
            }
        }
        JSONObject result = new JSONObject();
        result.accumulate("status","ok");
        result.accumulate("values",items);
        return  result;
    }

    public String deleteCart(JSONObject data){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = userRepository.findByUserName(userDetails.getUsername());
        Carts cart = cartRepository.findByUserIdAndGoodId(currentUser.getUserId(),data.getLong("id"));
        if(cart == null){
            return "{\"status\": \"haven delete\"}";
        }
        else {
            cartRepository.delete(cart);
            return "{\"status\": \"ok\"}";
        }
    }
}
