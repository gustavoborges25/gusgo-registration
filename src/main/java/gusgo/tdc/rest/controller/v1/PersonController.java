package gusgo.tdc.rest.controller.v1;

import gusgo.tdc.application.dto.PersonDto;
import gusgo.tdc.application.interfaces.PersonService;
import gusgo.tdc.rest.controller.BaseRestController;
import gusgo.tdc.rest.exception.BusinessException;
import gusgo.tdc.rest.resource.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/people")
@Tag(name = "People", description = "Operations for people")
public class PersonController extends BaseRestController {

    private final PersonService personService;

    @Operation(summary = "Find all people")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Return all configuration",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping
    public ResponseEntity<RestResponse<List<PersonDto>>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new RestResponse<>(personService.findAll()));
    }

    @Operation(summary = "Create a new person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person created"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<RestResponse<PersonDto>> create(@Valid @RequestBody PersonDto configurationDto) throws BusinessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestResponse<>(personService.create(configurationDto)));
    }

    @Operation(summary = "Create people by upload file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person created"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/upload")
    public ResponseEntity<RestResponse<Void>> uploadExcelFile(@RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok().build();
    }


}
