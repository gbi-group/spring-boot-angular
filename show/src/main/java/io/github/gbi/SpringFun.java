package io.github.gbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Date;
import java.util.Map;

@EnableAutoConfiguration
@ComponentScan
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringFun extends WebMvcConfigurerAdapter {
    /*
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringFun.class);
        app.setShowBanner(false);
        app.run(args);
    }
    */
    @Controller
    protected static class HomeController {

        @RequestMapping("/")
        @Secured("ROLE_ADMIN")
        public String home(Map<String, Object> model) {
            model.put("message", "Hello World");
            model.put("title", "Hello Home");
            model.put("date", new Date());
            return "home";
        }

    }

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(SpringFun.class).run(args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/access").setViewName("access");
    }

    @Bean
    public ApplicationSecurity applicationSecurity() {
        return new ApplicationSecurity();
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Configuration
    protected static class AuthenticationSecurity extends
            GlobalAuthenticationConfigurerAdapter {

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            // @formatter:off
            auth.inMemoryAuthentication().withUser("admin").password("admin")
                    .roles("ADMIN", "USER").and().withUser("user").password("user")
                    .roles("USER");
            // @formatter:on
        }
    }

    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http.authorizeRequests().antMatchers("/login").permitAll().anyRequest()
                    .fullyAuthenticated().and().formLogin().loginPage("/login")
                    .failureUrl("/login?error").and().logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).and()
                    .exceptionHandling().accessDeniedPage("/access?error");
            // @formatter:on
        }

    }

}
