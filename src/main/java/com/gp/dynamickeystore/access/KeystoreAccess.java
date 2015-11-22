package com.gp.dynamickeystore.access;

import com.gp.dynamickeystore.access.models.KeystoreDto;
import com.typesafe.config.Config;

import java.util.List;
import java.util.Optional;

public interface KeystoreAccess {
	/**
	 *
	 * @param hashKey: HashIndex for the table item to be retrieved
	 * @param rangeKey: Range attribute for the table item to be retrieved
	 * @return Optional containing the value of the item if retrieved or empty Optional
	 */
	Optional<String> getConfig(String hashKey, String rangeKey);

	/**
	 *
	 * @param hashKey: HashIndex for the table item to be retrieved
	 * @return Optional containing a Config object which contains a map of {configName:configValue}
	 */
	Optional<Config> getConfigs(String hashKey);

	/**
	 *
	 * @param keyStores: List of KeystoreDTO objects to be batch saved to dynamo
	 */
	void putConfig(List<KeystoreDto> keyStores);

	/**
	 *
	 * @param hashKey: HashIndex for the table item to be deleted
	 * @param rangeKey Range attribute for the table item to be deleted
	 */
	void deleteConfig(String hashKey, String rangeKey);

}
