package io.github.eyulingo;

import io.github.eyulingo.SMSManager.SMSManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EyulingoApplication {

    public static void main(String[] args) {
        SMSManager sms = new SMSManager();
        sms.sendCheckId("18701961157", "您收到这条短信了吗？");
        SpringApplication.run(EyulingoApplication.class, args);
    }
}
