import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class ServerStore {

	
	public static Map<String, String> getServerMap(){
		Map<String, String> propertyMap = new HashMap<String, String>();
		try{
			Properties properties = new Properties();
			InputStream in = ServerStore.class.getResourceAsStream("server.properties");
			properties.load(in);			
			Enumeration<?> e = properties.propertyNames();
			while (e.hasMoreElements()) {
				String name = (String) e.nextElement();
				String value = properties.getProperty(name);
				propertyMap.put(name, value);
			}
		} catch(FileNotFoundException fnfe){
			System.out.println("Properties file not found" + fnfe);
		} catch(IOException ioe){
			System.out.println("IOException while reading the properties file" + ioe);
		}
		return propertyMap;
	}
	
	public static int getPortNumber(String value) {
		if(value.equals(Config.SERVER1)){
			return Config.SERVER1_PORT_NO;
		}
		if(value.equals(Config.SERVER2)){
			return Config.SERVER2_PORT_NO;
		}
		if(value.equals(Config.SERVER3)){
			return Config.SERVER3_PORT_NO;
		}
		if(value.equals(Config.SERVER4)){
			return Config.SERVER4_PORT_NO;
		}
		if(value.equals(Config.SERVER5)){
			return Config.SERVER5_PORT_NO;
		}
		return 0;
	} 
}
