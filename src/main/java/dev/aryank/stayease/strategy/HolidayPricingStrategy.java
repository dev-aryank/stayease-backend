package dev.aryank.stayease.strategy;


import dev.aryank.stayease.entity.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class HolidayPricingStrategy implements PricingStrategy {

    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);
        boolean isHoliday = true; // call an API or check with local date

        if(isHoliday){
            price = price.multiply(BigDecimal.valueOf(1.25));
        }

        return price;
    }
}
