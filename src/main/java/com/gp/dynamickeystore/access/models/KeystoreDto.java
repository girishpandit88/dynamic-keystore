package com.gp.dynamickeystore.access.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Wither
@DynamoDBTable(tableName = "Keystore")
public class KeystoreDto {
	private String id;
	private String key;
	private String value;

	@DynamoDBHashKey
	public String getId() {
		return this.id;
	}

	@DynamoDBRangeKey
	public String getKey() {
		return this.key;
	}
}
