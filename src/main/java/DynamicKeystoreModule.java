import com.google.inject.AbstractModule;
import com.gp.dynamickeystore.access.DynamicAccessModule;
import com.gp.dynamickeystore.service.ServiceModule;

public class DynamicKeystoreModule extends AbstractModule {

	private final String dynamoEndpoint;
	private final String dynamoTablePrefix;

	public DynamicKeystoreModule(String dynamoEndpoint, String dynamoTablePrefix) {
		this.dynamoEndpoint = dynamoEndpoint;
		this.dynamoTablePrefix = dynamoTablePrefix;
	}

	@Override
	protected void configure() {
		install(new DynamicAccessModule(dynamoEndpoint,dynamoTablePrefix));
		install(new ServiceModule());
	}
}
