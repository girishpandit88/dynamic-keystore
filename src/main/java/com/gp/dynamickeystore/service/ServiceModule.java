package com.gp.dynamickeystore.service;

import com.gp.dynamickeystore.service.impl.ConfigServiceImpl;
import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(ConfigService.class).to(ConfigServiceImpl.class).asEagerSingleton();
	}
}
