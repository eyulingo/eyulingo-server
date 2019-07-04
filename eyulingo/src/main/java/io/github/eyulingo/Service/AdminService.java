package io.github.eyulingo.Service;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public interface AdminService {
    public String adminLogin(JSONObject data);

    public JSONArray getAllStores();

    public String moditifyStores(JSONObject data);

    public String moditifyDist(JSONObject data);

}
