package datumizeBussines.workOn;

import datumizeBussines.interf.WorkOnClient;
import datumizerServer.accesResouces.ServerCaller;

public class WorkOnClientSyncImplementation implements WorkOnClient {

    private ServerCaller serverCaller = new ServerCaller();

    public String getVal(){
        String response = serverCaller.serverGetValue();
        return response;
    }
    public String addVal(long newVal){
        String response = serverCaller.serverSetValue(newVal);
        return response;
    }
}
