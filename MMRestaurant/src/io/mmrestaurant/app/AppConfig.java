package io.mmrestaurant.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

// jersey annotation 
@ApplicationPath("/api")

// extending the jersey class ResourceConfig to configure jersey
public class AppConfig extends ResourceConfig {

	// constructor to tell where the classes(packages) are located
	public AppConfig(){
		packages("io.mmrestaurant");
	}
}
