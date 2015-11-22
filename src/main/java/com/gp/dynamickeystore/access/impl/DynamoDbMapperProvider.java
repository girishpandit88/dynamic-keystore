package com.gp.dynamickeystore.access.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class DynamoDbMapperProvider implements Provider<DynamoDBMapper> {

	private final AmazonDynamoDB client;

	private final String tablePrefix;

	@Inject
	public DynamoDbMapperProvider(final AmazonDynamoDBAsyncClient client, @Named("dynamo.tablePrefix") final String tablePrefix) {
		super();
		this.client = client;
		this.tablePrefix = tablePrefix;
	}

	@Override
	public DynamoDBMapper get() {
		final DynamoDBMapperConfig config = new DynamoDBMapperConfig(DynamoDBMapperConfig.TableNameOverride.withTableNamePrefix(this.tablePrefix));
		return new DynamoDBMapper(this.client, config);
	}

}
