package com.shadowhawk.registrar.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.shadowhawk.registrar.model.dynamodb.ProductInfo;

@EnableScan
public interface ProductInfoRepository extends 
  CrudRepository<ProductInfo, String> {
    
    Optional<ProductInfo> findById(String id);
}

