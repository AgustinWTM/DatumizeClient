package serverCallerTest;

import datumizerServer.accesResouces.ServerCaller;
import datumizerServer.accesResouces.MyCallable;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

public class ServerDatuCallTest {

    ServerCaller serverCaller = new ServerCaller();
    String workingStatus = "working";
    long callsSuccess = 0l;
    long threadCounter = 0l;
    long callsToDo = 10l;

    @Test
    public void testCall001(){
        String baseVal = serverCaller.serverGetValue();
        Assert.assertTrue(baseVal != null);
    }

    @Test
    public void testCall002(){
        long baseIncr = 23l;
        String baseVal = serverCaller.serverGetValue();
        Assert.assertTrue(baseVal != null);
        String respVal = serverCaller.serverSetValue(baseIncr);
        Assert.assertEquals(Long.valueOf(baseVal)+baseIncr, Long.valueOf(respVal).longValue());
    }

    @Test
    public void testCall003(){
        long baseIncr = 17l;
        String result = "";
        String baseVal = serverCaller.serverGetValue();
        Assert.assertTrue(baseVal != null);

        Callable<String> callableObj = () -> {
            String respVal = "";
            for (int i = 0; i < 20; i++) {
                respVal = serverCaller.serverSetValue(baseIncr);
            }
            return respVal;
        };

        try {
            for(int i =0; i < 50; i++){
                ExecutorService serviceCall =  Executors.newSingleThreadExecutor();
                Future<String> stringFuture = serviceCall.submit(callableObj);
                result = stringFuture.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(Long.valueOf(result).longValue(), (baseIncr * 50 * 20) + Long.valueOf(baseVal));
    }


    @Test
    public void testCall004(){
        long baseIncr = 23l;
        String baseVal = serverCaller.serverGetValue();
        Assert.assertTrue(baseVal != null);
        while (this.threadCounter < callsToDo) {
            this.threadCounter++;
            this.serverAsyncCaller(baseIncr);
        }
        try {
            while(this.workingStatus.equalsIgnoreCase("working")){
                TimeUnit.SECONDS.sleep(1l);
            }
            TimeUnit.SECONDS.sleep(2l);
            Assert.assertEquals(Long.valueOf(baseVal)+(baseIncr * callsToDo * 5), Long.valueOf(serverCaller.serverGetValue()).longValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void serverAsyncCaller(long baseIncr){
        for(int i = 0; i < 5; i++){
            serverCaller.serverSetValueAsync(baseIncr, this.manageAsyncResponse);
        }
    }

    MyCallable manageAsyncResponse = new MyCallable(){
        @Override
        public String call() throws Exception {
            return null;
        }
        public String call(String resp) throws Exception {
            advancerCounter();
            if(callsSuccess >= (callsToDo * 5) && (threadCounter) >= callsToDo) {
                workingStatus = "Completed";
            }

            return null;
        }
    };

    private synchronized void advancerCounter(){
        this.callsSuccess++;
    }

}
