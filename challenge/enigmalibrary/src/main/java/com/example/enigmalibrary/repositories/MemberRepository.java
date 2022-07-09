package com.example.enigmalibrary.repositories;

import com.example.enigmalibrary.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRepository extends JpaRepository<Member, String>, JpaSpecificationExecutor {
}
