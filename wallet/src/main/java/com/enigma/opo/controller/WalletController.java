package com.enigma.opo.controller;

import com.enigma.opo.constant.ResponseMessage;
import com.enigma.opo.entity.Wallet;
import com.enigma.opo.service.WalletService;
import com.enigma.opo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class WalletController {

    @Autowired
    WalletService walletService;

    @PostMapping("/wallets")
    public ResponseEntity<Response<Wallet>> createWallet(@RequestBody Wallet wallet){

        Response<Wallet> response = new Response<>();

        String message = String.format(ResponseMessage.DATA_INSERT, "Wallet");
        response.setMessage(message);
        response.setData(walletService.createWallet(wallet));
        ResponseEntity<Response<Wallet>> wa = ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
        System.out.println("RESPINSE WALLET" + wa);
        return wa;
    }

    @GetMapping("wallets")
    public List<Wallet> findAllWallet(){
        return walletService.findAllWallet();
    }

    @PutMapping("debit")
    public void debit(@RequestParam(name = "phoneNumber") String phoneNumber,
                      @RequestParam(name = "amount") BigDecimal amount){
        walletService.debit(phoneNumber, amount);
    }
}