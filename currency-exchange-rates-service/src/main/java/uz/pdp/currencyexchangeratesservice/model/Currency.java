package uz.pdp.currencyexchangeratesservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@Getter
@Setter
@Builder
public class Currency {
    @Builder.Default
    private Long id = new Random().nextLong(1000, 10000);
    private String title;
    private String code;
    private BigDecimal exchangeValue;
    private BigDecimal salesValue;
    private String environment;
    @Builder.Default
    private Date date = new Date();
}
