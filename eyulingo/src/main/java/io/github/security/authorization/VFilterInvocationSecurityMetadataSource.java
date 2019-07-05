package io.github.security.authorization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

/**
 * 权限资源管理器
 * 根据用户请求的地址，获取访问该地址需要的所需权限
 * @author Veiking
 */
@Component
public class VFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	Gson gson = new Gson();
	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		//获取请求起源路径
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		logger.info("VFilterInvocationSecurityMetadataSource getAttributes [requestUrl={}]", requestUrl);
		//登录页面就不需要权限
        if ("/login".equals(requestUrl)) {
            return null;
        }

        return null;//都可以访问
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
