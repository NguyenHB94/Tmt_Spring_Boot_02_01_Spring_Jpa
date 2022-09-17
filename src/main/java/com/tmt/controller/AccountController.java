package com.tmt.controller;

import com.tmt.enitty.Account;
import com.tmt.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public ResponseEntity<List<Account>> getAll() {
        var ret = this.accountService.getAll();
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Account>> getById(@PathVariable("id") Integer accountId) {
        try {
            var ret = this.accountService.getById(accountId);
            return ResponseEntity.ok(ret);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody Account newAccount) {
       try {
           var ret = this.accountService.createAccount(newAccount);
           return ResponseEntity.ok("Create success");
       } catch (Exception e) {
           System.out.println(e.getMessage());
           return ResponseEntity.internalServerError().body("Creat fail");
       }
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateAccount(@RequestBody Account newAccount) {
        try {
            var ret = this.accountService.updateAccount(newAccount);
            return ResponseEntity.ok("Update success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body("Update fail");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccoutn(@PathVariable("id") Integer accountId) {
        try {
            this.accountService.deleteAccount(accountId);
            return ResponseEntity.ok("Delete Success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body("Delete fail");
        }
    }

}
