package pl.jug.bydgoszcz.swarm.apples;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * <br>
 * Date: 10/18/17
 */
@Path("/apples")
@Produces("application/json")
@Consumes("application/json")
public class AppleResource {

    @Inject
    private AppleDao dao;

    @GET
    public List<Apple> getApples() {
        return dao.getAll();
    }

    @POST
    @Transactional
    public Apple addApple(Apple apple) {
        return dao.save(apple);
    }

}
