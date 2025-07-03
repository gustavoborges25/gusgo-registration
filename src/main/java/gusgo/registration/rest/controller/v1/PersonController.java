package gusgo.registration.rest.controller.v1;

import gusgo.registration.application.dto.PersonDTO;
import gusgo.registration.application.interfaces.PersonService;
import gusgo.registration.rest.exception.BusinessException;
import gusgo.registration.rest.resource.RestResponseDTO;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
