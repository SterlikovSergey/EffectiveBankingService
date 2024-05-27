package st.sergey.minsky.shop2doordelivers.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JWTTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "/api/admin/**";
    private static final String CREATE_USER_ENDPOINT = "/user";
    private static final String LOGIN_ENDPOINT = "/user/login";
    private static final String CREATE_DIRECTION_ENDPOINT = "/direction";
    private static final String CREATE_CATEGORY_ENDPOINT = "/category";
    private static final String CREATE_PRODUCT_ENDPOINT = "/product";
    private static final String CREATE_STORE_ENDPOINT = "/store";
    private static final String CREATE_STOCK_ENDPOINT = "/stock";
    private static final String CREATE_ORDER_ENDPOINT = "/order";
    private static final String CREATE_COURIER_ENDPOINT = "/courier";

    private static final String[] PUBLIC_URLS = {
            "/**",
            "/v2/api-docs",
            "/store/**",
            "/product/**",
            "/category/**",
            "/order/**",
            "/courier/**",
            "/swagger-ui/index.html",
            "/swagger-resources/**",
            "configuration/**",
            "webjars/**",
            "/*.html",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",
            "/user/allUsers",
            "/direction/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, CREATE_USER_ENDPOINT, CREATE_COURIER_ENDPOINT,
                        LOGIN_ENDPOINT, CREATE_DIRECTION_ENDPOINT,CREATE_CATEGORY_ENDPOINT,CREATE_PRODUCT_ENDPOINT,
                        CREATE_STORE_ENDPOINT,CREATE_ORDER_ENDPOINT,CREATE_STOCK_ENDPOINT).permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, PUBLIC_URLS).permitAll()
                .antMatchers("/db/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/store/**").permitAll()
                .antMatchers("/order/**").permitAll()
                .antMatchers("/stock/**").permitAll()
                .antMatchers("/product/**").permitAll()
                .antMatchers("/category/**").permitAll()
                .antMatchers("/courier/**").permitAll()
                .antMatchers("/direction/**").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JWTConfig(jwtTokenProvider));
        http
                .headers().frameOptions().sameOrigin();
    }
}
