import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server4 extends StoreServiceServer {

	public static void main(String args[]) throws Exception{

		Log log = new Log();

		try {
		    Server4 server = new Server4(4);
		    
		    StoreService stub = (StoreService)
		    		UnicastRemoteObject.exportObject(server, 0);
		    Registry registry = LocateRegistry.createRegistry(Config.SERVER4_PORT_NO);
		    registry.bind(Config.SERVER4, stub);
	
		    System.out.println("Server4 is running...");
		} catch (Exception e) {
		    log.connectionException();
		}
	}

	public Server4(int serverNumber) throws RemoteException {
		super(serverNumber);
	}
}
