import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client {


	public static void main(String args[]) throws Exception{

		Log log = new Log();
		System.out.println("Client is running...");

		try {
			Registry registry = LocateRegistry.getRegistry("localhost", ServerStore.getPortNumber(args[0]));
			StoreService stub = (StoreService) registry.lookup(args[0]);

			//init data & 15 tests
			test(stub);

			//take cmds from terminal
			while(true) {

				log.usage();
				String input = takeInputFromTerminal();
				String[] formattedInput = input.split(" ");
				log.ackRequestSent(input);
			
				if(formattedInput[0].equalsIgnoreCase("PUT")) {
					System.out.println(stub.put(Integer.valueOf(formattedInput[1])));
				} else if(formattedInput[0].equalsIgnoreCase("GET")) {
					System.out.println(stub.get(Integer.valueOf(formattedInput[1])));
				} else if(formattedInput[0].equalsIgnoreCase("DELETE")) {
					System.out.println(stub.delete(Integer.valueOf(formattedInput[1])));
				} else {
					log.illegalRequestTypeException();
				}
			}
		}
		catch(RemoteException | NotBoundException e){
			log.connectionException();
		}
	}

	//test
	public static void test(StoreService stub) throws Exception {
		System.out.println("========【Test Start】=======");
		Log log = new Log();
		int[] testKey = new int[10];// key list for test

		//10 times put: generate 10 random integer as keys
		for(int i = 0; i < 10; i++){
			testKey[i] = (int) (Math.random() * 100); // generate a random integer for testing;
			log.ackRequestSent("put " + testKey[i]);
			System.out.println(stub.put(testKey[i]));
		}

		//5 times delete: delete first 5 kv pairs
		for(int i = 0; i < 5; i++){
			log.ackRequestSent("delete " + testKey[i]);
			System.out.println(stub.delete(testKey[i]));
		}

		//5 times get: get 5 kv pairs follows
		for(int i = 5; i < 10; i++){
			log.ackRequestSent("get " + testKey[i]);
			System.out.println(stub.get(testKey[i]));
		}
	}

	//take input from cmd
	public static String takeInputFromTerminal() throws IOException {
		BufferedReader stringIn = new BufferedReader (new InputStreamReader(System.in));
		return  stringIn.readLine();
	}



}
