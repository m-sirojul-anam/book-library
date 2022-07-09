package com.example.enigmalibrary.specification;

import com.example.enigmalibrary.dto.ReportRecordSearchDTO;
import com.example.enigmalibrary.entities.ReportRecord;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ReportRecordSpesification {
    public static Specification<ReportRecord> getSpecification(ReportRecordSearchDTO reportRecordSearchDTO){
        return new Specification<ReportRecord>() {
            @Override
            public Predicate toPredicate(Root<ReportRecord> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if(reportRecordSearchDTO.getSearchReportRecordStartDate() != null){
                    Predicate searchReportRecordByDate = criteriaBuilder.between(root.get("reportDate"), reportRecordSearchDTO.getSearchReportRecordStartDate(), reportRecordSearchDTO.getSearchReportRecordEndDate());
                    predicates.add(searchReportRecordByDate);
                }

                Predicate[] arrayPredicate = predicates.toArray(predicates.toArray(new Predicate[predicates.size()]));

                return criteriaBuilder.and(arrayPredicate);
            }
        };
    }
}
