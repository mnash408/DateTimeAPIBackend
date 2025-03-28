package com.nashm;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@Tag(name = "Date and Time Service", description = "Provides current date and time information")
public class DateTimeController {

    private final DateTimeRepository repository;

    @Autowired
    public DateTimeController(DateTimeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/current-datetime")
    @Operation(
            summary = "Get Current Date and Time",
            description = "Returns the current date and time and stores it in the database",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved and stored current date and time",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DateTimeResponse.class)
                            )
                    )
            }
    )
    public DateTimeResponse getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Create and save the database entry
        DateTimeEntry entry = new DateTimeEntry(now, formattedDateTime);
        repository.save(entry);

        return new DateTimeResponse(formattedDateTime);
    }

    @GetMapping("/entries")
    @Operation(
            summary = "Get All DateTime Entries",
            description = "Returns all date and time entries stored in the database",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all date and time entries"
                    )
            }
    )
    public List<DateTimeEntry> getAllEntries() {
        return repository.findAll();
    }
}