package it.ayge.planet.listener;

import java.io.File;
import java.net.InetAddress;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;




@WebListener
public class AppContextListener implements ServletContextListener{

	
	public static String rootPath;
	public static String DIR_CONF;
	public static String AYGE_HOME;
	public static String DB_CONFIG_FILE;
	public static String CONTEXT_PATH;
	public static String CURRENT_HOST_NAME;

	
	public AppContextListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		System.out.println("=================== contextDestroyed ===============");
				
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		
		
		
		
		try 
		{
			CURRENT_HOST_NAME = InetAddress.getLoopbackAddress().getHostAddress();			
			CONTEXT_PATH = arg0.getServletContext().getContextPath().replace("/", "");
			rootPath = arg0.getServletContext().getRealPath(File.separator);			
			
			//AYGE_HOME = System.getenv("AYGE_HOME"); 
			//DIR_CONF = AYGE_HOME+File.separator+"config";
			
			System.out.println("CONTEXT_PATH----------------> " + CONTEXT_PATH);
			System.out.println("rootPath--------------------> " + rootPath);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	/*
	private String getConfFileValue(String propName)
	{
		String res = null;
		try {
			
			File f = null;
			String path = DIR_CONF.concat(File.separator).concat("ApplicationResources_").concat(CONTEXT_PATH).concat(".properties");
			//.out.println("path conf.................. "+path);
			
			f = new File(path);
			if(!f.exists())
				path = rootPath.concat(File.separator).concat("conf").concat(File.separator).concat("config.properties");
			
			res = GetConfigPropertyValues.getInstance().getPropValues(path,	propName);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res; 
	}
	*/
	
	
	
	
	
	

}
