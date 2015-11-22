Dev-in-Box for dynamic-keystore
==================================

This folder will allow you to setup the dev-in-box for this service module.

### Contents
 - dynamodb/Dockerfile: Dockerfile to bring up dynamoDB local container.
 - docker-compose: one place to run multiple docker containers
 - KeystoreDto.sh: script to generate the dynamoDB schema
 - Vagrantfile: Developer's life simplified
 
### Usage
Run this first

```bash
  $ vagrant up
```

Once vagrant boots up successfully execute the KeystoreDto.sh

```bash
  $ ./KeystoreDto.sh
```

You can verify that the DynamoDB schema has been created successfully using the awscli

```bash
  $ aws dynamodb list-tables --table-name Keystore --endpoint http://localhost:8080
```

Once done

```bash
  $ vagrant destroy
```