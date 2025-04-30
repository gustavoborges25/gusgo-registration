package gusgo.person.rest.controller.v1;

import gusgo.person.application.dto.PersonDTO;
import gusgo.person.application.interfaces.PersonService;
import gusgo.person.rest.exception.BusinessException;
import gusgo.person.rest.resource.RestResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/people")
@Tag(name = "People", description = "Operations for people")
public class PersonController {

    private final PersonService personService;

    @Operation(summary = "Find all people")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Return all configuration",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping
    public ResponseEntity<RestResponseDTO<List<PersonDTO>>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new RestResponseDTO<>(personService.findAll()));
    }

    @Operation(summary = "Create a new person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person created"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<RestResponseDTO<PersonDTO>> create(@Valid @RequestBody PersonDTO personDTO) throws BusinessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestResponseDTO<>(personService.create(personDTO)));
    }

    @Operation(summary = "Update a person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person created"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RestResponseDTO<PersonDTO>> update(@PathVariable UUID id, @Valid @RequestBody PersonDTO personDTO) throws BusinessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestResponseDTO<>(personService.update(id, personDTO)));
    }

    @Operation(summary = "Create people by upload file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person created"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/upload-bp")
    public ResponseEntity<RestResponseDTO<Void>> uploadExcelFile(@RequestParam("file") MultipartFile file) throws BusinessException {
        personService.uploadBp(file);
        return ResponseEntity.ok().build();
    }


}
