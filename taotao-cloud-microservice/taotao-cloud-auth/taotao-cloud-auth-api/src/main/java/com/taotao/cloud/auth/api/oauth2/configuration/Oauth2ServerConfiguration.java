///*
// * Copyright (c) 2020-2021 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.taotao.cloud.oauth2.api.oauth2.configuration;
//
//import static org.springframework.security.oauth2.server.authorization.config.TokenSettings.ACCESS_TOKEN_TIME_TO_LIVE;
//import static org.springframework.security.oauth2.server.authorization.config.TokenSettings.REFRESH_TOKEN_TIME_TO_LIVE;
//import static org.springframework.security.oauth2.server.authorization.config.TokenSettings.REUSE_REFRESH_TOKENS;
//
//import com.nimbusds.jose.jwk.JWKSet;
//import com.nimbusds.jose.jwk.RSAKey;
//import com.nimbusds.jose.jwk.source.JWKSource;
//import com.nimbusds.jose.proc.SecurityContext;
//import java.time.Duration;
//import java.util.UUID;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration(proxyBeanMethods = false)
//@Import(OAuth2AuthorizationServerConfiguration.class)
//public class Oauth2ServerConfiguration {
//
//	@Bean
//	public RegisteredClientRepository registeredClientRepository() {
//		RegisteredClient registeredClient = RegisteredClient
//			.withId(UUID.randomUUID().toString())
//			.clientId("client")
//			.clientSecret("secret")
//			.clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
//			.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//			.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//			.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//			.redirectUri("https://www.baidu.com")
//			.scope("message.read")
//			.scope("message.write")
//			.clientSettings(clientSettings -> clientSettings.requireUserConsent(true))
//			.tokenSettings(tokenSettings -> {
//				tokenSettings
//					.settings(settings -> {
//						settings.put(ACCESS_TOKEN_TIME_TO_LIVE, Duration.ofMinutes(1000));
//						settings.put(REUSE_REFRESH_TOKENS, true);
//						settings.put(REFRESH_TOKEN_TIME_TO_LIVE, Duration.ofMinutes(6000));
//					});
//			})
//			.build();
//
//		return new InMemoryRegisteredClientRepository(registeredClient);
//	}
//
//	@Bean
//	UserDetailsService users() {
//		UserDetails user = User.withDefaultPasswordEncoder()
//			.username("admin")
//			.password("123456")
//			.roles("USER")
//			.build();
//		return new InMemoryUserDetailsManager(user);
//	}
//
//	@Bean
//	public JWKSource<SecurityContext> jwkSource() {
//		RSAKey rsaKey = Jwks.generateRsa();
//		JWKSet jwkSet = new JWKSet(rsaKey);
//		return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
//	}
//
//	@Bean
//	public ProviderSettings providerSettings() {
//		return new ProviderSettings().issuer("http://127.0.0.1:9000");
//	}
//}
