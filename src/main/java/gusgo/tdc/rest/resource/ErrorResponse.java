package gusgo.tdc.rest.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private List<String> errors;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static ErrorResponse withErrors(List<String> errors) {
        ErrorResponse response = new ErrorResponse();
        response.setErrors(errors);
        return response;
    }

}
