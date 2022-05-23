package be.vdab.muziek.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select naam as username, paswoord as password, true as enabled from gebruikers where naam = ?")
                .authoritiesByUsernameQuery("select naam as username, id as authorities from gebruikers where naam = ?");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .mvcMatchers("/images/**")
                .mvcMatchers("/css/**")
                .mvcMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin(login -> login.loginPage("/login"));
        http.logout(logout -> logout.logoutSuccessUrl("/"));
        http.authorizeRequests(requests -> requests
                .mvcMatchers("/", "/login").permitAll()
                .mvcMatchers("/album/1", "/album/2", "/album/3", "/album/4", "/album/5", "/album/6", "/album/7", "/album/8", "/album/9", "/album/10", "/album/11", "/album/12", "/album/13").hasAuthority("1")
                .mvcMatchers("/album/14", "/album/15", "/album/16", "/album/17", "/album/18", "/album/19", "/album/20", "/album/21", "/album/22", "/album/23").hasAuthority("2"));
    }
}
