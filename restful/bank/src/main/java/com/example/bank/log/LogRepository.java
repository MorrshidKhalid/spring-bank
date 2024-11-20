package com.example.bank.log;

import com.example.bank.log.log_projections.LogDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {

    @Query("SELECT l.dateTime as dateTime, c.accountNo as accountNo, t.moveType as moveType, t.amount as amount, u.code as code FROM Log l JOIN l.client c JOIN l.transfer t JOIN l.transfer.currency u")
    List<LogDTO> findLogDTOs();
}
