package com.patika.hw.bank.repository;

import com.patika.hw.bank.model.Bank;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("bankDao")
public class BankDataAccessRepository implements BankDao {
    private static List<Bank> DB = new ArrayList<>();

    @Override
    public int insertBank(UUID id, Bank bank) {
        DB.add(new Bank(id, bank.getName()));
        return 1;
    }

    @Override
    public List<Bank> selectAllBanks() {
        return DB;
    }

    @Override
    public Optional<Bank> selectBankById(UUID id) {
        return DB.stream()
                .filter(bank -> bank.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteBankById(UUID id) {
        Optional<Bank> bankMaybe = selectBankById(id);
        if (bankMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(bankMaybe.get());
        return 0;
    }

    @Override
    public int updateBankById(UUID id, Bank update) {
        return selectBankById(id)
                .map(bank -> {
                    int indexOfBankToUpdate = DB.indexOf(bank);
                    if (indexOfBankToUpdate >= 0) {
                        DB.set(indexOfBankToUpdate, new Bank(id, update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public int updateBankAmountById(UUID id, Double newBankAmount) {
        return selectBankById(id)
                .map(bank -> {
                    int indexOfBankToUpdate = DB.indexOf(bank);
                    if (indexOfBankToUpdate >= 0) {
                        bank.setBankAmount(newBankAmount);
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
