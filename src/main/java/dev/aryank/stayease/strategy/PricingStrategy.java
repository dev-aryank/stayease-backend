package dev.aryank.stayease.strategy;

import dev.aryank.stayease.entity.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

public interface PricingStrategy {



    BigDecimal calculatePrice(Inventory inventory);
}
