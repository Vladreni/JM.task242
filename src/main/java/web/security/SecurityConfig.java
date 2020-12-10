package web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import web.service.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private  UserService userDetailsService;
                // сервис, с помощью которого тащим пользователя из БД
    private  LoginSuccessHandler loginSuccessHandler;
                // класс, в котором описана логика перенаправления пользователей по ролям

    @Autowired
    public SecurityConfig(UserService userDetailsService,
                          LoginSuccessHandler loginSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    //    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("ADMIN").password("ADMIN").roles("ADMIN")
//                .and()
//                .withUser("USER").password("USER").roles("USER");
//    }
    @Autowired
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

        @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.csrf().disable(); - попробуйте выяснить сами, что это даёт
        http.authorizeRequests()
                .antMatchers("/").permitAll() // доступность всем
                .antMatchers("/login").anonymous()
                .antMatchers("/admin").access("hasAnyRole('ROLE_ADMIN')")
                       // разрешаем входить на /user пользователям с ролью User
                .and().formLogin()  // Spring сам подставит свою логин форму
                .successHandler(loginSuccessHandler); // подключаем наш SuccessHandler для перенеправления по ролям
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                //страницы аутентификаци доступна всем
//            .antMatchers("/").permitAll()   //.anonymous()
//                // защищенные URL
//            //.antMatchers("/admin/{id}").access("hasAnyRole('ROLE_USER')")
//                //разрешаем входить на /user пользователям с ролью User
//            .antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN')")
//                //Все остальные страницы требуют аутентификации
//            //.anyRequest().authenticated();
//
//        //http
//            .and()    .formLogin()
//                // указываем страницу с формой логина
//                //.loginPage("/hello/login")
//                //указываем логику обработки при логине
//                .successHandler(loginSuccessHandler);
//        // указываем action с формы логина
//        //.loginProcessingUrl("hello/login")
//        // Указываем параметры логина и пароля с формы логина
//        //.usernameParameter("j_username")
//        //.passwordParameter("j_password")
//        // даем доступ к форме логина всем
//        //.permitAll();
//
////        http.logout()
////                // разрешаем делать логаут всем
////                .permitAll()
////                // указываем URL логаута
////                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////                // указываем URL при удачном логауте
////                .logoutSuccessUrl("/login?logout")
////                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
////                .and().csrf().disable();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
