package com.pharius.apirest.first_api_rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.pharius.apirest.first_api_rest.Entities.Product;
import com.pharius.apirest.first_api_rest.repositories.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    
    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id, @RequestBody Product productBody){
        return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("The product wit the ID "+ id + "wasn't found"));
    }


    @PostMapping
    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productBody){
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("The product wit the ID "+ id + "wasn't found"));

        product.setName(productBody.getName());
        product.setPrice(productBody.getPrice());

        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("The product wit the ID "+ id + "wasn't found"));

        productRepository.delete(product);

        return "The product with the Id" + id + "was deleted successfully";
    }
}
