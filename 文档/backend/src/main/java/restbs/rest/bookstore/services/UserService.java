package restbs.rest.bookstore.services;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface UserService {
    public String register(JSONObject data);

    public String login(JSONObject data);

    public String rememberLogin(String username,String password);

    public JSONObject getUserInfo(String username);


    public JSONArray getAllUsers();

}
