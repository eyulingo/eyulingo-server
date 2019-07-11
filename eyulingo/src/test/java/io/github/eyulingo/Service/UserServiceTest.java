package io.github.eyulingo.Service;

import io.github.eyulingo.Service.UserService;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static net.sf.json.test.JSONAssert.assertEquals;


/**
 * Created by admin on 2017/9/21-11:33.
 * Description :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void getCheckCodeTest(){
        JSONObject data= new JSONObject();
        data.accumulate("email","1217286706@qq.com");
        JSONObject item = new JSONObject();
        item.accumulate("status","ok");
        assertEquals(item, userService.getCheckCode(data));
    }
}
