package com.productapi.repository;

import com.productapi.model.ProductStatistics;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStatisticsRepository extends CassandraRepository<ProductStatistics, String> {
}
