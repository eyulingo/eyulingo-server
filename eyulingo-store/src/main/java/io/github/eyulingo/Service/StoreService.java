package io.github.eyulingo.Service;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface StoreService {

    public JSONObject getDist();

    public String modifyDist(JSONObject data);

    public JSONObject getMyStore();

    public  String ChangeDistImage(JSONObject data);

    public String changeMyStore(JSONObject data);

    public String changeStoreImage(JSONObject data);

    public JSONObject getDeliver();

    public  String setDeliver(JSONObject data);

    public JSONArray getAllDeliver();
}
