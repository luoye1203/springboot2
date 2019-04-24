package com.lht.fConfig;

import com.lht.eInterceptor.InterceptorMyA;
import com.lht.eInterceptor.InterceptorMyB;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Component
@ConfigurationProperties("interceptor")
@Data
public class InterceptorConfig implements WebMvcConfigurer {
   private List<String> urls;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      //        String[] urls1={"/interceptor/*"};
      registry.addInterceptor(new InterceptorMyA()).addPathPatterns(urls.toArray(new String[urls.size()]));
//        registry.addInterceptor(new InterceptorMyA()).addPathPatterns(urls);
      registry.addInterceptor(new InterceptorMyB()).addPathPatterns(urls.toArray(new String[urls.size()]));
//        registry.addInterceptor(new InterceptorMyB()).addPathPatterns(urls);

   }
}
