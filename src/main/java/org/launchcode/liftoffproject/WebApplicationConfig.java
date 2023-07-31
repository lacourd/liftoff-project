package org.launchcode.liftoffproject;


import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Parent;
import org.launchcode.liftoffproject.services.ChildService;
import org.launchcode.liftoffproject.services.ChildServiceImpl;
import org.launchcode.liftoffproject.services.ParentService;
import org.launchcode.liftoffproject.services.ParentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {

    // Create spring-managed object to allow the app to access our filter
    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }

    // Create spring-managed object for PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Create spring-managed object for ParentServiceImpl
    @Bean
    public ParentService parentService() {
        return new ParentServiceImpl() {
            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }

            @Override
            public Parent getParentByUsername(String username) {
                return null;
            }

            @Override
            public Parent getParentById(Integer parentId) {
                return null;
            }
        };
    }

    @Bean
    public ChildService childService() {
        return new ChildServiceImpl() {
            @Override
            public List<Child> getAllChildren() {
                return null;
            }

            @Override
            public Child getChildById(int id) {
                return null;
            }

            @Override
            public void addChild(Child child) {

            }

            @Override
            public void updateChild(Child child) {

            }

            @Override
            public void deleteChild(int id) {

            }

            @Override
            public List<Child> getChildrenByParent(Parent parent) {
                return null;
            }

            @Override
            public void registerChild(Child newChild) {

            }

            @Override
            public Object getEarnedRewards(String username) {
                return null;
            }

            @Override
            public Object getChores(String username) {
                return null;
            }

            @Override
            public Child getChildByUsername(String username) {
                return null;
            }

        };
    }



    // Register the filter, PasswordEncoder, and ParentServiceImpl with the Spring container
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationFilter());
    }

}
