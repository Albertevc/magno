package br.com.magna.customer.controller;

import br.com.magna.customer.domain.CustomerCreationDTO;
import br.com.magna.customer.domain.CustomerUpdateDTO;
import br.com.magna.customer.domain.CustomerVO;
import br.com.magna.customer.domain.entity.Customer;
import br.com.magna.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Find one customer")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer Found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerVO.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Customer not found",
                    content = @Content
            )
    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<CustomerVO> customer(@PathVariable("id") Long id) {
        return ResponseEntity.of(
                this.customerService.get(id)
        );
    }

    @Operation(summary = "List all customers, paged")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Listed successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerVO.class)
                            )
                    }
            )
    }
    )
    @GetMapping
    public ResponseEntity<Page<CustomerVO>> customers(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(
                this.customerService.getAll(pageable)
        );
    }

    @Operation(summary = "Create one customer")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Customer created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Customer.class)
                            )
                    }
            )
    }
    )
    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody CustomerCreationDTO customerCreationDTO) {
        return new ResponseEntity<>(
                this.customerService.save(customerCreationDTO),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Update given customer")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Customer.class)
                            )
                    }
            )
    }
    )
    @PutMapping
    public ResponseEntity<Customer> update(@RequestBody CustomerUpdateDTO customerUpdateDTO) {
        return ResponseEntity.ok(
                this.customerService.save(customerUpdateDTO)
        );
    }

    @Operation(summary = "Delete referenced customer")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer deleted"
            )
    }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.customerService.softDelete(id);
        return ResponseEntity.ok()
                .build();
    }

}
