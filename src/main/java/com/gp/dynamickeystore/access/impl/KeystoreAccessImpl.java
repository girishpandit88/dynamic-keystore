package com.gp.dynamickeystore.access.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.gp.dynamickeystore.access.KeystoreAccess;
import com.gp.dynamickeystore.access.models.KeystoreDto;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
@Slf4j
public class KeystoreAccessImpl implements KeystoreAccess {
	private final DynamoDBMapper dynamoDBMapper;

	@Inject
	public KeystoreAccessImpl(DynamoDBMapper dynamoDBMapper) {
		this.dynamoDBMapper = dynamoDBMapper;
	}

	@Override
	public Optional<String> getConfig(String hashKey, String rangeKey) {
		final KeystoreDto keystoreDto = dynamoDBMapper.load(KeystoreDto.class, hashKey, rangeKey);
		return keystoreDto != null ? Optional.of(keystoreDto.getValue()) : Optional.empty();

	}

	@Override
	public Optional<Config> getConfigs(String hashKey) {
		List<KeystoreDto> keystoreDtos = dynamoDBMapper.query(
			KeystoreDto.class,new DynamoDBQueryExpression<KeystoreDto>()
				.withHashKeyValues(new KeystoreDto().withId(hashKey)));
		if(keystoreDtos ==null || keystoreDtos.size()<0){
			return Optional.empty();
		} else {
			final Map<String, String> configMap = keystoreDtos.stream()
				.collect(Collectors.toMap(KeystoreDto::getKey, KeystoreDto::getValue));
			return Optional.of(ConfigFactory.parseMap(configMap));
		}
	}

	@Override
	public void putConfig(List<KeystoreDto> keyStores) {
		final List<DynamoDBMapper.FailedBatch> failedBatches = dynamoDBMapper.batchSave(keyStores);
		failedBatches.forEach(f->log.warn("Exception occurred while batch save {}",f.getException()));
	}

	@Override
	public void deleteConfig(String hashKey, String rangeKey) {
		dynamoDBMapper.delete(new KeystoreDto().withId(hashKey).withKey(rangeKey));
	}
}
