package io.github.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/**
 * Security 主配置文件
 * @author Veiking
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 定义不需要过滤的静态资源（等价于HttpSecurity的permitAll）
	 */
	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity.ignoring().antMatchers("/store/**", "/admin/**");
	}

	/**
	 * 安全策略配置
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.authorizeRequests().antMatchers("/store/**", "/admin/**","/img/download", "/img/upload", "/login", "/failure", "/ok").permitAll()
				.anyRequest().authenticated().and()
				// 定义当需要用户登录时候，转到的登录页面
				.formLogin().loginPage("/login")
				.defaultSuccessUrl("/ok")
				.permitAll()
				.failureUrl("/failure").and()
				// 定义登出操作
				.logout().logoutSuccessUrl("/login?logout").permitAll().and()
				.csrf().disable()
		;
		// 禁用缓存
		httpSecurity.headers().cacheControl();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// ALTOUGH THIS SEEMS LIKE USELESS CODE,
		// ITS REQUIRED TO PREVENT SPRING BOOT AUTO-CONFIGURATION
		return super.authenticationManagerBean();
	}

}
