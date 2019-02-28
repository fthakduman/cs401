package org.fatih.ak.messenger.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fatih.ak.messenger.model.Message;
import org.fatih.ak.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {

	
	MessageService messageService = new MessageService();
	

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessages() {
		
			return messageService.getAllMessages();
	}
	
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "test";
	}
		
}
