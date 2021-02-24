package br.com.rafamuvi.productapi.productapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rafamuvi.productapi.productapi.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
