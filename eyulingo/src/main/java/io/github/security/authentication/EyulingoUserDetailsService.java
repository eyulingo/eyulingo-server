package io.github.security.authentication;

import java.util.List;
import java.util.logging.Logger;

import io.github.eyulingo.Dao.StoreRepository;
import io.github.eyulingo.Dao.UserRepository;
import io.github.eyulingo.Entity.Users;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 提供用户信息封装服务
 * @author Veiking
 */
@Service
public class EyulingoUserDetailsService implements UserDetailsService {

	@Autowired
    UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.printf("用户的用户名: {%s}", username);
		// TODO 根据用户名，查找到对应的密码，与权限

		// 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
		Users foundUser = userRepository.findByUserName(username);
		if (foundUser == null) {
			throw new UsernameNotFoundException("no user found");
		}
		User realUser = new User(foundUser.getUserName(), foundUser.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("user"));
		return realUser;
	}


}
