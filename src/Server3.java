import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server3 extends StoreServiceServer {

	public static void main(String args[]) throws Exception{

        Log log = new Log();

		try {
		    Server3 server = new Server3(3);
		    
		    StoreService stub = (StoreService)
		    		UnicastRemoteObject.exportObject(server, 0);
		    Registry registry = LocateRegistry.createRegistry(Config.SERVER3_PORT_NO);
		    registry.bind(Config.SERVER3, stub);
	
		    System.out.println("Server3 is running...");
		} catch (Exception e) {
		    log.connectionException();
		}
    }

    public Server3(int serverNumber) throws RemoteException {
        super(serverNumber);
    }

}
