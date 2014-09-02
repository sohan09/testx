import play.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.net.*;

public class Global extends GlobalSettings {
/* 	PrintStream out = null;
	
	public Global() {
		try {
			out = new PrintStream(new FileOutputStream("C:\\Users\\sohan\\Desktop\\ttt.txt"));
		} catch(FileNotFoundException ex) {
			return;
		}
	}

	@Override
    public void beforeStart(Application app) {
		Logger.info("####Before Application Start");
		Map<String, Object> map = app.configuration().asMap();
		
		
 		URI dbUri = null;
		
		try {
			
		//	dbUri = new URI(System.getenv("DATABASE_URL"));
			Logger.info("postgres://postgres:root@localhost:5432/xstore");
			dbUri = new URI("postgres://postgres:root@localhost:5432/xstore");
			
		} catch (URISyntaxException ex) {
			Logger.info(ex + "");
			return;
		}
		
		String user = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
		Logger.info(user + " : " + password + " : " + dbUrl);
		
		map = (Map<String, Object>) map.get("db");
		map = (Map<String, Object>) map.get("default");
		
		map.put("driver", "org.postgresql.Driver");
		map.put("url", dbUrl);		
		map.put("user", user);
		map.put("password", password);
		
		
		out.println(map);
		Logger.info(map + "");      

		map = app.configuration().asMap();	
		map = (Map<String, Object>) map.get("db");
		map = (Map<String, Object>) map.get("default");		
		
		out.println("d : " + map);
		Logger.info("d : " + map + "");  
		out.flush();
    }

	@Override
    public void onStart(Application app) {
		Logger.info("##Application has started");
		Map<String, Object> map = app.configuration().asMap();
		
		map = (Map<String, Object>) map.get("db");
		map = (Map<String, Object>) map.get("default");
		
		out.println(map);
		Logger.info(map + "");        
    }
	
	@Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    } */

}