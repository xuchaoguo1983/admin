package cn.zmvision.ccm.factory.system.security;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.zmvision.ccm.factory.Constants;
import cn.zmvision.ccm.factory.system.dao.model.User;
import cn.zmvision.ccm.factory.system.service.UserService;

/**
 * 登陆验证
 * 
 * @author xuchaoguo
 * 
 */
public class MyRealm extends AuthorizingRealm {
	@Resource
	UserService userService;

	/**
	 * 为当前登录的Subject授予角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		return new SimpleAuthorizationInfo();
	}

	/**
	 * 验证当前登录的Subject
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		User user = userService.getByUserName(token.getUsername());
		if (user == null)
			throw new UnknownAccountException();
		if (!user.getPassword().equals(new String(token.getPassword())))
			throw new IncorrectCredentialsException();
		if (!user.getStatus().equals(Constants.CODE_ENTITY_ACTIVE))
			throw new LockedAccountException();

		// 保存一些会话记录
		SessionUtil.setUser(user);

		AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
				user.getUsername(), user.getPassword(), this.getName());

		return authcInfo;
	}
}
