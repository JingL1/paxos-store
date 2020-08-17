import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server5 extends StoreServiceServer {

	public static void main(String args[]) throws Exception{

		Log log = new Log();
		
		try {
		    Server5 server = new Server5(5);
		    
		    StoreService stub = (StoreService)
		    		UnicastRemoteObject.exportObject(server, 0);
		    Registry registry = LocateRegistry.createRegistry(Config.SERVER5_PORT_NO);
		    registry.bind(Config.SERVER5, stub);
	
		    System.out.println("Server5 is running...");
		} catch (Exception e) {
		    log.connectionException();
		}
	}

	public Server5(int serverNumber) throws RemoteException {
		super(serverNumber);
	}

}
