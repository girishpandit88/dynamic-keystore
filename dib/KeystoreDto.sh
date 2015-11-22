#!/usr/bin/env bash
aws dynamodb create-table --table-name Keystore --attribute-definitions AttributeName=id,AttributeType=S AttributeName=key,AttributeType=S --key-schema AttributeName=id,KeyType=HASH AttributeName=key,KeyType=RANGE --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 --endpoint-url http://localhost:8000
