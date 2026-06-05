package in.ujjwalsingh.creatorstor.services;

import in.ujjwalsingh.creatorstor.entities.Product;
import in.ujjwalsingh.creatorstor.repositories.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;



    public Product createProduct(  Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(  Long id,   Product product){
        Product existingProduct=productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id" + id));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCatagory(product.getCatagory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStockQuantity(product.getStockQuantity());

        return productRepository.save(existingProduct);
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }


    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id" + id));
    }


    public void deleteProduct(Long id){
        productRepository.deleteById(id);

    }

}
