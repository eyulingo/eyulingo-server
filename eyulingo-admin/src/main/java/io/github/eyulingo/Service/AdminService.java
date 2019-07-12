package io.github.eyulingo.Service;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public interface AdminService {


    public JSONArray getAllStores();

    public String modifyStores(JSONObject data);

    public String modifyDist(JSONObject data);

}

