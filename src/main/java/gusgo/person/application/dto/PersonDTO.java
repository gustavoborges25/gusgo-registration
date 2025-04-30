package gusgo.person.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO {

    private String id;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "cannot be empty")
    @Size(max = 255, message = "name must be at most 255 characters long")
    private String name;

    @Size(max = 255, message = "nickname must be at most 255 characters long")
    private String nickname;

    @NotNull(message = "is mandatory")
    @Pattern(regexp = "YES|NO", message = "invalid value. Accept only YES or NO.")
    private String isCustomer;

    @NotNull(message = "is mandatory")
    @Pattern(regexp = "YES|NO", message = "invalid value. Accept only YES or NO.")
    private String isProvider;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "cannot be empty")
    @Size(max = 255, message = "erpCode must be at most 255 characters long")
    private String erpCode;

    @Pattern(regexp = "BUSINESS|INDIVIDUAL", message = "invalid value. Accept only BUSINESS or INDIVIDUAL.")
    private String type;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "cannot be empty")
    @Size(max = 255, message = "mainDocument must be at most 255 characters long")
    private String mainDocument;

    @Size(max = 255, message = "secondaryDocument must be at most 255 characters long")
    private String secondaryDocument;

    @NotEmpty(message = "cannot be empty")
    private List<AddressDTO> addresses;

    @NotEmpty(message = "cannot be empty")
    private List<PhoneDTO> phones;

    @NotEmpty(message = "cannot be empty")
    private List<EmailDTO> emails;
}
