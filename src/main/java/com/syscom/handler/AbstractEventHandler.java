package com.syscom.handler;

import java.time.Duration;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEventHandler<T> {

	private final Logger logger = LoggerFactory.getLogger(AbstractEventHandler.class);

	public void handleEvent(T event) {
		LocalDateTime start = LocalDateTime.now();
		logger.info("Receive event kafka : {} {}", event, event.getClass());
		processEvent(event);
		LocalDateTime end = LocalDateTime.now();
		logger.info("End treatment of the event {} {} (duration {} ms) ", event, event.getClass(),
				Duration.between(start, end).getNano() / 1000000);

	}

	public abstract void processEvent(T event);

}
