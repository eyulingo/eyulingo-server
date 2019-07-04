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
                return "adminName not exists";
            }
            Admins admin = adminsList.get(0);

            if (admin.getAdminPassword().equals(data.getString("password"))) {
                return "ok";
            } else {
                return "Incorrect password";
            }
        } catch (Exception ex) {
            return "internal_error";
        }
    }

    public JSONArray getAllStores(){
        Iterable<Stores> Slist = storeRepository.findAll();
        JSONArray res = new JSONArray();
        for(Stores store:Slist){
            JSONObject item = new JSONObject();
            item.accumulate("store_id",store.getStoreId());
            item.accumulate("name",store.getStoreName());
            item.accumulate("address",store.getStoreAddress());
            item.accumulate("starttime",store.getStartTime());
            item.accumulate("endtime",store.getEndTime());
            item.accumulate("store_image_id",store.getCoverId());
            item.accumulate("location",store.getDistLocation());
            item.accumulate("truename",store.getDistName());
            item.accumulate("dist_phone_nu",store.getDistPhone());
            item.accumulate("password",store.getDistPassword());
            item.accumulate("dist_image_id",store.getDistImageId());
            item.accumulate("store_phone_nu",store.getStorePhone());

            res.add(item);
        }
        return res;
    }

    public String moditifyStores(JSONObject data){
        System.out.println(data);
        Stores store = storeRepository.findByStoreId(data.getLong("store_id"));
        store.setStoreName(data.getString("name"));
        store.setStoreAddress(data.getString("address"));
        store.setStartTime(data.getString("starttime"));
        store.setEndTime(data.getString("endtime"));
        store.setCoverId(data.getString("store_image_id"));
        store.setStorePhone(data.getString("store_phone_nu"));
        storeRepository.save(store);
        return "modify user success";
    }

    public String moditifyDist(JSONObject data){
        System.out.println(data);
        Stores store = storeRepository.findByStoreId(data.getLong("store_id"));
        store.setDistLocation(data.getString("location"));
        store.setDistName(data.getString("truename"));
        store.setDistPhone(data.getString("dist_phone_nu"));
        store.setDistPassword(data.getString("password"));
        store.setDistImageId(data.getString("dist_image_id"));
        storeRepository.save(store);
        return "modify user success";
    }

}
