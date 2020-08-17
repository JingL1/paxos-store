import java.util.HashMap;


public class KVStore {

	static HashMap<Integer, Integer> keyValueMap;
	Log log = new Log();

	public KVStore(){
		keyValueMap = new HashMap<>();
	}

	private static int generateValue(int key){
		return key*10;
	}

	//PUT
	public String putKey(int key) {
		String response = "";

		if(!keyValueMap.containsKey(key)){
			keyValueMap.put(key, generateValue(key));
			response = log.ackPut(key, generateValue(key));

		} else {
			response = log.errPut(key,generateValue(key));
		}
		System.out.println(response);
		return response;
	}


	//GET
	public String getKey(int key) {
		String response = "";
		if(keyValueMap.containsKey(key)){
			int val = keyValueMap.get(key);
			response  = log.ackGet(key,val);
		} else {
			response = log.errGet();
		}
		System.out.println(response);
		return response;
	}


	//DELETE
	public String deleteKey(int key) {
		String response = "";
		if(keyValueMap.containsKey(key)){
			keyValueMap.remove(key,generateValue(key));
			response = log.ackDel(key, generateValue(key));
		}else {
			response = log.errDel(key);
		}

		System.out.println(response);
	    return response;
	}


	//Check
	public boolean checkReq(int key, int action){
		switch(action) {
		case 1:if(keyValueMap.containsKey(key)) //get
				return true;				
		case 2:if(!keyValueMap.containsKey(key)) //put
				return true;				
		case 3:if(keyValueMap.containsKey(key)) //delete
				return true;				
		}
		return false;
	}
}
