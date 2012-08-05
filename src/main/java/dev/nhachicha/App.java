package dev.nhachicha;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

/**
 * @author Nabil HACHICHA
 */
public class App extends Thread
{
	@Override
	public void run() {
		try {
			// Init grizzly to launch our Rest service via Jersey
			ResourceConfig rc = new PackagesResourceConfig("dev.nhachicha");
			HttpServer server = GrizzlyServerFactory.createHttpServer(
							UriBuilder.fromUri("http://localhost/").port(9998).build(), rc);
			server.start();
			
			synchronized(this) {//CTRL-C to stop the server
			    while (true) {
			        this.wait();
			    }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public static void main( String[] args ) throws Exception
    {
    	Thread t = new App();
    	t.start();

    }
}
