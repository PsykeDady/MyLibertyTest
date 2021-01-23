package rest;

import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import facade.QueryFacade;
import facade.impl.SQLQueryFacade;
import psykeco.ioeasier.io.DebugPrint;
import utils.MyAppConstant;
import utils.MyAppProperties;

import static models.infosessions.ResponseState.*;

@Path("my")
public class MyResource {

    
    private static final QueryFacade facade=new SQLQueryFacade();
    private static DebugPrint dp;

    
    static{
        dp=new DebugPrint(MyAppProperties.getInstance().getValue(MyAppConstant.DEBUG_LOG),true);
		dp.debug_mode=true;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("hello")
    public String hello() {
        return "Ciao";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("instantTimerStart")
    public String instantTimerStart() {

        return toStringReponse(KO,"not implemented yet");
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("valueTest")
    public Map<String,String> valueTest() {
        Map<String,String> map = new java.util.HashMap<>();
        
        map.put("ciao", "come stai");
        
        return map;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("postTest")
    public String postTest(
        Map<String,String> m
    ) {
        dp.println(
            "[DEBUG:> postTest] Map<String,String> m=\n"+
            m        
        );
        
        StringBuilder sb=new StringBuilder();
        if (m==null){
            return "Non son stati trovati valori!";
        }
        for( Entry<String,String> kv: m.entrySet() ){
            sb.append(kv.getKey()+"="+kv.getValue()+'\n');
        }

        dp.flush();
        return sb.toString();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("insertTest")
    public String insertTest(
        Map<String,String> m
    ) {

        dp.println(
            "[DEBUG:> insertTest] Map<String,String> m=\n"+
            m        
        );


        return toStringReponse(KO,"not implemented yet");
    }

}