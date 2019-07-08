package io.github.eyulingo.Service;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Service;


public interface UserService {
    public JSONObject getCheckCode(JSONObject data);

    public String register(JSONObject data);

    public JSONObject getMe();

    public String changeImage(JSONObject data);

    public String changePassword(JSONObject data);

    public String changeEmail(JSONObject data);
}
