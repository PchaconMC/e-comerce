package com.ecomerce.gateway.filter;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * 
 * @author pchacon
 *
 */
@Component
public class GlobalFilter implements org.springframework.cloud.gateway.filter.GlobalFilter, Ordered {

	private final Logger logger = LoggerFactory.getLogger(GlobalFilter.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("Ejecutando pre-filtro");
		exchange.getRequest().mutate().headers(h->h.add("token","1234567890"));
		
		return chain.filter(exchange).then(Mono.fromRunnable(()->{
			logger.info("Ejecutando post-filtro");
			Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent(valor->{
				exchange.getResponse().getHeaders().add("token", valor);
			});
			
			exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "rojo").build());
			//exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
		}));
	}

	@Override
	public int getOrder() {
		return 2;
	}

}
