package com.enigma.opo.repository;

import com.enigma.opo.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String>, JpaSpecificationExecutor {
    public Wallet findWalletByPhoneNumber(String phoneNumber);
}
