package com.example.bank.trans;

import com.example.bank.trans.trans_inputs.TrnasInput;
import com.example.bank.trans.trans_projections.TransDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/trans/")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully data retrieved."),
            @ApiResponse(responseCode = "204", description = "Successfully request, but data dose not exists.")
    })
    @GetMapping("transfers")
    List<Transfer> findAll() {
        return transferService.getAllTransfers();
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully data retrieved."),
            @ApiResponse(responseCode = "204", description = "Successfully request, but data dose not exists.")
    })
    @GetMapping("dtos")
    List<TransDTO> findTransferDTOs() {
        return transferService.getTransferDTOs();
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully Deposit"),
            @ApiResponse(responseCode = "204", description = "Successfully request, but the (client or currency) dose not exists."),
            @ApiResponse(responseCode = "404", description = "Not valid input")
    })
    @PostMapping("deposit")
    Boolean deposit(@Valid @RequestBody TrnasInput transfer) {
        return transferService.deposit(transfer);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully Deposit"),
            @ApiResponse(responseCode = "204", description = "Successfully request, but the (client or currency) dose not exists."),
            @ApiResponse(responseCode = "404", description = "Not valid input"),
            @ApiResponse(responseCode = "406", description = "Not Acceptable, because there is no sufficient balance "),
    })
    @PostMapping("withdrawal")
    Boolean withdrawal(@Valid @RequestBody TrnasInput trnasInput) {
        return transferService.withdrawal(trnasInput);
    }

}
