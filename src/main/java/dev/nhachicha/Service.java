package dev.nhachicha;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.gson.Gson;
import com.sun.jersey.api.json.JSONWithPadding;
/**
 * @author Nabil HACHICHA
 */

@Path("/rest")
public class Service {

	@GET
	@Produces("application/x-javascript")
	@Path("greeting/{username}")
	public JSONWithPadding sayHello (@QueryParam("callback") String callback,
								 @PathParam("username") String username) {
		Gson gson = new Gson();
		Message msg = new Message("Hello " + username);
		String json = gson.toJson(msg);
		return new JSONWithPadding(json,callback);
	}
}
