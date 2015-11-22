package com.gp.dynamickeystore.access;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.gp.dynamickeystore.access.impl.AmazonDynamoDBAsyncClientProvider;
import com.gp.dynamickeystore.access.impl.DynamoDbMapperProvider;
import com.gp.dynamickeystore.access.impl.DynamoDbProvider;
import com.gp.dynamickeystore.access.impl.KeystoreAccessImpl;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class DynamicAccessModule extends AbstractModule {
	private final String endpoint;
	private final String tablePrefix;
	public DynamicAccessModule(String endpoint, String tablePrefix) {
		this.endpoint = endpoint;
		this.tablePrefix = tablePrefix;
	}


	@Override
	protected void configure() {
			this.bindConstant().annotatedWith(Names.named("dynamo.endpoint")).to(this.endpoint);
			this.bindConstant().annotatedWith(Names.named("dynamo.tablePrefix")).to(this.tablePrefix);
			this.bind(AmazonDynamoDBAsyncClient.class).toProvider(AmazonDynamoDBAsyncClientProvider.class).asEagerSingleton();
			this.bind(DynamoDB.class).toProvider(DynamoDbProvider.class).asEagerSingleton();
			this.bind(KeystoreAccess.class).to(KeystoreAccessImpl.class).asEagerSingleton();
			this.bind(DynamoDBMapper.class).toProvider(DynamoDbMapperProvider.class).asEagerSingleton();
		}
}
