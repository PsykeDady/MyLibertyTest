package main.java.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import dto.ChiaveValore;
import dto.GenericForm;

@Path("my")
public class MyResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("hello")
    public String hello() {
        return "Ciao";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("postTest")
    public String postTest(
        GenericForm form
    ) {
        StringBuilder sb=new StringBuilder();

        List<ChiaveValore> l=form.getValori();
        if (l==null){
            return "Non son stati trovati valori!";
        }
        for(ChiaveValore cv:l){
            sb.append(cv.getChiave()+"="+cv.getValore()+'\n');
        }

        return sb.toString();
    }

}