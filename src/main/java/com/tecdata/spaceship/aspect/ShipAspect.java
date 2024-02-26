package com.tecdata.spaceship.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ShipAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShipAspect.class);

    @Before("execution(* com.tecdata.spaceship.services.ShipService.getShip(int)) && args(id)")
    public void logNegativeIdRequest(JoinPoint joinPoint, int id) {
        if (id < 0) {
            LOGGER.info("Solicitud de nave con ID negativo: {}", id);
        }
    }
    
    public ShipAspect() {
        LOGGER.info("Aspecto creado");
    }
}
