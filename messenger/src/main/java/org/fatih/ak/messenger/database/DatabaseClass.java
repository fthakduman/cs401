package org.fatih.ak.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.fatih.ak.messenger.model.Message;
import org.fatih.ak.messenger.model.Profile;

public class DatabaseClass {

	
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<Long, Profile> profiles = new HashMap<>();
	
	
	
	public static Map<Long, Profile> getProfiles() {
		return profiles;
	}
	public static Map<Long, Message> getMessages() {
		// TODO Auto-generated method stub
		return messages;
	}
	
	
	
	
}
