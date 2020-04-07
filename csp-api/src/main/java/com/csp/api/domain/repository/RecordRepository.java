package com.csp.api.domain.repository;

import com.csp.api.domain.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository for {@link Record}.
 */
public interface RecordRepository extends JpaRepository<Record, UUID> {
}
