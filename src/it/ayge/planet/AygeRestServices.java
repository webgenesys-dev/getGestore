package it.ayge.planet;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("webgenesypublish")
public class AygeRestServices extends ResourceConfig{
	
	public AygeRestServices() {		
	   packages("it.ayge.planet.rest");
       register(MultiPartFeature.class);       
	}
}
