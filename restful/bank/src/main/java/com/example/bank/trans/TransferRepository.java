package com.example.bank.trans;

import com.example.bank.trans.trans_projections.TransDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

    @Query(value = "SELECT 1 FROM Transfer LIMIT 1", nativeQuery = true)
    Integer existsAny();

    @Query("SELECT t.moveType AS moveType, t.amount AS amount, c.code AS code FROM Transfer t JOIN t.currency c")
    List<TransDTO> findTransDTOs();

}
