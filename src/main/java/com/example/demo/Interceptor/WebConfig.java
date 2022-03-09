package com.example.demo.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private SessionInterceptor sessionInterceptor;


//    @Value("${file.staticPath}")
//    private  String     staticPatternPath;
//    @Value("${file.uploadFolder}")
    private  String    uploadFolder;
    //还可以通过extends WebMvcConfigurationSupport
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    //配置资源映射路径
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/templates/")
//                .addResourceLocations("classpath:/static/");
//    }
//    @Override
//    public void addResource(ResourceRegistry registry) {
//        registry.addResourceHandler(sessionInterceptor)
//                .addResourceHandler("/**")
//                .addResourceLocations("classpath:/templates/")
//                .addResourceLocations("classpath:/static/");
//    }

//    //配置资源映射路径
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler(staticPatternPath)
////                .addResourceLocations("file:"+uploadFolder);
//
//        registry.addResourceHandler("/uploading/**")
//                .addResourceLocations("file:D://image//");
//
//    }

    //原本的资源
    @Override
    public  void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**");
    }


}
//@Configuration
//public class WebAppConfingurer implements WebMvcConfigurer {
//    //还可以通过extends WebMvcConfigurationSupport
//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer.setUseSuffixPatternMatch(false);
//    }
//
//    //配置资源映射路径
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/templates/")
//                .addResourceLocations("classpath:/static/");
//    }
//}