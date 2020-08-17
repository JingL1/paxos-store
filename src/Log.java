
import java.text.SimpleDateFormat;

public class Log {

    // format time in yy-mm-dd hh:mm:ss.ms
    public String timeFormat(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        return formatter.format(time);
    }

    // periodically shut down a server
    public void errServerDown(int sv) {
        String res = timeFormat(System.currentTimeMillis()) +
                " ERR-FALT-TOLERANT-TEST: Server [" + sv+ "] has shut down for 200 milliseconds.";
        System.out.println("\n"+res);
    }

    //response for consensus
    public void ackRequestReceived(String stage, int count){

        String res = timeFormat(System.currentTimeMillis()) +
                " >>> INFO-Paxos: ["+ count +"] servers positively replied for the [" + stage + "] stage.";
        System.out.println(res);
    }


    // no consensus based on majority
    public String errResponse(int count) {
        return timeFormat(System.currentTimeMillis()) +
                " INFO: [" + count+ "] servers has positively replied and not reached consensus.";
        //System.out.println(">>> "+res);
    }

    public void paxosLog(String msg){
        System.out.println(timeFormat(System.currentTimeMillis()) +
                        " INFO: " + msg);
    }


    // ack for request send form client
    public void ackRequestSent(String req) {
        String res = timeFormat(System.currentTimeMillis()) +
                " INFO: Client sends a request [" + req+ "] to server.";
        System.out.println("\n"+res);
    }

    //ack for PUT operated successfully
    public String ackPut(int key, int val) {
        return timeFormat(System.currentTimeMillis()) +
                " INFO: Server has stored key [" + key + "] with value [" + val + "].";
    }

    //PUT failed
    public String errPut(int key, int val) {
        return timeFormat(System.currentTimeMillis()) +
                " INFO: The Key has existed. Server fails to store [" + key + "] with [" + val + "].";
    }

    //ack for GET operated successfully
    public String ackGet(int key, int val) {
        return timeFormat(System.currentTimeMillis()) +
                " INFO: The value [" + val + "] of key [" + key + "] is found in the store!";
    }

    //GET failed
    public String errGet() {
        return timeFormat(System.currentTimeMillis()) +
                " INFO: The key does not exist.";
    }

    //ack for Delete operated successfully
    public String ackDel(int key, int val) {
        return timeFormat(System.currentTimeMillis()) +
                " INFO: The key [" + key + "] has been removed with value [" + val + "]!";
    }

    //DELETE failed
    public String errDel(int key) {
        return timeFormat(System.currentTimeMillis()) +
                " INFO: The key does not exist.";
    }


    //invalid request
    public void illegalRequestTypeException() {
        System.out.println(System.currentTimeMillis() +
                " Error: Received invalid request from user. Check and try again.");
    }


    //malformed request : customized error type
    public void connectionException() {
        System.out.println(
                timeFormat(System.currentTimeMillis()) +
                " Error: Connection exception or RMI error. Check and try again.");
    }

    public void usage(){
        System.out.println(
                "\n>>> Usage: <RequestType> <Key>." +
                        "\n>>> E.g.: <Get/Put/Delete> <199>, Note that the key must be an integer." +
                        "\nEnter:"
        );
    }

}

