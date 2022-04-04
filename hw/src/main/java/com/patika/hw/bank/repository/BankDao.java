package com.patika.hw.bank.repository;

import com.patika.hw.bank.model.Bank;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankDao {

    int insertBank(UUID id, Bank bank);

    default int insertBank(Bank bank) {
        UUID id = UUID.randomUUID();
        return insertBank(id, bank);
    }

    List<Bank> selectAllBanks();

    Optional<Bank> selectBankById(UUID id);

    int deleteBankById(UUID id);

    int updateBankById(UUID id, Bank bank);

    int updateBankAmountById(UUID id, Double newBalance);
}
