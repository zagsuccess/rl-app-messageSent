package com.uhope.template.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.uhope.base.constants.Constant;
import com.uhope.template.filter.AddTokenFilter;
import com.uhope.template.filter.ServiceFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;

/**
 * Spring MVC 配置
 *
 * @author ChenBin on 2018/09/04
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 当前激活的配置文件
     */
    @Value("${spring.profiles.active}")
    private String env;

    /**
     * 解决路径资源映射问题
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 使用fastJson代替Jackjson解析JSON数据
     *
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        /*
         * 转换为JSON字符串，默认:
         * WriteNullListAsEmpty    List字段如果为null,输出为[],而非null
         * WriteNullStringAsEmpty  字符类型字段如果为null,输出为”“,而非null
         * WriteMapNullValue       是否输出值为null的字段,默认为false
         */
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        fastConverter.setDefaultCharset(Charset.forName(Constant.DEFAULT_CHARSET));
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }

    /**
     * 这个Filter 解决页面跨域访问问题
     */
    @Bean
    public FilterRegistrationBean omsFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ServiceFilter());
        registration.addUrlPatterns("/*");
        registration.setName("MainFilter");
        registration.setAsyncSupported(true);
        registration.setOrder(1);
        return registration;
    }

    /**
     * 这个Filter 往request中添加token
     */
    @Bean
    public FilterRegistrationBean addTokenFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AddTokenFilter(env));
        registration.addUrlPatterns("/*");
        registration.setName("addTokenFilter");
        registration.setAsyncSupported(true);
        registration.setOrder(2);
        return registration;
    }

}