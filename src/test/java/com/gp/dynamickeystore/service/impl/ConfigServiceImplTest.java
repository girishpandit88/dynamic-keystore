package com.gp.dynamickeystore.service.impl;

import com.gp.dynamickeystore.access.KeystoreAccess;
import com.typesafe.config.ConfigFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ConfigServiceImplTest {

	@Mock
	KeystoreAccess keystoreAccess;
	private ConfigServiceImpl _service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		_service = new ConfigServiceImpl(this.keystoreAccess);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testGetConfig() throws Exception {
		when(keystoreAccess.getConfig(Mockito.anyString(),
			Mockito.anyString())).thenReturn(Optional.of("mocked value"));
		assertEquals("mocked value",_service.getConfig("abc","def","user").get());
	}

	@Test
	public void testSetConfig() throws Exception {
		doNothing().when(keystoreAccess).putConfig(Mockito.anyList());
		_service.setConfig("test","env","user","dba");
	}

	@Test
	public void testGetConfigs() throws Exception {
		when(keystoreAccess.getConfigs(Mockito.anyString()))
			.thenReturn(Optional.of(ConfigFactory.load()));
		assertTrue(_service.getConfigs("test","test").get().hasPath("dynamo"));
	}

	@Test
	public void testDeleteConfig() throws Exception {
		doNothing().when(keystoreAccess).deleteConfig(Mockito.anyString(),Mockito.anyString());
		_service.deleteConfig("test","test","user");
	}
}
