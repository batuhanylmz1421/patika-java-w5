package com.patika.hw.bank.service;

import com.patika.hw.bank.model.Bank;
import com.patika.hw.bank.repository.BankDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankService {

    private final BankDao bankDao;

    @Autowired
    public BankService(@Qualifier("bankDao") BankDao bankDao) {
        this.bankDao = bankDao;
    }

    public int addBank(Bank bank) {
        return bankDao.insertBank(bank);
    }

    public List<Bank> getAllBanks() {
        return bankDao.selectAllBanks();
    }

    public Optional<Bank> getBankById(UUID id) {
        return bankDao.selectBankById(id);
    }

    public int deleteBank(UUID id) {
        return bankDao.deleteBankById(id);
    }

    public int updateBank(UUID id, Bank newBank) {
        return bankDao.updateBankById(id, newBank);
    }

    public int updateBankAmount(UUID id, Double newBankAmount) {
        return bankDao.updateBankAmountById(id, newBankAmount);
    }

}
