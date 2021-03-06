package com.after_sunrise.cryptocurrency.cryptotrader.framework.impl;

import com.after_sunrise.cryptocurrency.cryptotrader.core.ServiceFactory;
import com.after_sunrise.cryptocurrency.cryptotrader.framework.Adviser;
import com.after_sunrise.cryptocurrency.cryptotrader.framework.Context;
import com.after_sunrise.cryptocurrency.cryptotrader.framework.Estimator.Estimation;
import com.after_sunrise.cryptocurrency.cryptotrader.framework.Request;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.util.Map;

/**
 * @author takanori.takase
 * @version 0.0.1
 */
public class AdviserImpl extends AbstractService implements Adviser {

    private final Advice BAIL = Advice.builder().build();

    private final Map<String, Adviser> advisers;

    @Inject
    public AdviserImpl(Injector injector) {

        this.advisers = injector.getInstance(ServiceFactory.class).loadMap(Adviser.class);

    }

    @Override
    public String get() {
        return WILDCARD;
    }

    @Override
    public Advice advise(Context context, Request request, Estimation estimation) {

        Adviser adviser = advisers.get(request.getSite());

        if (adviser == null) {

            log.debug("Service not found : {}", request.getSite());

            return BAIL;

        }

        Advice advice = adviser.advise(context, request, estimation);

        log.info("Advice : [{}.{}] {}", request.getSite(), request.getInstrument(), advice);

        return trim(advice, BAIL);

    }

}
