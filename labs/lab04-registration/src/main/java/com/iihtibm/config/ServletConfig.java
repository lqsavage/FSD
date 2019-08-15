package com.iihtibm.config;

import com.iihtibm.convert.CustomDateConverter;
import com.iihtibm.exception.NoAuthExceptionResolver;
import com.iihtibm.interceptors.MyFirstInterceptor;
import com.iihtibm.interceptors.MySecondInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * @author : heibaiying
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.iihtibm.controller"})
public class ServletConfig implements WebMvcConfigurer {

    /**
     * 配置视图解析器
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setExposeContextBeansAsAttributes(true);
        return internalResourceViewResolver;
    }

    /**
     * 配置静态资源处理器
     */
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 添加自定义拦截器
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyFirstInterceptor()).addPathPatterns("/mvc/**").excludePathPatterns("mvc/login");
        registry.addInterceptor(new MySecondInterceptor()).addPathPatterns("/mvc/**");
    }


    /**
     * 添加全局异常处理器
     */
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new NoAuthExceptionResolver());
    }

    /**
     * 添加全局日期处理
     */
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CustomDateConverter());
    }

    /**
     * 配置文件上传
     */
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(1024*1000*10);
        resolver.setMaxUploadSizePerFile(1024*1000);
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }
}
