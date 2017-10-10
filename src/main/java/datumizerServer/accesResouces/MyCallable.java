package datumizerServer.accesResouces;

import java.util.concurrent.Callable;

public class MyCallable implements Callable {
    @Override
    public String call() throws Exception {
        return null;
    }
    public String call(String resp) throws Exception {
        return resp;
    }
}
