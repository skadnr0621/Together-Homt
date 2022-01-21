package com.ssafy.togetherhomt.config.articleConfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private GlobalConfig config;

    @Bean
    public GlobalConfig config(){
        return new GlobalConfig();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String resourcePattern = config.getUploadResourcePath()+"**";
        registry.addResourceHandler(resourcePattern)
                .addResourceLocations("file:///"+ config.getUploadFilePath());
        System.out.println("resourcePattern = " + resourcePattern);

    }
}

