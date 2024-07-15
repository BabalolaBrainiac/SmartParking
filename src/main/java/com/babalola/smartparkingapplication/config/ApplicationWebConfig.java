package com.babalola.smartparkingapplication.config;

import com.babalola.smartparkingapplication.interceptors.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationWebConfig implements WebMvcConfigurer {

    private final LoggingInterceptor loggerInterceptor;

    public ApplicationWebConfig(LoggingInterceptor loggerInterceptor) {
        this.loggerInterceptor = loggerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggerInterceptor);
    }
}
