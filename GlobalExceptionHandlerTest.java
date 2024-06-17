import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    public void testHandleException() {
        Exception exception = new Exception("Test exception");
        ResponseEntity<String> response = exceptionHandler.handleException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Ett fel inträffade: Test exception", response.getBody());
    }
}
