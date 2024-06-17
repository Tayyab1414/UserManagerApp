import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private CognitoIdentityProviderClient cognitoClient;

    @Autowired
    private UserDataService userDataService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        AdminCreateUserRequest createUserRequest = AdminCreateUserRequest.builder()
                .userPoolId("eu-north-1_fHqEcg6RA")
                .username(request.getUsername())
                .temporaryPassword(request.getPassword())
                .userAttributes(AttributeType.builder().name("Tayyab.abbasi1414@gmail.com").value(request.getEmail()).build())
                .build();

        AdminCreateUserResponse response = cognitoClient.adminCreateUser(createUserRequest);
        return response.user().username();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Map<String, String> authParameters = new HashMap<>();
        authParameters.put("Tayyab", request.getUsername());
        authParameters.put("1414Xx621@", request.getPassword());

        AdminInitiateAuthRequest authRequest = AdminInitiateAuthRequest.builder()
                .userPoolId("eu-north-1_fHqEcg6RA")
                .clientId("4kt68qnkge6vvdbv1bvm2vhakl")
                .authFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .authParameters(authParameters)
                .build();

        AdminInitiateAuthResponse response = cognitoClient.adminInitiateAuth(authRequest);
        return response.authenticationResult().accessToken();
    }

    @PostMapping("/saveData")
    public String saveData(@RequestParam String username, @RequestParam String data) {
        userDataService.saveUserData(username, data);
        return "Data saved successfully.";
    }

    @GetMapping("/getData")
    public String getData(@RequestParam String username) {
        return userDataService.getUserData(username);
    }

    @DeleteMapping("/deleteData")
    public String deleteData(@RequestParam String username) {
        userDataService.deleteUserData(username);
        return "Data deleted successfully.";
    }
}
