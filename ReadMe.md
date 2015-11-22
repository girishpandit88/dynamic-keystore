dynamic-keystore
====================

A service module that you can include in your services. It provides you with ways to dynamically pull your application/service configs from DynamoDB. Advantage over other similar services is that you manage your own configurations. Other advantage being, you can pretty much store your entire config in DynamoDB using this module. So your applications/services can be configuration free.

To get started

```
$ git clone
$ mvn clean compile install
```

Then in you pom.xml include it as a dependency

```xml
<dependency>
	<groupId>com.gp.dynamic-keystore</groupId>
    <artifactId>dynamic-keystore</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

Finally in your injector binding class initialize as show below. The DynamicKeystoreModule takes DynamoDB endpoint and a config table prefix. Pass empty strings if you want to use AWS DynamoDB endpoint and don't want to have a table prefix

```java
Guice.createInjector(new DynamicKeystoreModule("", ""));
```

Then you can get your configs dynamically using something like below

```java
ConfigService _service = injector.getInstance(ConfigService.class);
String propertyAsString = _service.getConfig("service_name","service_environment", "key").get();
```

You can also get a TypeSafe Config object as a config property

```java
Config propertiesAsTypeSafeConfig = _service.getConfigs("service_name", "service_environment").get();
```

To save config you can use

```java
_service.putConfig("service_name", "service_environment", "key", "value");
```

Since value is a String, you can even save your keystore/crt/pfx files and retrieve them.

To delete config, use

```java
_service.deleteConfig("service_name", "service_environment", "key");
```
