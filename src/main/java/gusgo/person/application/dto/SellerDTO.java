package gusgo.person.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerDTO {

    private String id;

    @Size(max = 255, message = "name must be at most 255 characters long")
    @NotEmpty(message = "cannot be empty")
    private String name;

    @Size(max = 255, message = "erpId must be at most 255 characters long")
    @NotEmpty(message = "cannot be empty")
    private String erpId;

}
