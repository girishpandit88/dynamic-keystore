package com.gp.dynamickeystore.service.impl;

import com.gp.dynamickeystore.access.KeystoreAccess;
import com.gp.dynamickeystore.access.models.KeystoreDto;
import com.gp.dynamickeystore.service.ConfigService;
import com.google.inject.Inject;
import com.typesafe.config.Config;

import java.util.Arrays;
import java.util.Optional;

public class ConfigServiceImpl implements ConfigService {
	private final KeystoreAccess _access;

	@Inject
	public ConfigServiceImpl(KeystoreAccess keystoreAccess) {
		_access = keystoreAccess;
	}

	@Override
	public Optional<String> getConfig(String serviceName, String environment, String key) {
		return _access.getConfig(serviceName + "." + environment, key);
	}

	@Override
	public void setConfig(String serviceName, String environment, String key, String value) {
		_access.putConfig(
			Arrays.asList(
				new KeystoreDto().withId(serviceName + "." + environment).withKey(key).withValue(value)
			));
	}

	@Override
	public Optional<Config> getConfigs(String serviceName, String environment) {
		return _access.getConfigs(serviceName+"."+environment);
	}

	@Override
	public void deleteConfig(String serviceName, String environment, String key) {
		_access.deleteConfig(serviceName+"."+environment,key);
	}
}
