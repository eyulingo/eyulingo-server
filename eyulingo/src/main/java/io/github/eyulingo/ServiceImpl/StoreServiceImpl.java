package io.github.eyulingo.ServiceImpl;


import io.github.eyulingo.Dao.StoreCommentsRepository;
import io.github.eyulingo.Dao.StoreRepository;
import io.github.eyulingo.Dao.UserRepository;
import io.github.eyulingo.Entity.StoreComments;
import io.github.eyulingo.Entity.Stores;
import io.github.eyulingo.Service.StoreService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.eyulingo.Entity.Users;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    StoreCommentsRepository storeCommentsRepository;

    @Autowired
    UserRepository userRepository;

    public String distLogin(JSONObject data) {
        try {
            System.out.println(data.getString("distName"));
            Stores dist = storeRepository.findByDistName(data.getString("distName"));
            if (dist == null) return "{\"status\": \"distName not exists\"}";

            if (dist.getDistPassword().equals(data.getString("password"))) {
                return "{\"status\": \"ok\"}";
            } else {
                return "{\"status\": \"Incorrect password\"}";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
    }

    public JSONObject getDist(String data) {
        Stores store = storeRepository.findByDistName(data);
        JSONObject item = new JSONObject();
        item.accumulate("store_id", store.getStoreId());

        String location = store.getDistLocation();
        if (!location.isEmpty()) {
            item.accumulate("location", location);
        }

        String truename = store.getDistName();
        if (!truename.isEmpty()) {
            item.accumulate("truename", truename);
        }

        String dist_phone_nu = store.getDistPhone();
        if (!dist_phone_nu.isEmpty()) {
            item.accumulate("dist_phone_nu", dist_phone_nu);
        }

        String password = store.getDistPassword();
        if (!dist_phone_nu.isEmpty()) {
            item.accumulate("password", password);
        }

        String dist_image_id = store.getDistImageId();
        if (!dist_image_id.isEmpty()) {
            item.accumulate("dist_image_id", dist_image_id);
        }

        return item;
    }

    public String modifyDist(JSONObject data) {

        try {
            System.out.println(data);
            Stores store = storeRepository.findByStoreId(data.getLong("store_id"));

            String location = data.getString("location");
            if (!location.isEmpty()) {
                store.setDistLocation(location);
            }

            String truename = data.getString("truename");
            if (!location.isEmpty()) {
                store.setDistName(truename);
            }

            String dist_phone_nu = data.getString("dist_phone_nu");
            if (!location.isEmpty()) {
                store.setDistPhone(dist_phone_nu);
            }

            String password = data.getString("password");
            if (!location.isEmpty()) {
                store.setDistPassword(password);
            }
            storeRepository.save(store);
            return "{\"status\": \"ok\"}";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
    }

    public JSONObject getMyStore(String data) {
        Stores store = storeRepository.findByDistName(data);
        JSONObject item = new JSONObject();
        item.accumulate("store_id", store.getStoreId());
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
            item.accumulate("store_image_id", store_image_id);
        }

        String delivery_method = store.getDeliverMethod();
        if (!delivery_method.isEmpty()) {
            item.accumulate("delivery_method", delivery_method);
        }


        String store_phone_nu = store.getStorePhone();
        if (!store_phone_nu.isEmpty()) {
            item.accumulate("store_phone_nu", store_phone_nu);
        }

        List<StoreComments> commentsList = storeCommentsRepository.findByStoreId(store.getStoreId()) ;
        JSONArray comments =new JSONArray();
        for(StoreComments storeComments:commentsList){
            System.out.printf("Get one comment %s\n", storeComments.getStoreComments());
            JSONObject commentsitem = new JSONObject();
            Long userId = storeComments.getUserId();
            Users user= userRepository.findByUserId(userId);
            System.out.printf("Found username %s by %d\n", user.getUserName(), user.getUserId());
            commentsitem.accumulate("username",user.getUserName() );
            commentsitem.accumulate("comment_content",storeComments.getStoreComments() );
            commentsitem.accumulate("star_count",storeComments.getStar() );
            comments.add(commentsitem);
        }
        item.accumulate("comments",comments);

        return item;
    }
}
