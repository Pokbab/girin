package com.cdg.girin.config;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .formLogin()
//                .loginPage("/user/login")
//            .and()
//                .logout()
//                    .logoutUrl("/user/logout")
//                    .deleteCookies("JSESSIONID")
//                    .logoutSuccessUrl("/post/list")
//            .and()
//                .authorizeRequests()
//                    .antMatchers("/**/write*", "/**/edit*", "/**/delete*").authenticated()
//                    .antMatchers("/**").permitAll();
//    }
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/console/**");
//    }
//}