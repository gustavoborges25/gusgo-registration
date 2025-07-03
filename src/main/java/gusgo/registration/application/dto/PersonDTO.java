package gusgo.registration.application.dto;

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
    @NotEmpty(message = "cannot be empty")
    private String nickname;

    @NotNull(message = "is mandatory")
    @Pattern(regexp = "YES|NO", message = "invalid value. Accept only YES or NO.")
    private String isCustomer;

    @NotNull(message = "is mandatory")
    @Pattern(regexp = "YES|NO", message = "invalid value. Accept only YES or NO.")
    private String isProvider;

    @NotNull(message = "is mandatory")
    @Pattern(regexp = "YES|NO", message = "invalid value. Accept only YES or NO.")
    private String isBranch;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "cannot be empty")
    @Size(max = 255, message = "erpId must be at most 255 characters long")
    private String erpId;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "cannot be empty")
    private SellerDTO seller;

    @Pattern(regexp = "BUSINESS|INDIVIDUAL", message = "invalid value. Accept only BUSINESS or INDIVIDUAL.")
    private String type;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "cannot be empty")
    @Size(max = 255, message = "mainDocument must be at most 255 characters long")
    private String mainDocument;

    @Size(max = 255, message = "secondaryDocument must be at most 255 characters long")
    private String secondaryDocument;

    @Pattern(regexp = "ACTIVE|INACTIVE", message = "invalid value. Accept only ACTIVE or INACTIVE.")
    private String status;

    private List<AddressDTO> addresses;

    private List<PhoneDTO> phones;

    private List<EmailDTO> emails;
}
