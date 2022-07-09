package com.example.enigmalibrary.specification;

import com.example.enigmalibrary.dto.BorrowingRecordSearchDTO;
import com.example.enigmalibrary.entities.Book;
import com.example.enigmalibrary.entities.BorrowingRecord;
import com.example.enigmalibrary.entities.Member;
import com.example.enigmalibrary.entities.ReturnRecord;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BorrowingRecordSpecification {
    public static Specification<BorrowingRecord> getSpecification(BorrowingRecordSearchDTO borrowingRecordSearchDTO){
      return  new Specification<BorrowingRecord>() {
          @Override
          public Predicate toPredicate(Root<BorrowingRecord> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
              Join<ReturnRecord, Member> groupJoinMember = root.join("member");
              Join<ReturnRecord, Book> groupJoinBook = root.join("book");

              List<Predicate> predicates = new ArrayList<>();

              if(borrowingRecordSearchDTO.getSearchStartBorrowingDate() != null){
                  Predicate searchBorrowingDate = criteriaBuilder.between(root.get("bookBorrowingDate"), borrowingRecordSearchDTO.getSearchStartBorrowingDate(), borrowingRecordSearchDTO.getSearchEndBorrowingDate());
                  predicates.add(searchBorrowingDate);
              }

              if(borrowingRecordSearchDTO.getSearchStartReturnDate() != null){
                  Predicate searchReturnDate = criteriaBuilder.between(root.get("bookReturnDate"), borrowingRecordSearchDTO.getSearchStartReturnDate(), borrowingRecordSearchDTO.getSearchEndReturnDate());
                  predicates.add(searchReturnDate);
              }

              if(borrowingRecordSearchDTO.getSearchMemberId() != null){
                  Predicate searchMemberId = criteriaBuilder.like(criteriaBuilder.lower(groupJoinMember.get("id")), "%" + borrowingRecordSearchDTO.getSearchMemberId().toLowerCase(Locale.ROOT) + "%");
                  predicates.add(searchMemberId);
              }

              if(borrowingRecordSearchDTO.getSearchMemberFirstName() != null){
                  Predicate searchMemberFirstName = criteriaBuilder.like(criteriaBuilder.lower(groupJoinMember.get("firstName")), "%" + borrowingRecordSearchDTO.getSearchMemberFirstName().toLowerCase(Locale.ROOT) + "%");
                  predicates.add(searchMemberFirstName);
              }

              if(borrowingRecordSearchDTO.getSearchMemberLastName() != null){
                  Predicate searchMemberLastName = criteriaBuilder.like(criteriaBuilder.lower(groupJoinMember.get("lastName")), "%" + borrowingRecordSearchDTO.getSearchMemberLastName().toLowerCase(Locale.ROOT) + "%");
                  predicates.add(searchMemberLastName);
              }

              if(borrowingRecordSearchDTO.getSearchBookId() != null){
                  Predicate searchBookId = criteriaBuilder.like(criteriaBuilder.lower(groupJoinBook.get("id")), "%" + borrowingRecordSearchDTO.getSearchBookId().toLowerCase(Locale.ROOT) + "%");
                  predicates.add(searchBookId);
              }

              if(borrowingRecordSearchDTO.getSearchBookTitle() != null){
                  Predicate searchBookTitle = criteriaBuilder.like(criteriaBuilder.lower(groupJoinBook.get("title")), "%" + borrowingRecordSearchDTO.getSearchBookTitle().toLowerCase(Locale.ROOT) + "%");
                  predicates.add(searchBookTitle);
              }

              if(borrowingRecordSearchDTO.getSearchBookAuthor() != null){
                  Predicate searchBookAuthor = criteriaBuilder.like(criteriaBuilder.lower(groupJoinBook.get("author")), "%" + borrowingRecordSearchDTO.getSearchBookAuthor().toLowerCase(Locale.ROOT) + "%");
                  predicates.add(searchBookAuthor);
              }

              Predicate[] arrayPredicate = predicates.toArray(new Predicate[0]);

              return criteriaBuilder.and(arrayPredicate);
          }
      };
    }
}
