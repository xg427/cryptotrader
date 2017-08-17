package com.after_sunrise.cryptocurrency.cryptotrader.core;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;
import java.util.Set;

/**
 * @author takanori.takase
 * @version 0.0.1
 */
public interface PropertyManager extends Environment {

    Boolean getTradingActive();

    Map<String, Set<String>> getTradingTargets();

    Duration getTradingInterval();

    BigDecimal getTradingSpread();

    BigDecimal getTradingExposure();

    BigDecimal getTradingSplit();

    BigDecimal getTradingAggressiveness();

}
