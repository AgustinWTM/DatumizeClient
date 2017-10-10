package datumizerServer.accesResouces;

import org.glassfish.jersey.client.ClientConfig;
import serverEnums.DatumizeURI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.*;

public class ServerCaller {

    private WebTarget target = null;
    private Client client = null;

    public ServerCaller() {
        ClientConfig config = new ClientConfig();
        client = ClientBuilder.newClient(config);
        target = client.target(DatumizeURI.BASEURI.getUseURI());
    }

    public String serverGetValue() {
        String responseCall = target
                .path("rest")
                .path(DatumizeURI.BASEGETVAL.getUseURI())
                .request()
                .accept(MediaType.TEXT_PLAIN)
                .get(String.class);
        return responseCall;
    }

    public String serverSetValue(long valToAdd){
        String responseCall = target
                .path("rest")
                .path((DatumizeURI.BASESETVAL.getUseURI() + String.valueOf(valToAdd)))
                .request()
                .accept(MediaType.TEXT_PLAIN)
                .get(String.class);
        return responseCall;
    }

    public String serverGetValueAsync(MyCallable callable) {
        String response = "in progress";
        try {
        Future<String> responseCall = target
                .path("rest")
                .path(DatumizeURI.BASEGETVAL.getUseURI())
                .request()
                .async()
                .get(new InvocationCallback<String>() {
                    @Override
                    public void completed(String s) {
                        try {
                            callable.call(s);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        System.out.println("response on fail: "+throwable.getMessage());
                        try {
                            callable.call(throwable.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    public String serverSetValueAsync(long valToAdd, MyCallable callable){
        String response = "in progress";
        try {
            Future<String> responseCall = target
                    .path("rest")
                    .path((DatumizeURI.BASESETVAL.getUseURI() + String.valueOf(valToAdd)))
                    .request()
                    .async()
                    .get(new InvocationCallback<String>() {
                        @Override
                        public void completed(String s) {
                            try {
                                callable.call(s);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(Throwable throwable) {
                            System.out.println("response on fail: "+throwable.getMessage());
                            try {
                                callable.call(throwable.getMessage());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
