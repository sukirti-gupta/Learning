package com.rest.jersey.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.mvc.Viewable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.rest.jersey.model.User;
import com.rest.jersey.service.UserService;

@Component
@Path("/")
public class UserResource {

    //    @Context
    //    UriInfo uriInfo;
    @Autowired
    UserService userService;

    private final static Logger LOG = LoggerFactory.getLogger(UserResource.class);

    @Path("page")
    @GET 
    @Produces (MediaType.TEXT_HTML) //Was working withour this too
    public Viewable getHomePage () { 
        System.out.println("getHomePage");
        return new Viewable("/home"); //Works with .jsp as well, but does not without /
    }
    
    @Path("success")
    @GET 
    @Produces (MediaType.TEXT_HTML) //Was working withour this too
    public Viewable success () { 
        return new Viewable("/success");
    }
    
    @Path("failure")
    @GET 
    @Produces (MediaType.TEXT_HTML) //Was working withour this too
    public Viewable failure() { 
        return new Viewable("/failure");
    }

    @Path("signup")
    @GET
    @Produces (MediaType.TEXT_HTML)
    public Response signup() {
        return Response.ok(new Viewable("/signup")).build();
    }

    @Path("signup")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces (MediaType.TEXT_HTML)
    public Response signup(@FormParam("name") String name, @FormParam("password") String password) {
        if (name.length()==0 || password.length()==0) {
            return Response.status(Status.PRECONDITION_FAILED).build();
        }
        User user = new User(name, password);
        try {
            if(userService.findByName(name)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("message", "User Name exists. Try another user name");
                map.put("student", user);
                return Response.status(Status.BAD_REQUEST)
                        .entity(new Viewable("/signup", map)).build();
            } else {
                userService.CreateUser(user);
                return Response.ok(new Viewable("/success")).build();
            }
        } catch(Exception e) {
            LOG.error(e.getLocalizedMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Viewable("/failure")).build();
        }
    }

    @Path("login")
    @GET
    @Produces (MediaType.TEXT_HTML)
    public Response login() {
        return Response.ok(new Viewable("/login")).build();
    }
    
    @Path("openidlogin")
    @GET
    @Produces (MediaType.TEXT_HTML)
    public Response openidlogin() {
        return Response.ok(new Viewable("/loginWithOpenid")).build();
    }

    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces (MediaType.TEXT_HTML)
    public Response login(@FormParam("name") String name, @FormParam("password") String password) {
        if (name == null || password == null) {
            return Response.status(Status.PRECONDITION_FAILED).build();
        }
        try{
            boolean found = userService.validateUser(name, password);

            if (found) {
                return Response.ok().entity(new Viewable("/success")).build();
            } else {
                return Response.status(Status.BAD_REQUEST)
                        .entity(new Viewable("/failure")).build();
            }
        } catch(Exception e) {
            LOG.error(e.getLocalizedMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Viewable("/failure")).build();
        }
    }
    
//    @Path("login2")
//    @GET
//    @Produces (MediaType.TEXT_HTML)
//    public Response login2() {
//    	
//    	HttpServletRequest request = new HttpServletRequest()
//			
//    	HttpSession session = request.getSession();
//    	if(session!=null) {
//    		Enumeration<String> error = session.getAttributeNames();
//        	System.out.println(error);
//    	}
//    	
//        try {
//            String message = "";
//            String error2 = "abc";
//			if (error2  != null) {
//            	//message = error;
////                message = "Invalid username or password, try again !";
////                LOG.debug("Invalid credentials");
////                return Response.status(Status.BAD_REQUEST)
////                        .entity(new Viewable("/login", message)).build();
////            } else if (logout != null) {
////                message = "Logged out successfully";
////                LOG.info(message);
////                return Response.status(Status.BAD_REQUEST)
////                        .entity(new Viewable("/success", message)).build();
////            } else if (denied != null) {
////                message = "Access denied for this user !";
////                LOG.debug("Access denied");
////                return Response.status(Status.BAD_REQUEST)
////                        .entity(new Viewable("/login", message)).build();
//            	
//            }
//            return Response.ok().entity(new Viewable("/login", message)).build();
//            
//        } catch (Exception e) {
//            return Response.status(Status.BAD_REQUEST)
//                    .entity(new Viewable("/failure","An error occured. Please try again")).build();
//        }
//    }

    @Path("users")
    @GET
    @Produces (MediaType.APPLICATION_XML)
    public List<User> getUsersAll() {
        LOG.debug("getUsersAll()");
        List<User> als=null;
        try {
            als= userService.getUserAll();
        } catch (ClassNotFoundException e) {
            LOG.error(e.getLocalizedMessage());
        } catch (SQLException e) {
            LOG.error(e.getLocalizedMessage());
        }
        return als;
    }

    //    @Path("create")
    //    @POST
    //    @Consumes (MediaType.APPLICATION_XML)
    //    @Produces (MediaType.APPLICATION_XML)
    //    public User createUser(User user){
    //        LOG.debug("createUser()");
    //        URI uri = uriInfo.getAbsolutePathBuilder().path(user.getName()).build();
    //        Response.created(uri).build();
    //        try {
    //            userService.CreateUser(user);
    //        } catch (ClassNotFoundException e) {
    //            LOG.error(e.getLocalizedMessage());
    //        } catch (SQLException e) {
    //            LOG.error(e.getLocalizedMessage());
    //        }
    //        return user;
    //    }

    @Path("create")
    @POST
    //    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response createUser(){
        LOG.debug("createUser()");
        User user = new User("Malvika", "munmun");
        //        URI uri = uriInfo.getAbsolutePathBuilder().path(user.getName()).build();
        //        Response.created(uri).build();
        try {
            userService.CreateUser(user);
        } catch (ClassNotFoundException e) {
            LOG.error(e.getLocalizedMessage());
        } catch (SQLException e) {
            LOG.error(e.getLocalizedMessage());
        }
        //        return Response.ok().entity(new Viewable("/success")).build();
        return Response.ok(new Viewable("/success")).build();
    }

}
