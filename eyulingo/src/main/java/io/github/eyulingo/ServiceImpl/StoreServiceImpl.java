package io.github.eyulingo.ServiceImpl;


import io.github.eyulingo.Dao.StoreRepository;
import io.github.eyulingo.Entity.Stores;
import io.github.eyulingo.Service.StoreService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreRepository storeRepository;
    
    public String distLogin(JSONObject data){
        try {
            List<Stores> distList = storeRepository.findByDistName(data.getString("distName"));
            if (distList.isEmpty()) {
                return "{\"status\": \"distName not exists\"}";
            }
            Stores dist = distList.get(0);

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

    public JSONObject getDist(String data){
            Stores store = storeRepository.findByStoreName(data);
            JSONObject item = new JSONObject();
            item.accumulate("store_id", store.getStoreId());

            String location = store.getDistLocation();
            if(!location.isEmpty()){
                item.accumulate("location",location);
            }

            String truename = store.getDistName();
            if(!truename.isEmpty()){
                item.accumulate("truename",truename);
            }

            String dist_phone_nu= store.getDistPhone();
            if(!dist_phone_nu.isEmpty()){
                item.accumulate("dist_phone_nu",dist_phone_nu);
            }

            String password = store.getDistPassword();
            if(!dist_phone_nu.isEmpty()){
                item.accumulate("password",password);
            }

            String dist_image_id =  store.getDistImageId();
            if(!dist_image_id.isEmpty()){
                item.accumulate("dist_image_id", dist_image_id);
            }

            return item;
    }
}
