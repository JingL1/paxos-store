import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server1 extends StoreServiceServer {

	public static void main(String args[]) throws Exception{

		Log log = new Log();

		try {
		    Server1 server = new Server1(1);
		    
		    StoreService stub = (StoreService)
		    		UnicastRemoteObject.exportObject(server, 0);
		    Registry registry = LocateRegistry.createRegistry(Config.SERVER1_PORT_NO);
		    registry.bind(Config.SERVER1, stub);
	
		    System.out.println("Server1 is running...");
		} catch (Exception e) {
		    log.connectionException();
		}
		
	}

	public Server1(int serverNumber) throws RemoteException {
		super(serverNumber);
	}


}
