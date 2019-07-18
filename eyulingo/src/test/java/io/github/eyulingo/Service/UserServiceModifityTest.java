package io.github.eyulingo.Service;

import io.github.eyulingo.Service.UserService;
import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static net.sf.json.test.JSONAssert.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


/**
 * Created by admin on 2017/9/21-11:33.
 * Description :
 */
@Transactional
@Rollback()
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceModifityTest {
    @Autowired
    private UserService userService;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before // 在测试开始前初始化工作
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();
    }

    @Test
    @WithMockUser(username="无竹")
    public void changeImageTest() {
        JSONObject item = new JSONObject();
        item.accumulate("avatar_id","123");
        userService.changeImage(item);
        assertEquals("123", userService.getMe().getString("avatar"));
    }

    @Test
    @WithMockUser(username="无竹")
    public void changePasswordTest(){
        JSONObject item = new JSONObject();
        item.accumulate("origin_password","Wuzhu123456");
        item.accumulate("new_password","a123456789");
        item.accumulate("confirm_new_password","a123456789");
        userService.changePassword(item);
        assertEquals("a123456789", userService.getPassword());
    }



}