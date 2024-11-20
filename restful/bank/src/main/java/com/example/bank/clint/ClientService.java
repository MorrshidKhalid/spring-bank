package com.example.bank.clint;

import com.example.bank.clint.client_projections.AccSummaryDTO;
import com.example.bank.clint.client_projections.BalanceProjection;
import com.example.bank.clint.client_projections.ClientWithOutPinDTO;
import com.example.bank.clint.client_projections.NameDTO;
import com.example.bank.clint.client_inputs.ClientInput;
import com.example.bank.exceptions.BadRequestException;
import com.example.bank.exceptions.NoContentException;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Iterable<Client> findAll() {
        // Check if data exists orElse throw exception.
        isFound();

        return clientRepository.findAll();
    }

    public Iterable<ClientWithOutPinDTO> findAllClientDTOs() {

        // Check if data exists orElse throw exception.
        isFound();

        return clientRepository.findAllBy(ClientWithOutPinDTO.class);
    }

    public ClientWithOutPinDTO findClientDTOById(Long id) {

        // Check id and client existence.
        isValidOrExists(id);

        return clientRepository.findById(ClientWithOutPinDTO.class, id);
    }

    public Client findClientById(Long id) {

        // Check id and client existence.
        isValidOrExists(id);

        return clientRepository.findById(id).get();
    }

    public Boolean existsById(Long id) {
        return clientRepository.existsById(id);
    }

    public Client save(ClientInput client) {

        Client newClient = new Client();
        newClient.setAccountNo(client.accountNo());
        newClient.setPinCode(client.pinCode());
        newClient.setFirstName(client.firstName());
        newClient.setSecondName(client.secondName());
        newClient.setLastName(client.lastName());
        newClient.setEmail(client.email());
        newClient.setAccountBalance(client.accountBalance());

        return clientRepository.save(newClient);
    }

    public Boolean updatePinCode(Long clientId, String newPinCode) {

        if (clientId <= 0 || newPinCode == null || newPinCode.isEmpty())
            throw new BadRequestException("Invalid input, please make sure you insert the correct info.");

        return clientRepository.updatePinCode(newPinCode, clientId) != -1;
    }

    public Double findClientBalanceById(Long clientId) {

        // Check id and client existence.
        isValidOrExists(clientId);

        return clientRepository.findById(BalanceProjection.class, clientId).getAccountBalance();
    }

    public void updateBalance(Long clientId, Double newBalance) {

        // Check id and client existence.
        isValidOrExists(clientId);

        Client client = clientRepository.findById(clientId).get();
        client.setAccountBalance(newBalance);

        clientRepository.save(client);
    }

    public Boolean deleteClient(Long clientId) {

        // Check id and client existence.
        isValidOrExists(clientId);

        clientRepository.delete(clientRepository.findById(clientId).get());
        return true;
    }

    public Iterable<NameDTO> findAllNames() {

        isFound(); // Check if data exists orElse throw exception.
        return clientRepository.findAllBy(NameDTO.class);
    }

    public Iterable<AccSummaryDTO> findAccountSummaryDTOs() {

        isFound(); // Check if data exists orElse throw exception.
        return clientRepository.findAllBy(AccSummaryDTO.class);
    }


    private void isFound() {
        if (clientRepository.existsAny() < 1)
            throw new NoContentException("Sorry, there is no available clients data to show.");
    }

    private void isValidOrExists(Long id) {

        if (id <= 0)
            throw new BadRequestException("Not excepted id (" + id + ").");

        if (!clientRepository.existsById(id)) {
            throw new NoContentException("Sorry, client with id (" + id + ") not found.");
        }
    }
}