package pl.jug.bydgoszcz.swarm.apples;

import org.wildfly.swarm.topology.Advertise;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * <br>
 * Date: 10/18/17
 */
@Path("/alive")
@Advertise(value = "apple-service", tags = {"rest", "swarm", "apples"})
public class AliveEndpoint {
    @GET
    public Response isAlive() {
        return Response.ok().build();
    }
}
