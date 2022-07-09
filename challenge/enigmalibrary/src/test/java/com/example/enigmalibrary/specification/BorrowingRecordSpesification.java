package com.example.enigmalibrary.specification;

import com.example.enigmalibrary.dto.BorrowingRecordSearchDTO;
import com.example.enigmalibrary.entities.Book;
import com.example.enigmalibrary.entities.BorrowingRecord;
import com.example.enigmalibrary.entities.Member;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BorrowingRecordSpesification {
    public Specification<BorrowingRecord> getSpecification(BorrowingRecordSearchDTO borrowingRecordSearchDTO){
        return new Specification<BorrowingRecord>() {
            @Override
            public Predicate toPredicate(Root<BorrowingRecord> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Root<Member> member = query.from(Member.class);
                Root<Book> book = query.from(Book.class);
                List<Predicate> predicates = new ArrayList<>();

                if(borrowingRecordSearchDTO.getSearchStartBorrowingDate() != null){
                    Predicate searchBorrowingDatePredicate = criteriaBuilder.between(root.get("bookBorrowingDate"), borrowingRecordSearchDTO.getSearchStartBorrowingDate(), borrowingRecordSearchDTO.getSearchEndBorrowingDate());
                    predicates.add(searchBorrowingDatePredicate);
                }

                if(borrowingRecordSearchDTO.getSearchStartReturnDate() != null){
                    Predicate searchReturnDatePredicate = criteriaBuilder.between(root.get("bookReturnDate"), borrowingRecordSearchDTO.getSearchStartReturnDate(), borrowingRecordSearchDTO.getSearchEndReturnDate());
                    predicates.add(searchReturnDatePredicate);
                }

                if(borrowingRecordSearchDTO.getSearchMemberId() != null){
                    Predicate searchMemberById = criteriaBuilder.like(criteriaBuilder.lower(root.get("member").get("id")),"%" + borrowingRecordSearchDTO.getSearchMemberId().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(searchMemberById);
                }

                if(borrowingRecordSearchDTO.getSearchMemberFirstName() != null){
                    Predicate searchMemberName = criteriaBuilder.like(criteriaBuilder.lower(root.get("member").get("name")), "%" + borrowingRecordSearchDTO.getSearchMemberFirstName().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(searchMemberName);
                }

                if(borrowingRecordSearchDTO.getSearchBookId() != null){
                    Predicate searchBookById = criteriaBuilder.like(criteriaBuilder.lower(root.get("book").get("id")),"%" + borrowingRecordSearchDTO.getSearchBookId().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(searchBookById);
                }

                if(borrowingRecordSearchDTO.getSearchBookTitle() != null){
                    Predicate searchBookByName = criteriaBuilder.like(criteriaBuilder.lower(root.get("book").get("title")), "%" + borrowingRecordSearchDTO.getSearchBookTitle().toLowerCase(Locale.ROOT) + "%");
                }

                Predicate[] arrayPredicate = predicates.toArray(predicates.toArray(new Predicate[predicates.size()]));
                return criteriaBuilder.and(arrayPredicate);
            }
        };
    }
}
