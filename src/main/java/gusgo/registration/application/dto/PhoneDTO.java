package gusgo.registration.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneDTO {

    private UUID id;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "cannot be empty")
    @Size(max = 255, message = "phone must be at most 255 characters long")
    private String phone;

    @Size(max = 255, message = "contact must be at most 255 characters long")
    private String contact;

    @Size(max = 255, message = "note must be at most 255 characters long")
    private String note;
}
