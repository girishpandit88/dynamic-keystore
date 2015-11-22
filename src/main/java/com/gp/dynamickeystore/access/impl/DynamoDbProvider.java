package com.gp.dynamickeystore.access.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DynamoDbProvider implements Provider<DynamoDB> {
	private final AmazonDynamoDBClient client;

	@Inject
	public DynamoDbProvider(AmazonDynamoDBClient client) {
		this.client = client;
	}

	public DynamoDB get() {
		return new DynamoDB(this.client);
	}
}
