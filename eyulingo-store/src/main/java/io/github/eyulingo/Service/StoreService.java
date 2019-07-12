package io.github.eyulingo.Service;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface StoreService {
    public  String distLogin(JSONObject data);

    public JSONObject getDist();

    public String modifyDist(JSONObject data);

    public JSONObject getMyStore();

    public  String ChangeDistImage(JSONObject data);

    public String changeMyStore(JSONObject data);
}
