package main.java.rest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("my")
public class MyResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "Ciao";
    }

}