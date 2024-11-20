package com.example.bank.trans;

import com.example.bank.clint.Client;
import com.example.bank.clint.ClientService;
import com.example.bank.currency.Currency;
import com.example.bank.currency.CurrencyRepository;
import com.example.bank.exceptions.NoContentException;
import com.example.bank.log.LogService;
import com.example.bank.trans.trans_inputs.TrnasInput;
import com.example.bank.trans.trans_projections.TransDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

import java.util.List;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final CurrencyRepository currencyRepository;
    private final ClientService clientService;
    private final LogService logService;

    public TransferService(TransferRepository transferRepository, CurrencyRepository currencyRepository, ClientService clientService, LogService logService) {
        this.transferRepository = transferRepository;
        this.currencyRepository = currencyRepository;
        this.clientService = clientService;
        this.logService = logService;
    }


    public List<Transfer> getAllTransfers() {

        isDataFound();
        return transferRepository.findAll();
    }

    public List<TransDTO> getTransferDTOs() {

        isDataFound();
        return transferRepository.findTransDTOs();
    }

    public Boolean deposit(TrnasInput trnasInput) {

        isInfoValid(trnasInput);

        // updated balance.
        Double updatedBalance = clientService.findClientBalanceById(trnasInput.clientId());
        updatedBalance += trnasInput.amount();
        clientService.updateBalance(trnasInput.clientId(), updatedBalance);


        Currency currency = currencyRepository.findById(trnasInput.currencyId()).get();
        Transfer savedTransfer = new Transfer();

        savedTransfer.setAmount(trnasInput.amount());
        savedTransfer.setMoveType(trnasInput.moveType());
        savedTransfer.setCurrency(currency);

        transferRepository.save(savedTransfer);

        // Save to log table.
        Client client = clientService.findClientById(trnasInput.clientId());
        logService.save(savedTransfer, client);

        return true;
    }

    public Boolean withdrawal(TrnasInput trnasInput) {

        isInfoValid(trnasInput);

        Double accountBalance = clientService.findClientBalanceById(trnasInput.clientId());

        if (trnasInput.amount() > accountBalance) {
            throw new NotAcceptableStatusException("Transfer amount exceeds account balance");
        }

        // updated balance.
        accountBalance -= trnasInput.amount();

        clientService.updateBalance(trnasInput.clientId(), accountBalance);


        Transfer savedTransfer = new Transfer();
        savedTransfer.setAmount(trnasInput.amount());
        savedTransfer.setMoveType(trnasInput.moveType());
        savedTransfer.setCurrency(currencyRepository.findById(trnasInput.currencyId()).get());

        // Save to log table.
        Client client = clientService.findClientById(trnasInput.clientId());
        logService.save(savedTransfer, client);

        return true;
    }

    private void isDataFound() {
        if (transferRepository.existsAny() < 1) {
            throw new NoContentException("No transfers found");
        }
    }

    private void isInfoValid(TrnasInput input) {

        if (!currencyRepository.existsById(input.currencyId()))
            throw new NoContentException("Currency with Id (" + input.currencyId() + ") not found." );

        if (!clientService.existsById(input.clientId()))
            throw new NoContentException("Client with Id (" + input.clientId() + ") not found." );

    }
}