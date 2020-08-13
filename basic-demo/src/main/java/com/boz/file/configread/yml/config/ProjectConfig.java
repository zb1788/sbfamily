package com.boz.file.configread.yml.config;

import com.boz.file.configread.yml.entity.Project;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author boz
 * @date 2019/7/10
 */
@Configuration
public class ProjectConfig {

    @Bean
    @ConfigurationProperties("boz")
    public Project project(){
        return new Project();
    }
}
