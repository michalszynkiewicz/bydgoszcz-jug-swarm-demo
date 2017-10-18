package pl.jug.bydgoszcz.swarm.apples;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * <br>
 * Date: 10/18/17
 */
@Path("/alive")
public class AliveEndpoint {
    @GET
    public Response isAlive() {
        return Response.ok().build();
    }
}
