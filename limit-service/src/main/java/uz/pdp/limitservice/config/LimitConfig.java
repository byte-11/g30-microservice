package uz.pdp.limitservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(value = "limit-service")
public class LimitConfig {
    private int minimum;
    private int maximum;
}
