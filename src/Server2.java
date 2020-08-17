import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server2 extends StoreServiceServer {

	public static void main(String args[]) throws Exception{

		Log log = new Log();

		try {
		    Server2 server = new Server2(2);
		    StoreService stub = (StoreService)
		    		UnicastRemoteObject.exportObject(server, 0); 
		    Registry registry = LocateRegistry.createRegistry(Config.SERVER2_PORT_NO);
		    registry.bind(Config.SERVER2, stub);
	
		    System.out.println("Server2 is running...");
		} catch (Exception e) {
			log.connectionException();
		}
	}

	public Server2(int serverNumber) throws RemoteException {
		super(serverNumber);
	}

}