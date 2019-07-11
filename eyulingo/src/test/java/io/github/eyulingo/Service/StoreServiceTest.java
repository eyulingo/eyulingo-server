package io.github.eyulingo.Service;

import io.github.eyulingo.Service.StoreService;
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
public class StoreServiceTest {
    @Autowired
    private StoreService storeService;

    @Test
    public void getDistTest(){
        JSONObject item =new JSONObject();
        item.accumulate("store_id",1);
        item.accumulate("location","上海市静安区中华新路479号");
        item.accumulate("truename","乌绮玉");
        item.accumulate("dist_phone_nu","13640698865");
        item.accumulate("password","Wuqiyu123456");
        item.accumulate("dist_image_id","5d1d5e47634459000715143b");
        assertEquals(item, storeService.getDist("乌绮玉"));
    }

    @Test
    public void modifityDistTest(){
        JSONObject item =new JSONObject();
        item.accumulate("location","香港特別行政區黃大仙區新蒲崗大有街34號");
        item.accumulate("truename","方1语");
        item.accumulate("dist_phone_nu","53542013");
        item.accumulate("password","Fangyu123456");
        assertEquals("{\"status\": \"ok\"}",storeService.modifyDist("方语",item));

    }

    @Test
    public void getMyStoreTest(){
        assertEquals("13640698865", storeService.getDist("乌绮玉").getString("dist_phone_nu"));
    }

    @Test
    public void changeDistImageTest(){
        JSONObject data = new JSONObject();
        data.accumulate("image_id","7896145");
        storeService.ChangeDistImage("迟幼枫",data);
        assertEquals("7896145",storeService.getDist("迟幼枫").getString("dist_image_id"));
    }

    @Test
    public void changeMyStoreTesr(){
        JSONObject item = new JSONObject();
        item.accumulate("name","店铺名称");
        item.accumulate("address", "址");
        item.accumulate("starttime","营业开始间");
        item.accumulate("endtime","营业终止时间");
        item.accumulate("phone_nu", "联系电话");
        assertEquals("{\"status\": \"ok\"}",storeService.changeMyStore(item,"尚晴"));

    }
}