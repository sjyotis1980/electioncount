/**
 * 
 */
package com.jyoti.ElectionCount.config;






import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


/**
 * @author JyotiKumar
 *
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class BasicConfiguration extends WebSecurityConfigurerAdapter {
	
	
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("user").password("pwd").roles("USER")
		                                                                                .and().withUser("admin").password("admin").roles("USER", "ADMIN");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.httpBasic().and().authorizeRequests().antMatchers("/api/**").hasAnyRole("USER")
		                                          .antMatchers("/**").hasRole("ADMIN").and().csrf().disable()
		                                          .headers().frameOptions().disable();
	}

}
