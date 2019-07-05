package io.github.security.authentication;

import java.util.Collection;
import java.util.List;

import io.github.eyulingo.Entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.gson.Gson;

/**
 * 用户信息的封装，包含用户名称密码及用户状态、权限等信息
 * @author Veiking
 */
public class VUserDetails extends Users implements UserDetails {

	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	Logger logger = LoggerFactory.getLogger(this.getClass());


	public VUserDetails(Users sUser) {
		super(sUser);
	}
	/**
	 * 获取用户权限列表方法
	 * 可以理解成，返回了一个List<String>，之后所谓的权限控制、鉴权，其实就是跟这个list里的String进行对比
	 * 这里处理了角色和资源权限两个列表，可以这么理解，
	 * 角色是权限的抽象集合，是为了更方便的控制和分配权限，而真正颗粒化细节方面，还是需要资源权限自己来做
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		StringBuilder authoritiesBuilder = new StringBuilder("");

		String authoritiesStr = "";
		if(authoritiesBuilder.length()>0) {
			authoritiesStr = authoritiesBuilder.deleteCharAt(0).toString();
		}
		logger.info("VUserDetails getAuthorities [authoritiesStr={} ", authoritiesStr);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesStr);
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getUserName();
	}

	/**
	 * 判断账号是否已经过期，默认没有过期
	 */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 判断账号是否被锁定，默认没有锁定
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 判断信用凭证是否过期，默认没有过期
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

    /**
     * 判断账号是否可用，默认可用
     */
	@Override
	public boolean isEnabled() {
		return true;
	}


}
