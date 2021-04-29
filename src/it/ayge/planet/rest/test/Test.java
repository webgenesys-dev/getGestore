package it.ayge.planet.rest.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/testService")
public class Test{

@GET
@Produces(MediaType.TEXT_PLAIN)
public String getTestService() throws ClassNotFoundException {
 	System.out.println("Test OK - Web service raggiungibile !");
	return "Test OK - Web service raggiungibile !";
	}
}