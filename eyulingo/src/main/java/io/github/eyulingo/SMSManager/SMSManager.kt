package io.github.eyulingo.SMSManager

import org.springframework.core.io.ClassPathResource
import java.util.*
import com.github.qcloudsms.SmsSingleSender



class SMSManager {

    private var appId: Int
    private var appKey: String

    init {
        val fis = ClassPathResource(
                "application.properties").inputStream
        // create Properties class object to access properties file
        val prop = Properties()
        // load the properties file
        prop.load(fis)
        // get the property of "url" using getProperty()

        // 短信应用SDK AppID
        this.appId = prop.getProperty("tencent.sms.appId").toInt()

        // 短信应用SDK AppKey
        this.appKey = prop.getProperty("tencent.sms.appKey")

        // 签名
        var smsSign = "优邻购Project"
    }

    fun sendCheckId(phoneNo: String, checkCode: String): Boolean {
        try {
            val ssender = SmsSingleSender(this.appId, this.appKey)
            val result = ssender.send(0, "86", phoneNo,
                    "【优邻购Project】您的验证码是: $checkCode", "", "")
            println(result)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
}