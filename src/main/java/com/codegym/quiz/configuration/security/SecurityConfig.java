package com.codegym.quiz.configuration.security;

import com.codegym.quiz.configuration.customConfig.CustomAccessDeniedHandler;
import com.codegym.quiz.configuration.customConfig.RestAuthenticationEntryPoint;
import com.codegym.quiz.configuration.filter.JwtAuthenticationFilter;
import com.codegym.quiz.service.UserService;
import com.codegym.quiz.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.codegym.quiz.model.StaticVariable.LOGIN;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Autowired
    private UserService userService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/**");
        http.httpBasic().authenticationEntryPoint(restServicesEntryPoint());
        http.authorizeRequests()
                .antMatchers("/",
                        LOGIN,
                        "/register",
                        "/confirm-account/**",
                        "/forgot-password",
                        "/new-password/**",
                        "/users/**",
                        "/findAllQuestionByQuiz",
                        "/findAllQuestionByQuizNull",
                        "/findAllQuestionByContent",
                        "/findAllQuestionByContentContainingAndCategory",
                        "/findAllQuestionByContentContainingAndTypeOfQuestion",
                        "/findAllQuestionByQuizIsNullAndContentContaining",
                        "/findAllQuestionByQuizIsNullAndCategory",
                        "/findAllQuestionByQuizIsNullAndTypeOfQuestion",
                        "/findAllQuestionByQuizIsNullAndCategoryAndTypeOfQuestion",
                        "/findAllQuestionByQuizIsNullAndContentContainingAndCategory",
                        "/findAllQuestionByQuizIsNullAndContentContainingAndTypeOfQuestion",
                        "/findAllQuestionByQuizIsNullAndContentContainingAndCategoryAndTypeOfQuestion").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/categories",
                        "/typeOfQuestions",
                        "/questions",
                        "/answers",
                        "/quizzes",
                        "/exams",
                        "/results",
                        "/classes")
                .access("hasRole('USER') or hasRole('ADMIN') or hasRole('TUTOR')")
                .antMatchers(HttpMethod.POST, "/categories",
                        "/typeOfQuestions",
                        "/questions",
                        "/answers",
                        "/quizzes").access("hasRole('TUTOR')")
                .antMatchers(HttpMethod.POST,
                        "/exams",
                        "/classes")
                .access("hasRole('ADMIN')")
                .antMatchers(HttpMethod.PUT, "/categories",
                        "/typeOfQuestions",
                        "/questions",
                        "/answers",
                        "/quizzes").access("hasRole('TUTOR')")
                .antMatchers(HttpMethod.PUT,
                        "/exams",
                        "/classes")
                .access("hasRole('ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/categories",
                        "/typeOfQuestions",
                        "/questions",
                        "/answers",
                        "/quizzes").access("hasRole('TUTOR')")
                .antMatchers(HttpMethod.DELETE,
                        "/exams",
                        "/classes")
                .access("hasRole('ADMIN')")
                .antMatchers(HttpMethod.PUT, "/users")
                .access("hasRole('USER')")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
    }
}
