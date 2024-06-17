import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class SessionManager {

    private Map<String, String> userSessionMap = new HashMap<>();

    public void setUserSession(String userId, String sessionId) {
        userSessionMap.put(userId, sessionId);
    }

    public String getSessionId(String userId) {
        return userSessionMap.get(userId);
    }

    public void invalidateSession(String userId) {
        userSessionMap.remove(userId);
    }

    public boolean isSessionValid(String userId) {
        return userSessionMap.containsKey(userId);
    }
}
