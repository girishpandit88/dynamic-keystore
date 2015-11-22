package com.gp.dynamickeystore.access.impl;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.util.StringUtils;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class AmazonDynamoDBAsyncClientProvider implements Provider<AmazonDynamoDBAsyncClient> {
	private final String dynamoEndpoint;

	@Inject
	public AmazonDynamoDBAsyncClientProvider(@Named("dynamo.endpoint") String dynamoEndpoint) {
		this.dynamoEndpoint = dynamoEndpoint;
	}

	@Override
	public AmazonDynamoDBAsyncClient get() {
		AmazonDynamoDBAsyncClient client = new AmazonDynamoDBAsyncClient(new DefaultAWSCredentialsProviderChain());
		if (!StringUtils.isNullOrEmpty(this.dynamoEndpoint)) {
			client.setEndpoint(this.dynamoEndpoint);
		}

		return client;
	}
}

