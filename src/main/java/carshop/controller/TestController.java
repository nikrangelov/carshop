package carshop.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/test")
public class TestController {

    @GET
    @Produces("application/json")
    public String test() {
        return "Jersey: Up and Running!";
    }



}