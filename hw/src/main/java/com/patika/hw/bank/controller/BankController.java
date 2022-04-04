package com.patika.hw.bank.controller;

import com.patika.hw.bank.model.Bank;
import com.patika.hw.bank.service.BankService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/bank")
@RestController
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping
    public void addBank(@Valid @NonNull @RequestBody Bank bank) {
        bankService.addBank(bank);
    }

    @GetMapping
    public List<Bank> getAllBanks() {
        return bankService.getAllBanks();
    }

    @GetMapping(path = "{id}")
    public Bank getBankById(@PathVariable("id") UUID id) {
        return bankService.getBankById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteBank(@PathVariable("id") UUID id) {
        bankService.deleteBank(id);
    }

    @PutMapping(path = "{id}")
    public void updateBank(@PathVariable("id") UUID id,
                           @Valid @NonNull @RequestBody Bank bankToUpdate) {
        bankService.updateBank(id, bankToUpdate);
    }

    @PutMapping(path = "{id}/edit-amount/{amount}")
    public void updateBankAmount(@PathVariable("id") UUID id,
                                 @PathVariable("amount") Double newAmount) {
        bankService.updateBankAmount(id, newAmount);
    }


}
