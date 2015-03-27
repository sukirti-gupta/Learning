package com.rest.jersey.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rest.jersey.client.JerseyClient;
import com.rest.jersey.model.User;

public class JerseyClient {
    
    public static final String USER_URI="http://localhost:8080/JerseyLatestWithSpring";
    
    public String testGetUsersAll() {

        Client client = ClientBuilder.newClient();
        //        WebTarget target = client.target(USER_URI);
        //        Response response = target
        //                .request(MediaType.TEXT_PLAIN_TYPE)
        //                .get();

        //        ClientConfig config = new DefaultClientConfig();
        //        Client client = Client.create(config);
        //        WebResource resource = client.resource(USER_URI);
        //        ClientResponse response = resource.type(MediaType.APPLICATION_XML).get(
        //                ClientResponse.class);
        //String en = response.getEntity(String.class);

        String en = client.target(USER_URI)
                .request(MediaType.APPLICATION_XML)
                .get(String.class);
        return en;
    }
    
    public User testCreateUser() {
        User user = new User("John", "john@");

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(USER_URI);
        Response response = target.request(MediaType.APPLICATION_XML)
                .post(Entity.entity(user, MediaType.APPLICATION_XML));

        //        Client client = Client.create();
        //        WebResource resource = client.resource(USER_URI);
        //        ClientResponse response = resource.accept(MediaType.APPLICATION_XML).post(
        //        ClientResponse.class, user);

        System.out.println(response.getStatus());
        return user;
    }
    
    public static void main(String[] args) {
        JerseyClient jerseyClient = new JerseyClient();
        jerseyClient.testCreateUser();
    }

}
