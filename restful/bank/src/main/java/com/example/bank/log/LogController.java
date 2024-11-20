package com.example.bank.log;

import com.example.bank.log.log_projections.LogDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/log/")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs Successfully retrieved."),
            @ApiResponse(responseCode = "204", description = "Successful request, but no logs exists.")
    })
    @GetMapping("logs")
    public List<LogDTO> getLogService() {
        return logService.findAllLogs();
    }
}
