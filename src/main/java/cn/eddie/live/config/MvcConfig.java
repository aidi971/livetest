package cn.eddie.live.config;

import cn.eddie.live.dao.LiveDao;
import cn.eddie.live.dao.StudentDao;
import cn.eddie.live.dao.TeacherDao;
import cn.eddie.live.interceptor.LiveCountInterceptor;
import cn.eddie.live.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig {
    @Autowired
    private LiveDao liveDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginInterceptor())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/login", "/register/**", "/css/**", "/js/**", "/img/**");
                registry.addInterceptor(new LiveCountInterceptor(liveDao, studentDao, teacherDao))
                        .addPathPatterns("/**")
                        .excludePathPatterns("/login", "/register/**", "/css/**", "/js/**", "/img/**");
            }
        };
    }
}
