import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CognitoIdentityProviderException.class)
    public ResponseEntity<String> handleCognitoException(CognitoIdentityProviderException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Autentiseringsfel: " + e.awsErrorDetails().errorMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ett okänt fel inträffade: " + e.getMessage());
    }
}
