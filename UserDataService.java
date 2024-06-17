import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserDataService {

    private final Map<String, String> userDataMap = new HashMap<>();

    public void saveUserData(String username, String userData) {
        userDataMap.put(username, userData);
    }

    public String getUserData(String username) {
        return userDataMap.get(username);
    }

    public void deleteUserData(String username) {
        userDataMap.remove(username);
    }
}
