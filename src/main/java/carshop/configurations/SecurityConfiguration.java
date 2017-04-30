package carshop.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/private/**").hasRole("USER")
                .and()
                    .formLogin() .defaultSuccessUrl("/home.html")
        .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .logout()
                .logoutSuccessUrl("/");
                //.loginPage("/login.html")
                //.loginProcessingUrl("/perform_login")
                //.defaultSuccessUrl("/index.html",true)
                //.failureUrl("/login.html?error=true");

        /*
         http.formLogin()
      .loginPage("/login.html")
      .loginProcessingUrl("/perform_login")
      .defaultSuccessUrl("/homepage.html",true)
      .failureUrl("/login.html?error=true")
         */
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*
        auth.inMemoryAuthentication()
                    .withUser("niki").password("nik2098").roles("USER")
                .and()
                    .withUser("admin").password("admin2098").roles("USER", "ADMIN");
     */

        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }
}
