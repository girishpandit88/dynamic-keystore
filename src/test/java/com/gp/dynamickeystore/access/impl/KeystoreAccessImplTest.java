package com.gp.dynamickeystore.access.impl;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.gp.dynamickeystore.access.KeystoreAccess;
import com.gp.dynamickeystore.access.models.KeystoreDto;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static junit.framework.Assert.assertEquals;

public class KeystoreAccessImplTest {
	private static final Config config = ConfigFactory.load();
	private static KeystoreAccess keystoreAccess;
	@BeforeClass
	public static void setUp() throws Exception {
		AmazonDynamoDBAsyncClient client = new AmazonDynamoDBAsyncClient(
			new DefaultAWSCredentialsProviderChain()).withEndpoint(config.getString("dynamo.endpoint"));

		DynamoDBMapper _mapper = new DynamoDbMapperProvider(client,config.getString("dynamo.tablePrefix")).get();
		keystoreAccess = new KeystoreAccessImpl(_mapper);
	}

	@AfterClass
	public static void tearDown() throws Exception{
		keystoreAccess.deleteConfig("test.test","user");
		keystoreAccess.deleteConfig("test1.test","user");
		keystoreAccess.deleteConfig("test2.test","user");
		assertEquals(Optional.empty(),keystoreAccess.getConfig("test.test","user"));
	}

	@Test
	public void testDeleteConfig() throws Exception{
		keystoreAccess.putConfig(Arrays.asList(new KeystoreDto("test.test", "user", "xyz")));
		keystoreAccess.deleteConfig("test3.test","user");
	}

	@Test
	public void testGetConfig() throws Exception {
		keystoreAccess.putConfig(Arrays.asList(new KeystoreDto("test.test", "user", "xyz")));
		assertEquals("xyz", keystoreAccess.getConfig("test.test", "user").get());
		assertEquals(Optional.empty(),keystoreAccess.getConfig("test13.test","user"));
	}

	@Test
	public void testGetConfigs() throws Exception {
		keystoreAccess.putConfig(Arrays.asList(new KeystoreDto("test1.test", "user", "xyz")));
		assertEquals("xyz",keystoreAccess.getConfigs("test1.test").get().getString("user"));
	}

	@Test
	public void testPutConfig() throws Exception {
		keystoreAccess.putConfig(Arrays.asList(new KeystoreDto("test2.test", "user", "xyz")));
		assertEquals("xyz", keystoreAccess.getConfig("test2.test", "user").get());
	}
}
