package com.vet.manage.repository;

import com.vet.manage.model.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Reporot
 */
@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

}
