package com.example.enigmalibrary.specification;

import com.example.enigmalibrary.dto.ReturnRecordSearchDTO;
import com.example.enigmalibrary.entities.Book;
import com.example.enigmalibrary.entities.BorrowingRecord;
import com.example.enigmalibrary.entities.ReturnRecord;
import com.example.enigmalibrary.entities.Member;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReturnRecordSpesification {
    public static Specification<ReturnRecord> getSpecification(ReturnRecordSearchDTO returnRecordSearchDTO){
        return  new Specification<ReturnRecord>() {
            @Override
            public Predicate toPredicate(Root<ReturnRecord> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<ReturnRecord, BorrowingRecord> groupJoinBorrowingRecord = root.join("borrowingRecord");
                Join<BorrowingRecord, Member> groupJoinMember = groupJoinBorrowingRecord.join("member");
                Join<BorrowingRecord, Book> groupJoinBook = groupJoinBorrowingRecord.join("book");

                List<Predicate> predicates = new ArrayList<>();

                if(returnRecordSearchDTO.getSearchStatusReturnRecord() != null){
                    Predicate searchStatusReturn = criteriaBuilder.like(criteriaBuilder.lower(root.get("statusReturnDate")), "%" + returnRecordSearchDTO.getSearchStatusReturnRecord().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(searchStatusReturn);
                }

                if(returnRecordSearchDTO.getSearchStartReturnDate() != null){
                    Predicate searchReturnDate = criteriaBuilder.between(root.get("bookReturnDate"), returnRecordSearchDTO.getSearchStartReturnDate(), returnRecordSearchDTO.getSearchEndReturnDate());
                    predicates.add(searchReturnDate);
                }

                if(returnRecordSearchDTO.getSearchMemberId() != null){
                    Predicate searchMemberId = criteriaBuilder.like(criteriaBuilder.lower(groupJoinMember.get("id")), "%" + returnRecordSearchDTO.getSearchMemberId().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(searchMemberId);
                }

                if(returnRecordSearchDTO.getSearchMemberFirstName() != null){
                    Predicate searchMemberFirstName = criteriaBuilder.like(criteriaBuilder.lower(groupJoinMember.get("firstName")), "%" + returnRecordSearchDTO.getSearchMemberFirstName().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(searchMemberFirstName);
                }

                if(returnRecordSearchDTO.getSearchMemberLastName() != null){
                    Predicate searchMemberLastName = criteriaBuilder.like(criteriaBuilder.lower(groupJoinMember.get("lastName")), "%" + returnRecordSearchDTO.getSearchMemberLastName().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(searchMemberLastName);
                }

                if(returnRecordSearchDTO.getSearchBookId() != null){
                    Predicate searchBookId = criteriaBuilder.like(criteriaBuilder.lower(groupJoinBook.get("id")), "%" + returnRecordSearchDTO.getSearchBookId().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(searchBookId);
                }

                if(returnRecordSearchDTO.getSearchBookTitle() != null){
                    Predicate searchBookTitle = criteriaBuilder.like(criteriaBuilder.lower(groupJoinBook.get("title")), "%" + returnRecordSearchDTO.getSearchBookTitle().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(searchBookTitle);
                }

                if(returnRecordSearchDTO.getSearchBookAuthor() != null){
                    Predicate searchBookAuthor = criteriaBuilder.like(criteriaBuilder.lower(groupJoinBook.get("author")), "%" + returnRecordSearchDTO.getSearchBookAuthor().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(searchBookAuthor);
                }

                Predicate[] arrayPredicate = predicates.toArray(new Predicate[0]);

                return criteriaBuilder.and(arrayPredicate);
            }
        };
    }
}
