package com.enigma.opo.service.impl;

import com.enigma.opo.entity.Wallet;
import com.enigma.opo.repository.WalletRepository;
import com.enigma.opo.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Override
    public void debit(String phoneNumber, BigDecimal amount) {
        Wallet wallet = walletRepository.findWalletByPhoneNumber(phoneNumber);
        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletRepository.save(wallet);
    }

    @Override
    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet topUp(Wallet wallet) {
        return null;
    }

    @Override
    public Wallet topUpAmount(BigDecimal topUpAmount, String phoneNumber) {
        return null;
    }

    @Override
    public List<Wallet> findAllWallet() {
        return walletRepository.findAll();
    }
}
