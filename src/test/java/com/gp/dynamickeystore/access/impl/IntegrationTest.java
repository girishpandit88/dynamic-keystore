package com.gp.dynamickeystore.access.impl;

public interface IntegrationTest {
    public default String getPort(){
        return System.getProperty("dynamodb.port");
    }
}
