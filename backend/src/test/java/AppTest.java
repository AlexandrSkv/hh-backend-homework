import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.dto.Employer.FavoriteEmployerDto;

import javax.ws.rs.core.Response;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = AppTestConfig.class)
public class AppTest extends NabTestBase {

  @Override
  protected NabApplication getApplication() {
    return NabApplication.builder().configureJersey().bindToRoot().build();
  }

  @Before
  public void before() {}

  @Test
  public void createUserAndCompanyTest() {
    Response response = createRequest("/")
            .buildGet()
            .invoke();

    assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
  }


  @Test
  public void countTest() throws JsonProcessingException, InterruptedException {

    Integer count = 1000;
    String url = "http://localhost:8080/favorites/employer";
    RestTemplate restTemplate = new RestTemplate();
    ExecutorService executor = Executors.newFixedThreadPool(4);

    for (int i = 0; i < count; i++) {
      executor.submit(() -> restTemplate.getForObject(url, String.class));
    }

    executor.shutdown();
    executor.awaitTermination(60, TimeUnit.SECONDS);

    String response = restTemplate.getForObject(url, String.class);

    ObjectMapper mapper = new ObjectMapper();

    List<FavoriteEmployerDto> favoriteEmployerDto = mapper.readValue(response, new TypeReference<List<FavoriteEmployerDto>>(){});

    Integer value = favoriteEmployerDto.get(0).getViews_count();

    count += 1;

    assertEquals(count, value);

  }
}
