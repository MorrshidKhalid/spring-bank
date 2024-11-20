package com.example.bank.clint;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

    @Query(value = "SELECT 1 FROM Client LIMIT 1", nativeQuery = true)
    Integer existsAny();

    @Modifying
    @Query("update Client c set c.pinCode = ?1 where c.id = ?2")
    Integer updatePinCode(String pinCode, Long clientId);

    <T> Iterable<T> findAllBy(Class<T> type);
    <T> T findById(Class<T> type, Long id);
}

/*
//    @Query("SELECT p.number FROM Phone p WHERE p.client.id = :clientId")
//    List<String> findPhoneNumbersByClientId(@Param("clientId") Long clientId);

//    @Query("SELECT c.id AS id, c.accountNo AS accountNo, c.firstName AS firstName, c.secondName AS secondName, c.lastName AS lastName, c.email AS email, c.accountBalance AS accountBalance FROM Client c")
//    List<ClientWithOutPinDTO> findAllClientDTOs();

//    @Query("SELECT c.id AS id, c.accountNo AS accountNo, c.firstName AS firstName, c.secondName AS secondName, c.lastName AS lastName, c.email AS email, c.accountBalance AS accountBalance FROM Client c WHERE c.id = ?1")
//    ClientWithOutPinDTO findClientDTOById(Long clientId);

//    @Query("SELECT c.accountBalance as accountBalance FROM Client c WHERE c.id = ?1")
//    Double findBalanceById(Long clientId);
*/