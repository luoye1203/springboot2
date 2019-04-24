package com.lht.fConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("interceptor")
@Data
public class InterceptorConfig {
   private List<String> urls;
}
