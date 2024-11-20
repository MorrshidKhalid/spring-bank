package com.example.bank.clint;

import com.example.bank.clint.client_projections.AccSummaryDTO;
import com.example.bank.clint.client_projections.ClientWithOutPinDTO;
import com.example.bank.clint.client_projections.NameDTO;
import com.example.bank.clint.client_inputs.ClientInput;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/client/")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("names")
    public Iterable<NameDTO> names() {
        return clientService.findAllNames();
    }


    @GetMapping("summary")
    public Iterable<AccSummaryDTO> accSummaryDTOS() {
        return clientService.findAccountSummaryDTOs();
    }


    @GetMapping("hey")
    public String sayHey() {
        return "Hey";
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of clients"),
            @ApiResponse(responseCode = "204", description = "Successful request, but no client data to retrieve"),
    })
    @GetMapping("all")
    Iterable<ClientWithOutPinDTO> getClients() {
        return clientService.findAllClientDTOs();
    }


    @GetMapping("allWithoutProjection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of clients"),
            @ApiResponse(responseCode = "204", description = "Successful request, but no client data to retrieve"),
    })
    Iterable<Client> findAllWithOutProjection() {
        return clientService.findAll();
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved client info"),
            @ApiResponse(responseCode = "204", description = "Successful request, but no client data to retrieve"),
            @ApiResponse(responseCode = "400", description = "Not valid id make sure the id is positive number and not (0).")
    })
    @GetMapping("id/{id}")
    ClientWithOutPinDTO findById(@PathVariable Long id) {
        return clientService.findClientDTOById(id);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved client info"),
            @ApiResponse(responseCode = "204", description = "Successful request, but no client data to retrieve"),
            @ApiResponse(responseCode = "400", description = "Not valid id make sure the id is positive number and not (0).")
    })
    @GetMapping("balance/{id}")
    Double findAcbById(@PathVariable Long id) {
        return clientService.findClientBalanceById(id);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully Created new record"),
            @ApiResponse(responseCode = "400", description = "invalid input"),
            @ApiResponse(responseCode = "406", description = "not excepted data make sure all filed are filled correctly"),
    })
    @PostMapping("create")
    public Client createClient(@Valid @RequestBody ClientInput client) {
        return clientService.save(client);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pin Code Successfully Changed."),
            @ApiResponse(responseCode = "204", description = "No phone number(s) found"),
            @ApiResponse(responseCode = "400", description = "invalid input, and to make sure you insert the correct info"),
    })
    @PutMapping("pin/{id}")
    Boolean updatePinCode(@PathVariable Long id, @RequestBody String newPinCode) {
        return clientService.updatePinCode(id, newPinCode);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted Successfully"),
            @ApiResponse(responseCode = "204", description = "No client found"),
            @ApiResponse(responseCode = "400", description = "invalid id"),
    })
    @DeleteMapping("delete/{id}")
    void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}