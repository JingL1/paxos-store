
public class Learner extends KVStore implements Runnable{


	public String commit(int key, int reqType){
		String response = "";
		switch(reqType) {
			case 1: response = super.getKey(key);//get
					break;
			case 2: response = super.putKey(key);//put
					break;
			case 3: response = super.deleteKey(key);//delete
					break;
		}
		return response;
	}

	public void start(){}

	@Override
	public void run() {}
}
