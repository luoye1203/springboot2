package com.lht.fConfig;

import com.lht.register.DynamicDataSourceRegister;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;


/**
 * lht
 * 2019-4-19 17:24:00
 *
 */

@Configuration
@Import(DynamicDataSourceRegister.class)
public class ManualRegisterBeanConfig {

    //导入自定义的yaml文件
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("otherConfig.yaml"));
        configurer.setProperties(yaml.getObject());
        return configurer;
    }

}
