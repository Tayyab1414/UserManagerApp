import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRegister() {
        String requestBody = "{\"username\":\"integrationUser\",\"password\":\"password123\",\"email\":\"integration@example.com\"}";
        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register", requestBody, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testLogin() {
        String requestBody = "{\"username\":\"integrationUser\",\"password\":\"password123\"}";
        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login", requestBody, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSaveData() {
        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/saveData?username=integrationUser&data=sampleData", null, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetData() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/users/getData?username=integrationUser", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteData() {
        ResponseEntity<String> response = restTemplate.exchange("/api/users/deleteData?username=integrationUser", HttpMethod.DELETE, null, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
