package pl.jug.bydgoszcz.swarm.apples;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.arquillian.DefaultDeployment;

import javax.inject.Inject;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * <br>
 * Date: 10/19/17
 */
@RunWith(Arquillian.class)
@DefaultDeployment
public class AppleResourceTest {

    @Inject
    private AppleResource resource;

    @Test
    public void shouldAndGetApple() {
        Apple apple = new Apple();
        apple.setName("cortland");
        resource.addApple(apple);

        Optional<Apple> maybeCortland = resource.getApples()
                .stream()
                .filter(a -> "cortland".equals(a.getName()))
                .findAny();

        assertTrue(maybeCortland.isPresent());
        assertNull(maybeCortland.get().getDescription());
    }
}
