package com.taotao.cloud.standalone.system.modules.security.social.gitee.connect;

import com.taotao.cloud.standalone.system.modules.security.social.gitee.api.Gitee;
import com.taotao.cloud.standalone.system.modules.security.social.gitee.api.GiteeImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @Classname GiteeServiceProvider
 * @Description Gitee 社交登录的自动配置
 * @Author shuigedeng
 * @since 2019-07-08 22:04
 * 
 */
public class GiteeServiceProvider extends AbstractOAuth2ServiceProvider<Gitee> {

	public GiteeServiceProvider(String clientId, String clientSecret) {

		super(new GiteeOAuth2Template(clientId, clientSecret, "https://gitee.com/oauth/authorize", "https://gitee.com/oauth/token"));
	}

	@Override
	public Gitee getApi(String accessToken) {
		return new GiteeImpl(accessToken);
	}
}
