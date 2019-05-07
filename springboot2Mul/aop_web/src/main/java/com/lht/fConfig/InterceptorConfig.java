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
   private List<String> excludeUrls;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(new InterceptorMyA()).addPathPatterns(urls).excludePathPatterns(excludeUrls);

      registry.addInterceptor(new InterceptorMyB()).addPathPatterns(urls).excludePathPatterns(excludeUrls);

   }
}
