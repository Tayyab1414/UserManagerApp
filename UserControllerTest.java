import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CognitoIdentityProviderClient cognitoClient;

    @MockBean
    private UserDataService userDataService;

    @BeforeEach
    public void setUp() {
        // Set up any required mock behavior here
    }

    @Test
    public void testRegister() throws Exception {
        String requestBody = "{\"username\":\"testUser\",\"password\":\"password123\",\"email\":\"test@example.com\"}";

        mockMvc.perform(post("/api/users/register")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void testLogin() throws Exception {
        String requestBody = "{\"username\":\"testUser\",\"password\":\"password123\"}";

        mockMvc.perform(post("/api/users/login")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveData() throws Exception {
        mockMvc.perform(post("/api/users/saveData")
                        .param("username", "testUser")
                        .param("data", "sampleData"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetData() throws Exception {
        mockMvc.perform(get("/api/users/getData")
                        .param("username", "testUser"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteData() throws Exception {
        mockMvc.perform(delete("/api/users/deleteData")
                        .param("username", "testUser"))
                .andExpect(status().isOk());
    }
}
