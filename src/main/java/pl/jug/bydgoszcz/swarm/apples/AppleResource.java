package pl.jug.bydgoszcz.swarm.apples;

import org.wildfly.swarm.jaxrs.btm.zipkin.ClientRequestInterceptor;
import org.wildfly.swarm.jaxrs.btm.zipkin.ClientResponseInterceptor;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
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

    @Inject
    @ConfigurationValue("nutrition.url")
    private String nutritionServiceUrl;

    @GET
    public List<Apple> getApples() {
        return dao.getAll();
    }

    @POST
    @Transactional
    public Apple addApple(Apple apple) {
        apple.setCalories(getCalories(apple.getName()));
        return dao.save(apple);
    }

    private Integer getCalories(String name) {
        String url = String.format("%s?name=%s", nutritionServiceUrl, name);
        Response response = ClientBuilder.newClient()
                .target(url)
                .register(ClientResponseInterceptor.class)
                .register(ClientRequestInterceptor.class)
                .request().get();

        if (response.getStatus() != 200) {
            throw new RuntimeException("Unable to get calories from URL: " + url);
        }
        return response.readEntity(Integer.class);
    }

}
