package io.github.eyulingo.ServiceImpl;


import io.github.eyulingo.Dao.GoodsRepository;
import io.github.eyulingo.Dao.StoreRepository;
import io.github.eyulingo.Dao.TagsRepository;
import io.github.eyulingo.Entity.Goods;
import io.github.eyulingo.Entity.Stores;
import io.github.eyulingo.Entity.Tags;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.github.eyulingo.Service.AdminService;
import io.github.eyulingo.Dao.AdminRepository;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    TagsRepository tagsRepository;



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
                store.setStoreAddress(address);
            }
            String name = data.getString("name");
            if(!name.isEmpty()){
                store.setStoreName(name);
            }
            String starttime = data.getString("starttime");
            if(!starttime.isEmpty()){
                store.setStartTime(starttime);
            }
            String endtime = data.getString("endtime");
            if(!endtime.isEmpty()){
                store.setEndTime(endtime);
            }

            String store_phone_nu = data.getString("store_phone_nu");
            if(!store_phone_nu.isEmpty()){
                store.setStorePhone(store_phone_nu);
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



    public JSONObject getStoreGood(String store_id){
        if(store_id.isEmpty()){
            List<Goods> GoodsList = goodsRepository.findAll();
            JSONObject allGoods = new JSONObject();
            JSONArray goods = new JSONArray();
            for(Goods good:GoodsList){
                JSONObject item = new JSONObject();
                item.accumulate("id", good.getGoodId());
                String name = good.getGoodName();
                if (!name.isEmpty()) {
                    item.accumulate("name", name);
                }

                BigDecimal price = good.getPrice();
                if (price != null) {
                    item.accumulate("price", price);
                }

                BigDecimal count_price = good.getDiscount();
                if (count_price != null) {
                    item.accumulate("coupon_price", count_price);
                }

                Long storage = good.getStorage();
                if (storage != null) {
                    item.accumulate("storage", storage);
                }

                String description = good.getDescription();
                if (!description.isEmpty()) {
                    item.accumulate("description", description);
                }

                String image_id = good.getGoodImageId();
                if (!image_id.isEmpty()) {
                    item.accumulate("image_id", image_id);
                }


                Boolean hidden = good.getHidden();
                if (hidden != null) {
                    item.accumulate("hidden", hidden);
                }

                List<Tags> tagsList = tagsRepository.findByGoodId(good.getGoodId());
                List<String> list = new ArrayList<String>();
                for(Tags tag:tagsList){
                    list.add(tag.getTagName());
                }
                item.accumulate("tags",list);
                goods.add(item);
            }
            allGoods.accumulate("status","ok");
            allGoods.accumulate("values",goods);
            return  allGoods;
        }
        else {
            List<Goods> GoodsList = goodsRepository.findByStoreId(new Long(store_id));
            JSONObject allGoods = new JSONObject();
            JSONArray goods = new JSONArray();
            for (Goods good : GoodsList) {
                JSONObject item = new JSONObject();
                item.accumulate("id", good.getGoodId());
                String name = good.getGoodName();
                if (!name.isEmpty()) {
                    item.accumulate("name", name);
                }

                BigDecimal price = good.getPrice();
                if (price != null) {
                    item.accumulate("price", price);
                }

                BigDecimal count_price = good.getDiscount();
                if (count_price != null) {
                    item.accumulate("coupon_price", count_price);
                }

                Long storage = good.getStorage();
                if (storage != null) {
                    item.accumulate("storage", storage);
                }

                String description = good.getDescription();
                if (!description.isEmpty()) {
                    item.accumulate("description", description);
                }

                String image_id = good.getGoodImageId();
                if (!image_id.isEmpty()) {
                    item.accumulate("image_id", image_id);
                }


                Boolean hidden = good.getHidden();
                if (hidden != null) {
                    item.accumulate("hidden", hidden);
                }

                List<Tags> tagsList = tagsRepository.findByGoodId(good.getGoodId());
                List<String> list = new ArrayList<String>();
                for (Tags tag : tagsList) {
                    list.add(tag.getTagName());
                }
                item.accumulate("tags", list);
                goods.add(item);
            }
            allGoods.accumulate("status", "ok");
            allGoods.accumulate("values", goods);
            return allGoods;
        }
    }

    public String addTag(JSONObject data){
        Tags tag = new Tags();
        tag.setGoodId(data.getLong("good_id"));
        tag.setTagName(data.getString("tag_name"));
        tagsRepository.save(tag);
        return "{\"status\": \"ok\"}";
    }

    public String deleteTag(JSONObject data){
        Tags tag = tagsRepository.findByGoodIdAndAndTagName(data.getLong("good_id"),data.getString("tag_name"));
        tagsRepository.delete(tag);
        return "{\"status\": \"ok\"}";
    }
}


