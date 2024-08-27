package com.pharius.apirest.first_api_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharius.apirest.first_api_rest.Entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
