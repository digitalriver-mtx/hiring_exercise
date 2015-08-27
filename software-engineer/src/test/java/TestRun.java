import com.drmtx.app.Application;
import com.drmtx.frequency.FrequencyFactory;
import com.drmtx.frequency.Point;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antivo on 8/27/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class TestRun {
    private static final String API1 = "http://localhost:8080/frequency/new";
    private static final String POST_ARGUMENT = "https://www.reddit.com/r/java/comments/32pj67/java_reference_in_gta_v_beautiful/.json";
    private static final String API2 = "http://localhost:8080/frequency/1/count?number_of_results=5";

    @Test
    public void commentsToFrequency() {
        RestTemplate restTemplate = new RestTemplate();
        //Long id = restTemplate.postForEntity(API1, POST_ARGUMENT, Long.class).getBody();

        String ss = restTemplate.getForEntity(API2, String.class).getBody();


    }
}
