

import java.net.SocketTimeoutException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StoreService extends Remote {

	  String get(int key) throws RemoteException;
	  String put(int key) throws RemoteException;
	  String delete(int key) throws RemoteException;
	  boolean prepare(int proposalId, int key, int action) throws RemoteException, SocketTimeoutException;
	  boolean accept(int proposalId, int key, int action) throws RemoteException, SocketTimeoutException;
	  String commit(int key, int action) throws RemoteException, SocketTimeoutException;

}
