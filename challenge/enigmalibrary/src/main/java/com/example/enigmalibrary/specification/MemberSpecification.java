package com.example.enigmalibrary.specification;

import com.example.enigmalibrary.dto.MemberSearchDTO;
import com.example.enigmalibrary.entities.Member;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.Boolean.FALSE;

public class MemberSpecification {
    public static Specification<Member> getSpecification(MemberSearchDTO memberSearchDTO){
        return new Specification<Member>() {
            @Override
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if(memberSearchDTO.getSearchMemberFirstName() != null){
                    Predicate memberFirstNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + memberSearchDTO.getSearchMemberFirstName().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(memberFirstNamePredicate);
                }

                if(memberSearchDTO.getSearchMemberLastName() != null){
                    Predicate memberLastNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + memberSearchDTO.getSearchMemberLastName().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(memberLastNamePredicate);
                }

                if(memberSearchDTO.getSearchMemberGender() != null){
                    Predicate memberGenderPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("gender")),"%" + memberSearchDTO.getSearchMemberGender() + "%");
                    predicates.add(memberGenderPredicate);
                }

                if(memberSearchDTO.getSearchMemberStartDateOfBirth() != null){
                    Predicate memberDateOfBirthPredicate = criteriaBuilder.between(root.get("dateOfBirth"), memberSearchDTO.getSearchMemberStartDateOfBirth(), memberSearchDTO.getSearchMemberEndDateOfBirth());
                    predicates.add(memberDateOfBirthPredicate);
                }

                if(memberSearchDTO.getSearchMemberAddress() != null){
                    Predicate memberAddressPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + memberSearchDTO.getSearchMemberAddress().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(memberAddressPredicate);
                }

                if(memberSearchDTO.getSearchMemberPhone() != null){
                    Predicate memberPhonePredicate = criteriaBuilder.like(root.get("phone"), "%" + memberSearchDTO.getSearchMemberPhone() + "%");
                    predicates.add(memberPhonePredicate);
                }

                if(memberSearchDTO.getSearchMemberUsername() != null){
                    Predicate memberUsernamePredicate = criteriaBuilder.like(root.get("username"), "%" + memberSearchDTO.getSearchMemberUsername().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(memberUsernamePredicate);
                }

                Predicate memberDeletePredicate = criteriaBuilder.equal(root.get("deleted"),FALSE);
                predicates.add(memberDeletePredicate);

                Predicate[] arrayPredicate = predicates.toArray(new Predicate[0]);

                return criteriaBuilder.and(arrayPredicate);
            }
        };
    }
}
