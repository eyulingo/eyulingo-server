package io.github.security.authentication;

import java.util.List;

import io.github.eyulingo.Dao.StoreRepository;
import io.github.eyulingo.Dao.UserRepository;
import io.github.eyulingo.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 提供用户信息封装服务
 * @author Veiking
 */
@Service
public class VUserDetailsService implements UserDetailsService {


	@Autowired
    UserRepository userRepository;
	/**
	 * 根据用户输入的用户名返回数据源中用户信息的封装，返回一个UserDetails
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		Users sUser = userRepository.findByUserName(username);

		return new VUserDetails(sUser);
	}

}
