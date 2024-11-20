package com.example.bank.phone;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/num/")
public class PhoneController {

    private final PhoneRepository phoneRepository;

    public PhoneController(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addClientNumber(@RequestBody Phone phone) {
        phoneRepository.save(phone);
    }


    @DeleteMapping
    public void deleteClientNumber(Long id) {
        phoneRepository.deleteById(id);
    }
}