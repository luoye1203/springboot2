package com.lht.config;

import com.lht.register.DynamicDataSourceRegister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * lht
 * 2019-4-19 17:24:00
 *
 */

@Configuration
@Import(DynamicDataSourceRegister.class)
public class ManualRegisterBeanConfig {



}
