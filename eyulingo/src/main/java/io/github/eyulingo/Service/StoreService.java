package io.github.eyulingo.Service;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface StoreService {
    public  String distLogin(JSONObject data);

    public JSONObject getDist(String data);

    public String modifyDist(String name,JSONObject data);

    public JSONObject getMyStore(String data);

    public  String ChangeDistImage(String distName,JSONObject data);

    public String changeMyStore(JSONObject data,String name);
}
