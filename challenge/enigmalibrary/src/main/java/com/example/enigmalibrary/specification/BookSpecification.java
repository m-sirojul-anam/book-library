package com.example.enigmalibrary.specification;

import com.example.enigmalibrary.dto.BookSearchDTO;
import com.example.enigmalibrary.entities.Book;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.Boolean.FALSE;

public class BookSpecification {
    public static Specification<Book> getSpecification(BookSearchDTO bookSearchDTO){
        return new Specification<Book>() {

            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if(bookSearchDTO.getSearchBookTitle() != null){
                    Predicate bookTitlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + bookSearchDTO.getSearchBookTitle().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(bookTitlePredicate);
                }

                if(bookSearchDTO.getSearchBookGenre() != null){
                    Predicate bookGenrePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("genre")), "%" + bookSearchDTO.getSearchBookGenre().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(bookGenrePredicate);
                }

                if(bookSearchDTO.getSearchBookPublisher() != null){
                    Predicate bookPublisherPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("publisher")), "%" + bookSearchDTO.getSearchBookPublisher().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(bookPublisherPredicate);
                }

                if(bookSearchDTO.getSearchBookPublishStartYear() != null && bookSearchDTO.getSearchBookPublishEndYear() != null){
                    Predicate bookPublishYear = criteriaBuilder.between(root.get("publishYear"), bookSearchDTO.getSearchBookPublishStartYear(), bookSearchDTO.getSearchBookPublishEndYear());
                    predicates.add(bookPublishYear);
                }

                if(bookSearchDTO.getSearchBookAuthor() != null){
                    Predicate bookAuthorPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), "%" + bookSearchDTO.getSearchBookAuthor().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(bookAuthorPredicate);
                }

                    Predicate bookDeletedPredicate = criteriaBuilder.equal(root.get("deleted"), FALSE);
                    predicates.add(bookDeletedPredicate);

                Predicate[] arrayPredicate = predicates.toArray(predicates.toArray(new Predicate[predicates.size()]));

                return criteriaBuilder.and(arrayPredicate);
            }
        };
    }
}
