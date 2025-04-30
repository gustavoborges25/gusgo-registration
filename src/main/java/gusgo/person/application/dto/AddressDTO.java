package gusgo.person.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {

    private UUID id;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "is mandatory")
    @Size(max = 255, message = "street must be at most 255 characters long")
    private String street;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "is mandatory")
    @Size(max = 255, message = "number must be at most 255 characters long")
    private String number;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "is mandatory")
    @Size(max = 255, message = "neighborhood must be at most 255 characters long")
    private String neighborhood;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "is mandatory")
    @Size(max = 255, message = "zipCode must be at most 255 characters long")
    private String zipCode;

    @Size(max = 255, message = "complement must be at most 255 characters long")
    private String complement;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "is mandatory")
    @Size(max = 255, message = "state must be at most 255 characters long")
    private String state;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "is mandatory")
    @Size(max = 255, message = "city must be at most 255 characters long")
    private String city;
}
