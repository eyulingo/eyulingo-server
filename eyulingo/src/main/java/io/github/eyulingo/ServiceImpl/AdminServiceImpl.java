package io.github.eyulingo.ServiceImpl;


import io.github.eyulingo.Dao.StoreRepository;
import io.github.eyulingo.Entity.Stores;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.eyulingo.Service.AdminService;
import io.github.eyulingo.Dao.AdminRepository;
import io.github.eyulingo.Entity.Admins;


import java.util.List;


@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    StoreRepository storeRepository;

    public String adminLogin(JSONObject data) {
        try {
            List<Admins> adminsList = adminRepository.findByAdminName(data.getString("adminName"));
            if (adminsList.isEmpty()) {
                return "{\"status\": \"adminName not exists\"}";
            }
            Admins admin = adminsList.get(0);

            if (admin.getAdminPassword().equals(data.getString("password"))) {
                return "{\"status\": \"ok\"}";
            } else {
                return "{\"status\": \"Incorrect password\"}";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
    }

    public JSONArray getAllStores(){
            Iterable<Stores> Slist = storeRepository.findAll();
            JSONArray res = new JSONArray();
            for (Stores store : Slist) {
                JSONObject item = new JSONObject();
                item.accumulate("store_id", store.getStoreId());
                String name=store.getStoreName();
                if(!name.isEmpty()){
                    item.accumulate("name",name );
                }

                String address=store.getStoreAddress();
                if(!address.isEmpty()){
                    item.accumulate("address",address);
                }

                String starttime = store.getStartTime();
                if(!starttime.isEmpty()){
                    item.accumulate("starttime", starttime);
                }

                String endtime = store.getEndTime();
                if(!endtime.isEmpty()){
                    item.accumulate("endtime", endtime);
                }

                String store_image_id = store.getCoverId();
                if(!store_image_id.isEmpty()){
                    item.accumulate("store_image_id",store_image_id);
                }

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
                if(!password.isEmpty()){
                    item.accumulate("password",password);
                }

                String dist_image_id =  store.getDistImageId();
                if(!dist_image_id.isEmpty()){
                    item.accumulate("dist_image_id", dist_image_id);
                }

                String store_phone_nu =  store.getStorePhone();
                if (!store_phone_nu.isEmpty()) {
                    item.accumulate("store_phone_nu", store_phone_nu);
                }
                res.add(item);
            }
            return res;
        }

    public String modifyStores(JSONObject data){
        try {
            System.out.println(data);
            Stores store = storeRepository.findByStoreId(data.getLong("store_id"));
            String address = data.getString("address");
            if(!address.isEmpty()){
                store.setStoreName(address);
            }
            String name = data.getString("name");
            if(!name.isEmpty()){
                store.setStoreName(name);
            }
            String starttime = data.getString("starttime");
            if(!starttime.isEmpty()){
                store.setStoreName(starttime);
            }
            String endtime = data.getString("endtime");
            if(!endtime.isEmpty()){
                store.setStoreName(endtime);
            }
            String store_phone_nu = data.getString("store_phone_nu");
            if(!store_phone_nu.isEmpty()){
                store.setStoreName(store_phone_nu);
            }
            storeRepository.save(store);
            return "{\"status\": \"ok\"}";
        }
        catch(Exception ex){
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
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
}


