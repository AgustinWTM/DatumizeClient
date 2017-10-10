package datumizeBussines.workOn;

import datumizeBussines.consoleManager.ConsoleManager;
import datumizeBussines.interf.WorkOnClient;
import datumizerServer.accesResouces.ServerCaller;
import datumizerServer.accesResouces.MyCallable;

public class WorkOnClientAsyncImplementation implements WorkOnClient {

    private ServerCaller serverCaller = new ServerCaller();
    private ConsoleManager consoleManager = new ConsoleManager();

    public String getVal(){
        String response = serverCaller.serverGetValueAsync(this.manageAsyncResponse);
        return response;
    }
    public String addVal(long newVal){
        String response = serverCaller.serverSetValueAsync(newVal, this.manageAsyncResponse);
        return response;
    }

    MyCallable manageAsyncResponse = new MyCallable(){
        @Override
        public String call() throws Exception {
            return null;
        }
        public String call(String resp) throws Exception {
            consoleManager.serverResponseShow(resp);
            return null;
        }
    };
}
