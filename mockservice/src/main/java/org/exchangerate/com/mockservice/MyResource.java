package org.exchangerate.com.mockservice;
import org.exchangerate.com.outerws.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;
import org.json.JSONTokener;


@Path("/outerws")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.	
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {	
        JSONTokener tokener = null;
        JSONObject object = null;
        Response response = null;
    			try {
    				tokener = new JSONTokener(new FileReader("ypkrediconfig.json"));
    			} catch (FileNotFoundException e) {
    				e.printStackTrace();
    			}
    	        JSONObject config = new JSONObject(tokener);
    	        ProcessData data = new ProcessData(config);
    	         object = data.getMockRates();
           
           try {
             
             response = Response.status(Status.OK).entity(object.toString()).build();
           } catch (Exception e) {
             System.out.println("error=" + e.getMessage());
           }
           return response;
    }	
   
}
