package com.example.bank.clint.gql;

import com.example.bank.clint.Client;
import com.example.bank.clint.ClientService;
import com.example.bank.clint.client_inputs.ClientInput;
import com.example.bank.clint.client_projections.AccSummaryDTO;
import com.example.bank.clint.client_projections.ClientWithOutPinDTO;
import com.example.bank.clint.client_projections.NameDTO;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


@Controller
public class ClientGraphQlController {

    private final ClientService clientService;

    public ClientGraphQlController(ClientService clientService) {
        this.clientService = clientService;
    }


    @QueryMapping
    public String greeting() {
        return "Hey there";
    }

    @QueryMapping
    public Iterable<NameDTO> names() {
        return clientService.findAllNames();
    }

    @QueryMapping
    public Iterable<AccSummaryDTO> accSummaryDTOS() {
        return clientService.findAccountSummaryDTOs();
    }

    @QueryMapping
    Iterable<ClientWithOutPinDTO> findAllClientDTOs() {
        return clientService.findAllClientDTOs();
    }

    @QueryMapping
    ClientWithOutPinDTO findClientDTOById(@Argument Long id) {
        return clientService.findClientDTOById(id);
    }

    @QueryMapping
    Double findAccountBalanceById(@Argument Long id) {
        return clientService.findClientBalanceById(id);
    }

    @MutationMapping
    public Client createClient(@Valid @Argument ClientInput client) {
        return clientService.save(client);
    }

    @MutationMapping
    Boolean updatePinCode(@Argument Long id, @Argument String newPinCode) {
        return clientService.updatePinCode(id, newPinCode);
    }

    @MutationMapping
    Boolean deleteClient(@Argument Long id) {
        return clientService.deleteClient(id);
    }


}
