package com.csp.api.domain.repository;

import com.csp.api.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository for {@link Report}.
 */
public interface ReportRepository extends JpaRepository<Report, UUID> {
}
