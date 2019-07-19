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

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderitemsRepository orderitemsRepository;

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
            item.accumulate("status","邮箱格式错误");
            return item;
        }
        else if(usertest != null){
            item.accumulate("status","邮箱已被注册");
            return item;

        }
        else if(emalitset.size() != 0 && lastGetTime.compareTo(getDate) >= 0){
            item.accumulate("status","获取验证码三分钟后才能再次获取");
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
                item.accumulate("status", "发送失败");
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
            return  "{\"status\": \"各项信息不能为空\"}";
        }
        else if(!password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{8,20}$")){
            return  "{\"status\": \"密码格式错误\"}";
        }
        else if(!password.equals(confirm_password)) {
            return  "{\"status\": \"两次密码不一致\"}";
        }
        else if(usertest1 != null){
            return  "{\"status\": \"邮箱已被使用\"}";
        }
        else if(usertest2 != null){
            return  "{\"status\": \"用户名已被使用\"}";
        }
        else if(!email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"))
        {

            return "{\"status\": \"邮箱格式错误\"}";
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
                    return "{\"status\": \"验证码错误\"}";
                }
            }
            else {
                return "{\"status\": \"请先获取验证码\"}";
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
            return "{\"status\": \"密码不能为空\"}";
        }
        if(!data.getString("new_password").matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{8,20}$")){
            return  "{\"status\": \"密码格式错误\"}";
        }
        if(data.getString("origin_password").equals(currentUser.getPassword())){
            if(data.getString("new_password").equals(data.getString("confirm_new_password"))){
                if(data.getString("new_password").equals(data.getString("origin_password"))){
                    return "{\"status\": \"旧密码不能与新密码相同\"}";
                }
                else {
                    currentUser.setPassword(data.getString("new_password"));
                    userRepository.save(currentUser);
                    return "{\"status\": \"ok\"}";
                }
            }
            else{
                return "{\"status\": \"两次输入新密码应相同\"}";
            }
        }
        else{
            return "{\"status\": \"旧密码错误\"}";
        }
    }

    public String changeEmail(JSONObject data){
        String new_email = data.getString("new_email");
        if(!new_email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"))
        {
            return "{\"status\": \"邮箱格式错误\"}";
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
                return "{\"status\": \"验证码错误\"}";
            }
        }
        else {
            return "{\"status\": \"验证码错误\"}";
        }
    }

    public JSONObject findPasswordGetCode(JSONObject data){
        String username = data.getString("username");
        Users missUser = userRepository.findByUserName(username);
        if (missUser == null){
            JSONObject item = new JSONObject();
            item.accumulate("status","用户不存在");
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
                item.accumulate("status","邮箱格式错误");
                return item;
            }
            else if(lastGetTime.compareTo(getDate) >= 0){
                item.accumulate("status","两次验证码获取需间隔三分钟");
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
                    item.accumulate("status", "发送失败");
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
            return  "{\"status\": \"密码格式错误\"}";
        }
        else if(!password.equals(confirm)){
            return "{\"status\": \"两次输入密码需相同\"}";
        }
        else if (missUser == null){
            return "{\"status\": \"该用户不存在\"}";
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
                    return "{\"status\": \"错误验证码\"}";
                }
            }
            else {
                return "{\"status\": \"请先获取验证码\"}";
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
            return "{\"status\": \"地址信息不能为空\"}";
        }
        List<UserAddress> addressList = userAddressRepository.findByUserId(currentUser.getUserId()) ;
        for(UserAddress old_address:addressList){
            if(old_address.getReceiverAddress().equals(new_address) && old_address.getReceiverName().equals(name) && old_address.getRecevierPhone().equals(phone)){
                return "{\"status\": \"存在的地址\"}";
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
        return "{\"status\": \"地址已被删除\"}";
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
                return "{\"status\": \"地址已存在\"}";
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
            if(goodname.contains(data) && good.getHidden() == false && !data.isEmpty()){
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
            if(storename.contains(data) && !data.isEmpty()){
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
        BigDecimal star = new BigDecimal(0);
        JSONArray comments =new JSONArray();
        for(GoodComments goodComments:commentsList){
            JSONObject commentsitem = new JSONObject();
            Long userId = goodComments.getUserId();
            Users user= userRepository.findByUserId(userId);
            commentsitem.accumulate("username",user.getUsername());
            commentsitem.accumulate("comment_content",goodComments.getGooodComment() );
            commentsitem.accumulate("star_count",goodComments.getStar() );
            comments.add(commentsitem);
            star.add(new BigDecimal(goodComments.getStar()));
        }
        item.accumulate("comments",comments);
        if(commentsList.size()>0) {
            item.accumulate("star_number", commentsList.size());
            item.accumulate("star", star.divide(new BigDecimal(commentsList.size())));
        }
        else{
            item.accumulate("star_number", 0);
            item.accumulate("star", 0);
        }
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
            BigDecimal star = new BigDecimal(0);
            for (StoreComments storeComments : commentsList) {
                System.out.printf("Get one comment %s\n", storeComments.getStoreComments());
                JSONObject commentsitem = new JSONObject();
                Long userId = storeComments.getUserId();
                Users user = userRepository.findByUserId(userId);
                System.out.printf("Found username %s by %d\n", user.getUsername(), user.getUserId());
                commentsitem.accumulate("username", user.getUsername());
                commentsitem.accumulate("comment_content", storeComments.getStoreComments());
                commentsitem.accumulate("star_count", storeComments.getStar());
                star.add(new BigDecimal(storeComments.getStar()));
                comments.add(commentsitem);
            }
            item.accumulate("comments", comments);
            if(commentsList.size()>0) {
                item.accumulate("star_number", commentsList.size());
                item.accumulate("star", star.divide(new BigDecimal(commentsList.size())));
            }
            else{
                item.accumulate("star_number", 0);
                item.accumulate("star", 0);
            }

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
            item.accumulate("status", "商品不存在");
            return item;
        }
    }

    public String addToCart(JSONObject data){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = userRepository.findByUserName(userDetails.getUsername());
        Carts cartTest = cartRepository.findByUserIdAndGoodId(currentUser.getUserId(),data.getLong("id"));
        if(cartTest != null){
            cartTest.setAmount(cartTest.getAmount()+data.getLong("amount"));
            cartRepository.save(cartTest);
            return "{\"status\": \"ok\"}";
        }
        else {
            Carts cart = new Carts();
            cart.setUserId(currentUser.getUserId());
            cart.setAmount(data.getLong("amount"));
            cart.setGoodId(data.getLong("id"));
            cartRepository.save(cart);
            return "{\"status\": \"ok\"}";
        }
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
                Stores store = storeRepository.findByStoreId(good.getStoreId());
                item.accumulate("id",good.getGoodId());
                item.accumulate("name",good.getGoodName());
                item.accumulate("image_id",good.getGoodImageId());
                item.accumulate("price",good.getDiscount());
                item.accumulate("amount",cart.getAmount());
                item.accumulate("store_id",store.getStoreId());
                item.accumulate("store",store.getStoreName());
                item.accumulate("storage",good.getStorage());
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
            return "{\"status\": \"商品已被删除\"}";
        }
        else {
            cartRepository.delete(cart);
            return "{\"status\": \"ok\"}";
        }
    }

    public JSONObject purchase(JSONObject data){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = userRepository.findByUserName(userDetails.getUsername());
        Date date = new Date();
        Timestamp nowdate = new Timestamp(date.getTime());
        String receive_name = data.getString("receive_name");
        String receive_phone = data.getString("receive_phone");
        String receive_address = data.getString("receive_address");
        JSONArray idArray = data.getJSONArray("values");
        List<Long> goodsId = new ArrayList<>();
        List<Long> goodsAmount = new ArrayList<>();
        for(Object ob:idArray){
            JSONObject jsonObject = (JSONObject) ob;
            Long id = jsonObject.getLong("id");
            Long amount = jsonObject.getLong("amount");
            goodsId.add(id);
            Goods this_good = goodsRepository.findByGoodId(id);
            goodsAmount.add(amount);
            if(this_good == null || this_good.getHidden()==true){
                JSONObject result = new JSONObject();
                result.accumulate("status","选中的部分商品已下架");
                return  result;
            }
            if(this_good.getStorage() < amount){
                JSONObject result = new JSONObject();
                result.accumulate("status","库存不足");
                return  result;
            }

        }
        List<Long> storeId = new ArrayList<>();
        for(Long id:goodsId){
            Long new_id = goodsRepository.findByGoodId(id).getStoreId();
            if(!storeId.contains(new_id)) {
                storeId.add(new_id);
            }
        }

        int size = goodsId.size();
        BigDecimal cost = new BigDecimal(0);
        for(Long store_id:storeId){
            Orders order = new Orders();
            order.setDeliverMethod(storeRepository.findByStoreId(store_id).getDeliverMethod());
            order.setOrderTime(nowdate);
            order.setReAddress(receive_address);
            order.setReceiver(receive_name);
            order.setStatus("pending");
            order.setUserId(currentUser.getUserId());
            order.setStoreId(store_id);
            order.setRePhone(receive_phone);
            orderRepository.save(order);
            for(int i = 0;i<size;i++){
                if(goodsRepository.findByGoodId(goodsId.get(i)).getStoreId() == store_id){
                    OrderItems orderItem = new OrderItems();
                    orderItem.setAmount(goodsAmount.get(i));
                    orderItem.setCurrentPrice(goodsRepository.findByGoodId(goodsId.get(i)).getPrice());
                    orderItem.setGoodId(goodsId.get(i));
                    orderItem.setOrderId(order.getOrderId());
                    orderitemsRepository.save(orderItem);
                    Goods changed_good = goodsRepository.findByGoodId(goodsId.get(i));
                    changed_good.setStorage(changed_good.getStorage()-goodsAmount.get(i));
                    cost = goodsRepository.findByGoodId(goodsId.get(i)).getPrice().multiply(new BigDecimal(goodsAmount.get(i))).add(cost);
                }
            }
        }
        JSONObject result = new JSONObject();
        result.accumulate("status","ok");
        result.accumulate("cost",cost);
        return  result;
    }

    public JSONObject orderList(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = userRepository.findByUserName(userDetails.getUsername());
        List<Orders> ordersList = orderRepository.findByUserId(currentUser.getUserId());
        JSONObject result = new JSONObject();
        JSONArray values = new JSONArray();
        for(Orders order:ordersList){
            JSONObject item = new JSONObject();
            item.accumulate("bill_id",order.getOrderId());
            item.accumulate("receiver",order.getReceiver());
            item.accumulate("receiver_phone",order.getRePhone());
            item.accumulate("receiver_address",order.getReAddress());
            item.accumulate("transport_method",order.getDeliverMethod());
            item.accumulate("order_status",order.getStatus());
            List<OrderItems> orderItemsList = orderitemsRepository.findByOrderId(order.getOrderId());
            JSONArray goodsList = new JSONArray();
            for(OrderItems orderItem:orderItemsList){
                Goods good = goodsRepository.findByGoodId(orderItem.getGoodId());
                Stores store = storeRepository.findByStoreId(good.getStoreId());
                JSONObject goodDetail = new JSONObject();
                goodDetail.accumulate("id",orderItem.getGoodId());
                goodDetail.accumulate("name",good.getGoodName());
                goodDetail.accumulate("store",store.getStoreName());
                goodDetail.accumulate("store_id",store.getStoreId());
                goodDetail.accumulate("current_price",orderItem.getCurrentPrice());
                goodDetail.accumulate("amount",orderItem.getAmount());
                goodDetail.accumulate("description",good.getDescription());
                goodDetail.accumulate("image_id",good.getGoodImageId());
                goodsList.add(goodDetail);
            }
            item.accumulate("goods",goodsList);
            values.add(item);
        }
        result.accumulate("status","ok");
        result.accumulate("values",values);
        return result;
    }

    public String commentGoods(JSONObject data){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = userRepository.findByUserName(userDetails.getUsername());
        List<Orders> ordersList = orderRepository.findByUserId(currentUser.getUserId());
        Long id = data.getLong("id");
        Long star = data.getLong("star_count");
        String comment = data.getString("comment_content");
        List<OrderItems> isPurchase = new ArrayList<>();
        for(Orders order:ordersList){
            Long order_id = order.getOrderId();
            OrderItems orderItems = orderitemsRepository.findByOrderIdAndGoodId(order_id,id);
            if(orderItems != null){
                isPurchase.add(orderItems);
            }
        }
        if(isPurchase.size() == 0){
            return "{\"status\": \"您未购买该商品，无法评价\"}";
        }
        else {
            GoodComments newComment = new GoodComments();
            newComment.setGoodId(id);
            newComment.setGooodComment(comment);
            newComment.setStar(star);
            newComment.setUserId(currentUser.getUserId());
            goodCommentsRepository.save(newComment);
            return "{\"status\": \"ok\"}";
        }
    }

    public String commentStores(JSONObject data){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = userRepository.findByUserName(userDetails.getUsername());
        List<Orders> ordersList = orderRepository.findByUserId(currentUser.getUserId());
        Long id = data.getLong("id");
        Long star = data.getLong("star_count");
        String comment = data.getString("comment_content");
        List<Orders> isPurchase = new ArrayList<>();
        for(Orders order:ordersList){
            Long order_id = order.getOrderId();
            Orders orders = orderRepository.findByOrderIdAndStoreId(order_id,id);
            if(orders != null){
                isPurchase.add(orders);
            }
        }
        if(isPurchase.size() == 0){
            return "{\"status\": \"您未购买该店商品，无法评价\"}";
        }
        else {
            StoreComments newComment = new StoreComments();
            newComment.setStoreId(id);
            newComment.setStoreComments(comment);
            newComment.setStar(star);
            newComment.setUserId(currentUser.getUserId());
            storeCommentsRepository.save(newComment);
            return "{\"status\": \"ok\"}";
        }
    }

    public JSONObject suggestionGood(String data){
        List<Goods> allGoods = goodsRepository.findAll();
        List<String> allName = new ArrayList<>();
        for(Goods good:allGoods){
            if(good.getGoodName().contains(data) && !data.isEmpty() && !good.getHidden())
                allName.add(good.getGoodName());
        }
        JSONObject item = new JSONObject();
        item.accumulate("status","ok");
        item.accumulate("values",allName);
        return item;
    }

    public JSONObject suggestionStore(String data){
        List<Stores> allStores = storeRepository.findAll();
        List<String> allName = new ArrayList<>();
        for(Stores store:allStores){
            if(store.getStoreName().contains(data) && !data.isEmpty())
                allName.add(store.getStoreName());
        }
        JSONObject item = new JSONObject();
        item.accumulate("status","ok");
        item.accumulate("values",allName);
        return item;
    }

    public String getPassword(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = userRepository.findByUserName(userDetails.getUsername());
        return currentUser.getPassword();
    }

    public String editCart(JSONObject data){
        Long id = data.getLong("id");
        Long amount = data.getLong("amount");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = userRepository.findByUserName(userDetails.getUsername());
        Carts cart = cartRepository.findByUserIdAndGoodId(currentUser.getUserId(),id);
        cart.setAmount(amount);
        cartRepository.save(cart);
        return "{\"status\": \"ok\"}";
    }
}
