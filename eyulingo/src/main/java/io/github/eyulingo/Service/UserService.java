package io.github.eyulingo.Service;

import net.sf.json.JSONObject;



public interface UserService {
    public JSONObject getCheckCode(JSONObject data);

    public String register(JSONObject data);

    public JSONObject getMe();

    public String changeImage(JSONObject data);


}
