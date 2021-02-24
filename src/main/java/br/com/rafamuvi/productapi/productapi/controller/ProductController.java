package br.com.rafamuvi.productapi.productapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafamuvi.productapi.productapi.model.Product;
import br.com.rafamuvi.productapi.productapi.repository.ProductRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    private Product product;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = new ArrayList<>();
        products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Product>> getById(@PathVariable Long id) {
        Optional<Product> product;
        try{
            product = productRepository.findById(id);
            return new ResponseEntity<Optional<Product>>(HttpStatus.OK);
        }catch (Exception error) {
            return new ResponseEntity<Optional<Product>>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<Product> post(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, 
        @RequestBody Product newProduct){
            return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDesc(newProduct.getDesc());
                    product.setImgUrl(newProduct.getImgUrl());
                    return ResponseEntity.ok().body(product);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<Product>> delete(@PathVariable Long id){
        try{
            productRepository.deleteById(id);
            return new ResponseEntity<Optional<Product>>(HttpStatus.OK);
        }catch (Exception error) {
            return new ResponseEntity<Optional<Product>>(HttpStatus.NOT_FOUND);
        }
    }
    
}
