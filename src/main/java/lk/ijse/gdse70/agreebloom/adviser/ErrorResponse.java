package lk.ijse.gdse70.agreebloom.adviser;

//often called controller adviser it uses to handle exception and customize response for globally for controller
/*
centerlize error handling , validation and response formatting
 */

/*
Builder enables builder pattern for the class allowing you to create instances in a flexible and readable way , used for class with many fields
 */
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private List<String> errors;

}
