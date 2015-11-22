package com.gp.dynamickeystore.service;

import com.typesafe.config.Config;

import java.util.Optional;

public interface ConfigService {
	/**
	 *
	 * @param serviceName: Service name for which config property needs to be retrieved
	 * @param environment: Service environment for which config property needs to be retrieved
	 * @param key: Name of the config property
	 * @return Optional containing the value of the config property if property is retrieved or empty Optional
	 */
	Optional<String> getConfig(String serviceName, String environment, String key);

	/**
	 *
	 * @param serviceName: Service name for which config property needs to be set
	 * @param environment: Service environment for which config property needs to be set
	 * @param key: Name of the config property@param key
	 * @param value Value of above config property
	 */
	void setConfig(String serviceName, String environment, String key, String value);

	/**
	 *
	 * @param serviceName: Service name for which config properties needs to be retrieved
	 * @param environment: Serivce environment for which config properties needs to be retrieved
	 * @return Optional containing Config object if properties are retrieved or empty Optional
	 */
	Optional<Config> getConfigs(String serviceName, String environment);

	/**
	 *
	 * @param serviceName: Service name for which config properties needs to be deleted
	 * @param environment: Service environment for which config properties needs to be deleted
	 * @param key: Config property to be deleted
	 */
	void deleteConfig(String serviceName, String environment, String key);
}
