package com.tmt.service;

import com.tmt.enitty.Account;
import com.tmt.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Index;
import javax.persistence.criteria.CriteriaBuilder;
import javax.sound.midi.MidiFileFormat;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepo;

    public AccountService(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    public List<Account> getAll() {
        return this.accountRepo.findAll();
    }

    public Optional<Account> getById(Integer accountId) {
        //validation
        if (accountId <= 0) {
            throw new RuntimeException("Id is invalid");
        }

        return this.accountRepo.findById(accountId);
    }

    public Account createAccount(Account newAccount) {
        String email = newAccount.getEmail();
        String userName = newAccount.getUserName();

        Optional<Account> checkByEmail = this.accountRepo.findByEmail(email);
        if (checkByEmail.isPresent()) {
            throw new RuntimeException("email already exists");
        }

        Optional<Account> checkByUserName = this.accountRepo.findByUserName(userName);
        if (checkByUserName.isPresent()) {
            throw new RuntimeException("User Name already exists");
        }

        return this.accountRepo.save(newAccount);
    }

    public Account updateAccount(Account newAccount) {
        Integer id = newAccount.getId();
        if (id <= 0 ) {
            throw new RuntimeException("id is invalid");
        }

        Optional<Account> checkAccount = this.accountRepo.findById(id);

        if (checkAccount.isEmpty()) {
            throw new RuntimeException("Account not found");
        }

        return this.accountRepo.save(newAccount);
    }

    public void deleteAccount(Integer accountId) {
        if (accountId <= 0) {
            throw new RuntimeException("id is invalid");
        }

        Optional<Account> checkAccount = this.accountRepo.findById(accountId);
        if (checkAccount.isEmpty()) {
            throw new RuntimeException("Account not found");
        }

        this.accountRepo.deleteById(accountId);
    }

}
