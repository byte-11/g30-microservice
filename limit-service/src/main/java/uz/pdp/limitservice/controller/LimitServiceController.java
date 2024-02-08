package uz.pdp.limitservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.limitservice.config.LimitConfig;
import uz.pdp.limitservice.model.Limit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/limits")
public class LimitServiceController {

    private final Environment environment;
    private final LimitConfig limitConfig;

    @GetMapping
    public Limit getLimitConfig() {
        return new Limit(
                limitConfig.getMinimum(),
                limitConfig.getMaximum(),
                environment.getProperty("limit-service.attempt", Integer.class, -1)
                );
    }
}
