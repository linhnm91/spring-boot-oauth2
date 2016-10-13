package com.codehubs.springoauth2.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class OAuth2ServerConfiguration {

	private static final String RESOURCE_ID = "restservice";

	@Configuration
	@EnableResourceServer
	protected static class OAuth2ResourceServerConfiguration extends
			ResourceServerConfigurerAdapter {
		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) {
			// @formatter:off
			resources.resourceId(RESOURCE_ID);
			// @formatter:on
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http
				.authorizeRequests()
				.antMatchers("/api/**").authenticated();
			// @formatter:on
		}

	}

	@Configuration
	@EnableAuthorizationServer
	protected static class OAuth2AuthorizationServerConfiguration extends
			AuthorizationServerConfigurerAdapter {

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints)
				throws Exception {
			// @formatter:off
			endpoints.tokenStore(tokenStore())
					.authenticationManager(this.authenticationManager)
					.pathMapping("/oauth/token", "/api/auth/token");
			// @formatter:on
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients)
				throws Exception {
			// @formatter:off
			clients.inMemory().withClient("clientapp")
					.authorizedGrantTypes("password", "refresh_token")
					.authorities("USER").scopes("read", "write")
					.resourceIds(RESOURCE_ID).secret("123456");
			// @formatter:on
		}

		@Bean
		public TokenStore tokenStore() {
			return new InMemoryTokenStore();
		}

		@Bean
		@Primary
		public DefaultTokenServices tokenServices() {
			DefaultTokenServices tokenServices = new DefaultTokenServices();
			tokenServices.setSupportRefreshToken(true);
			tokenServices.setTokenStore(tokenStore());
			return tokenServices;
		}

	}
}
