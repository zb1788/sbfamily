package com.boz.file.configread.properties.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author boz
 * @date 2019/7/10
 */

@Component
@ConfigurationProperties(prefix = "author")
@Data
public class Author {
    private String name;
    private Integer age;
}
