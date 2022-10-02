package com.ecomerce.gateway.filter.factory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import lombok.Data;
import reactor.core.publisher.Mono;
/**
 * 
 * @author pchacon
 *
 */
@Component
public class PriceGatewayFilterFactory extends AbstractGatewayFilterFactory<PriceGatewayFilterFactory.Config> {
	private final Logger logger = LoggerFactory.getLogger(PriceGatewayFilterFactory.class);

	
	
	public PriceGatewayFilterFactory() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return new OrderedGatewayFilter((exchange,chain)->{
			logger.info("Ejecutando pre gateway filter factory: " + config.message);
			return chain.filter(exchange).then(Mono.fromRunnable(()->{
				
				Optional.ofNullable(config.cookieVal).ifPresent(cookie->{
					exchange.getResponse().addCookie(ResponseCookie.from(config.cookieName, cookie).build());
				});
				logger.info("Ejecutando post gateway filter factory: " + config.message);
			}));
		}, 1);
	}

	@Override
	public List<String> shortcutFieldOrder() {
		return Arrays.asList("message","cookieName","cookieVal");
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "PriceFilter";
	}

	@Data
	public static class Config {
		private String message;
		private String cookieVal;
		private String cookieName;
	}


}
