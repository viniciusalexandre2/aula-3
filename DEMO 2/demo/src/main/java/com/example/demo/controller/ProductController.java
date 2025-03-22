package com.example.demo.controller; 
import java.util.ArrayList; 
import java.util.List; 

import org.springframework.http.HttpStatus; 
import org.springframework.web.bind.annotation.*; 

import com.example.demo.model.Product; 

@RestController 
@RequestMapping("/products")
public class ProductController {

    private final List<Product> products = new ArrayList<>();
    private long nextId = 1L;

    @GetMapping
    public List<Product> getAllProducts() {
        return products; 
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) 
    public Product addProduct(@RequestBody Product product) {
        product.setId(nextId++); 
        products.add(product);
        return product; 
    }

    @GetMapping("/{id}")
    public Object getProductById(@PathVariable Long id) {
        for (Product product : products) { 
            if (product.getId().equals(id)) { 
                return product; 
            }
        }
        return "Produto não encontrado"; 
    }

    
    @PutMapping("/{id}")
    public Object updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        for (Product product : products) { 
            if (product.getId().equals(id)) { 
                product.setName(updatedProduct.getName()); 
            }
        }
        return "Produto não encontrado"; 
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        for (Product product : products) { 
            if (product.getId().equals(id)) { 
                products.remove(product); 
                return "Produto removido com sucesso"; 
            }
        }
        return "Produto não encontrado"; 
    }
}