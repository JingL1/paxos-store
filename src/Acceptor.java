import java.net.SocketTimeoutException;
import java.rmi.RemoteException;

public class Acceptor extends KVStore implements Runnable{

	static int isAlive;
	private static int myproposalId;
	private boolean active;
	private int serverNo;


	public void setProposalId(int myproposalId) {
		Acceptor.myproposalId = myproposalId;
	}
	
	public void start(){
		active = true;
	}

	
	public boolean accept(int proposalId, int key, int req) throws RemoteException, SocketTimeoutException{
		return isFault(proposalId, key, req);
	}
	
	public boolean prepare(int proposalId, int key, int req) throws RemoteException, SocketTimeoutException{
		return isFault(proposalId, key, req);
	}

	private boolean isFault(int proposalId, int key, int req){
		try{
			if(isAlive % 20 == 0){//every 20 times a server would shut down for 200 ms
				log.errServerDown(serverNo);
				Thread.sleep(200);
			}
			isAlive++;

		} catch (InterruptedException ie){
			
		}
		if(proposalId < myproposalId){
			return false;
		}
		if(super.checkReq(key, req)) {
			setProposalId(proposalId);
			return true;
		}
		return false;
	}

	public void setServerNo(int serverNo) {
		this.serverNo = serverNo;
	}

	@Override
	public void run() {
	}
}
