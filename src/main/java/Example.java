import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gp.dynamickeystore.service.ConfigService;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Example {
	public static void main(String[] args) {
		Config config = ConfigFactory.load();
		final Injector injector = Guice.createInjector(
			new DynamicKeystoreModule(config.getString("dynamo.endpoint"), config.getString("dynamo.tablePrefix"))
		);
		ConfigService _service = injector.getInstance(ConfigService.class);
		int port = 8080;
		for(int i=0;i<3;i++){
			_service.setConfig("test","dev","db"+i+".url","jdbc://localhost:"+(port+i));
			_service.setConfig("test","dev","db"+i+".user","admin");
			_service.setConfig("test","dev","db"+i+".password","admin");

		}
		_service.setConfig("local","dev","config",config.getConfig("dynamo").toString());

		assert _service.getConfigs("local","dev").get().getConfigList("config").size()==2;
		assert "admin".equalsIgnoreCase(_service.getConfig("test","dev","db1.user").get());

		log.info(_service.getConfigs("test","dev").get().toString());
	}
}
