import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDataServiceTest {

    private UserDataService userDataService = new UserDataService();

    @Test
    public void testSaveUserData() {
        String username = "testUser";
        String data = "sampleData";

        userDataService.saveUserData(username, data);
        assertEquals(data, userDataService.getUserData(username));
    }

    @Test
    public void testGetUserData() {
        String username = "testUser";
        String data = "sampleData";

        userDataService.saveUserData(username, data);
        assertEquals(data, userDataService.getUserData(username));
    }

    @Test
    public void testDeleteUserData() {
        String username = "testUser";
        String data = "sampleData";

        userDataService.saveUserData(username, data);
        userDataService.deleteUserData(username);
        assertNull(userDataService.getUserData(username));
    }
}
