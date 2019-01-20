package com.frankie.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/*
password grant type
 */

public class PasswordConfig {
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    static class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.requestMatchers().antMatchers("/oauth/**")
                    .and()
                    .authorizeRequests()
                    .antMatchers("/oauth/**").authenticated();
        }

        //配置内存模式的用户
        @Bean
        @Override
        protected UserDetailsService userDetailsService() {
            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
            manager.createUser(User.withUsername("demoUser1").password("{noop}123456").authorities("USER").build());
            manager.createUser(User.withUsername("demoUser2").password("{noop}123456").authorities("USER","ADMIN").build());
            return manager;
        }

        /**
         * 需要配置这个支持password模式
         * support password grant type
         *
         * @return
         * @throws Exception
         */
        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }

    @Configuration
    @EnableAuthorizationServer //提供/oauth/authorize,/oauth/token,/oauth/check_token,/oauth/confirm_access,/oauth/error
    static class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {
        @Bean
        protected JwtAccessTokenConverter accessTokenConverter() {
            KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt-test.jks"), "my_pass".toCharArray());
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt-test"));
            return converter;
        }

        @Bean
        public TokenStore tokenStore() {
            return new JwtTokenStore(accessTokenConverter());
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer
                    .tokenKeyAccess("permitAll()") //url:/oauth/token_key,exposes public key for token verification if using JWT tokens
                    .checkTokenAccess("isAuthenticated()") //url:/oauth/check_token allow check token
                    .allowFormAuthenticationForClients();
        }

        /**
         * 注入authenticationManager
         * 来支持 password grant type
         */
        @Autowired
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.tokenStore(tokenStore())
                    .accessTokenConverter(accessTokenConverter())
                    .authenticationManager(authenticationManager);
        }

        @Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
            defaultTokenServices.setTokenStore(tokenStore());
            defaultTokenServices.setSupportRefreshToken(true);
            return defaultTokenServices;
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient("demoApp")
                    .secret("{noop}demoAppSecret")
                    .authorizedGrantTypes("client_credentials", "password", "refresh_token")
                    .scopes("web_client", "mobile_client")
                    .resourceIds("oauth2-resource")
                    .accessTokenValiditySeconds(1200)
                    .refreshTokenValiditySeconds(50000);
        }
    }

    @Configuration
    @EnableResourceServer
    static class ResourceServerConfig extends ResourceServerConfigurerAdapter {
        /**
         * 要正常运行，需要反注释掉这段，具体原因见下面分析
         * 这里设置需要token验证的url
         * 这些需要在WebSecurityConfigurerAdapter中排查掉
         * 否则优先进入WebSecurityConfigurerAdapter,进行的是basic auth或表单认证,而不是token认证
         *
         * @param http
         * @throws Exception
         */
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.requestMatchers().antMatchers("/api/**")
                    .and()
                    .authorizeRequests()
                    .antMatchers("/api/**").authenticated();
        }

    }
}
