package com.enigma.opo.service;

import com.enigma.opo.entity.Wallet;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService {
    public void debit(String phoneNumber, BigDecimal amount);
    public Wallet createWallet(Wallet wallet);
    public Wallet topUp(Wallet wallet);
    public Wallet topUpAmount(BigDecimal topUpAmount, String phoneNumber);
    public List<Wallet> findAllWallet();
}
