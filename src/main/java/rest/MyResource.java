package rest;

import static models.infosessions.ResponseState.KO;
import static models.infosessions.ResponseState.toStringReponse;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import facade.QueryFacade;
import facade.impl.SQLQueryFacade;
import models.relationship.SimpleDataTest;
import psykeco.ioeasier.io.DebugPrint;
import utils.MapperClass;
import utils.MyAppLog;

@Path("my")
public class MyResource {

    
    private static final QueryFacade facade=new SQLQueryFacade();
    private static DebugPrint dp=MyAppLog.getInstance();

    
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
        String methodName=""+this.getClass()+'.'+Thread.currentThread().getStackTrace()[1].getMethodName();
        dp.debug_mode=true;
        dp.println(
            "[DEBUG START:> "+methodName+"] Map<String,String> m=\n"+
            m        
        );
        
        StringBuilder sb=new StringBuilder();
        if (m==null){
            return "Non son stati trovati valori!";
        }
        for( Entry<String,String> kv: m.entrySet() ){
            sb.append(kv.getKey()+"="+kv.getValue()+'\n');
        }

        dp.println("[DEBUG END:> "+methodName+"]");
        dp.flush();
        return sb.toString();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("insertTest")
    public String insertTest(
        Map<String,String> m
    ) {
        String methodName=""+this.getClass()+'.'+Thread.currentThread().getStackTrace()[1].getMethodName();
        dp.debug_mode=true;
        dp.println(
            "[DEBUG START:> "+methodName+"] Map<String,String> m=\n"+
            m        
        );

        SimpleDataTest e= MapperClass.mapObject(SimpleDataTest.class, m);
        facade.insert(e);

        dp.println("[DEBUG END:> "+methodName+"]");
        dp.flush();
        Entry<String,String> resp=facade.getResp();
        return resp.getKey()+":"+resp.getValue();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("updateTest")
    public String updateTest(
        Map<String,String> m
    ) {
        String methodName=""+this.getClass()+'.'+Thread.currentThread().getStackTrace()[1].getMethodName();
        dp.debug_mode=true;
        dp.println(
            "[DEBUG START:> "+methodName+"] Map<String,String> m=\n"+
            m        
        );

        SimpleDataTest e= MapperClass.mapObject(SimpleDataTest.class, m);
        facade.update(e,"identity");

        dp.println("[DEBUG END:> "+methodName+"]");
        dp.flush();
        Entry<String,String> resp=facade.getResp();
        return resp.getKey()+":"+resp.getValue();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deleteTest")
    public String deleteTest(
        Map<String,String> m
    ) {
        String methodName=""+this.getClass()+'.'+Thread.currentThread().getStackTrace()[1].getMethodName();
        dp.debug_mode=true;
        dp.println(
            "[DEBUG START:> "+methodName+"] Map<String,String> m=\n"+
            m        
        );

        SimpleDataTest e= MapperClass.mapObject(SimpleDataTest.class, m);
        facade.remove(e);

        dp.println("[DEBUG END:> "+methodName+"]");
        dp.flush();
        Entry<String,String> resp=facade.getResp();
        return resp.getKey()+":"+resp.getValue();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("selectListTest")
    public List<String> selectList(
        Map<String,String> m
    ) {
        String methodName=""+this.getClass()+'.'+Thread.currentThread().getStackTrace()[1].getMethodName();
        dp.debug_mode=true;
        dp.println(
            "[DEBUG START:> "+methodName+"] Map<String,String> m=\n"+
            m        
        );

        SimpleDataTest e= MapperClass.mapObject(SimpleDataTest.class, m);
        List<Object> l=facade.selectList(e);

        dp.println("[DEBUG END:> "+methodName+"]");
        dp.flush();
        Entry<String,String> resp=facade.getResp();
        List<String>result=new LinkedList<>();
        result.add(resp.getKey()+":"+resp.getValue());
        for (Object obj : l)
            result.add(obj.toString());

        return result;
    }

}