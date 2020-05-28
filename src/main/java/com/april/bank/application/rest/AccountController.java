package com.april.bank.application.rest;

import com.april.bank.application.request.CreateAccountRequest;
import com.april.bank.application.request.TransferMoneyRequest;
import com.april.bank.application.response.CreateAccountResponse;
import com.april.bank.application.response.FindBalanceResponse;
import com.april.bank.domain.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<FindBalanceResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.find(id));
    }

    @PostMapping("/accounts")
    public ResponseEntity<CreateAccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(createAccountRequest));
    }

    @PutMapping("/deposit")
    public ResponseEntity deposit(@Valid @RequestBody TransferMoneyRequest transferMoneyRequest) {
        accountService.deposit(transferMoneyRequest);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/withdraw")
    public ResponseEntity withdraw(@Valid @RequestBody TransferMoneyRequest transferMoneyRequest) {
        accountService.withdraw(transferMoneyRequest);
        return ResponseEntity.noContent().build();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorDefaultMessage = error.getDefaultMessage();
            errors.put(fieldName, errorDefaultMessage);
        });

        return errors;
    }
}
