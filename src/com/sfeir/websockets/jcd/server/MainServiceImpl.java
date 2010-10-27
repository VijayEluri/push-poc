package com.sfeir.websockets.jcd.server;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sfeir.websockets.jcd.client.MainService;
import com.sfeir.websockets.jcd.shared.Profile;
import com.sfeir.websockets.jcd.shared.User;

@SuppressWarnings("serial")
public class MainServiceImpl extends RemoteServiceServlet implements MainService {

	private static Map<String, User> users;
	private static Set<User> clients;
	private static Set<User> operators;
	
	private static int sequence = 0;
	static {
		users = new HashMap<String, User>();
		clients = new HashSet<User>();
		operators = new HashSet<User>();
		
		registerUser(new User(sequence++, "operateur1", "Op�rateur 1", Profile.OPERATOR, "Paris", User.Language.FRENCH, ""));
		registerUser(new User(sequence++, "operateur2", "Op�rateur 2", Profile.OPERATOR, "Paris", User.Language.FRENCH, ""));
		registerUser(new User(sequence++, "client1", "Client 1", Profile.CLIENT, "London", User.Language.ENGLISH, "Ne parle que anglais."));
		registerUser(new User(sequence++, "client2", "Client 2", Profile.CLIENT, "Geneva", User.Language.FRENCH, "A d�j� appel� le 3 sept."));
		registerUser(new User(sequence++, "client3", "Client 3", Profile.CLIENT, "Paris", User.Language.FRENCH, ""));
	}
	
	static void registerUser(User u) {
		users.put(u.getUsername(), u);
		switch (u.getProfile()) {
		case CLIENT:
			clients.add(u);
			break;
		case OPERATOR:
			operators.add(u);
			break;
		}
	}
	
	@Override
	public boolean login(String username) {
		User user = users.get(username);
		return user != null && Profile.OPERATOR.equals(user.getProfile());
	}
	
	@Override
	public User getDetails(String userId) {
		return users.get(userId);
	}
	
	@Override
	public Set<User> listClients() {
		return Collections.unmodifiableSet(clients);
	}
	
	@Override
	public Set<User> listOperators() {
		return Collections.unmodifiableSet(operators);
	}

	@Override
	public void makeCall(String operator, String client) {
		
	}
	
}
