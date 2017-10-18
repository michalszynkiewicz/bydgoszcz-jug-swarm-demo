package pl.jug.bydgoszcz.swarm.apples;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

/**
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * <br>
 * Date: 10/18/17
 */
@Path("/apples")
public class AppleEndpoint {

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
