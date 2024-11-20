package com.example.bank.log;

import com.example.bank.clint.Client;
import com.example.bank.exceptions.NoContentException;
import com.example.bank.log.log_projections.LogDTO;
import com.example.bank.trans.Transfer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }


    public List<LogDTO> findAllLogs() {

        if (logRepository.count() == 0)
            throw new NoContentException("No logs found");

        return logRepository.findLogDTOs();
    }

    public void save(Transfer transfer, Client client) {

        Log log = new Log();
        log.setClient(client);
        log.setTransfer(transfer);
        log.setDateTime(LocalDateTime.now());

        logRepository.save(log);
    }
}