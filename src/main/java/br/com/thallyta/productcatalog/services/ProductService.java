package br.com.thallyta.productcatalog.services;

import br.com.thallyta.productcatalog.config.exceptions.CategoryNotFoundException;
import br.com.thallyta.productcatalog.config.exceptions.ProductNotFoundException;
import br.com.thallyta.productcatalog.models.Category;
import br.com.thallyta.productcatalog.models.Product;
import br.com.thallyta.productcatalog.models.dtos.request.ProductRequestDTO;
import br.com.thallyta.productcatalog.models.dtos.response.ProductResponseDTO;
import br.com.thallyta.productcatalog.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

    public Product insert(ProductRequestDTO productRequestDTO){
        Category category = categoryService.findById(productRequestDTO.getCategoryId()).orElseThrow(CategoryNotFoundException::new);
        Product product = new Product();

        product.setTitle(productRequestDTO.getTitle());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setCategory(category);

        return productRepository.save(product);
    }


    public Product update(String id, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        if(product.getCategory().getId() != null) {
            categoryService.findById(productRequestDTO.getCategoryId())
                    .ifPresent(product::setCategory);
        }

        if(!productRequestDTO.getTitle().isEmpty()) product.setTitle(productRequestDTO.getTitle());
        if(!productRequestDTO.getDescription().isEmpty()) product.setDescription(productRequestDTO.getDescription());
        if(productRequestDTO.getPrice() != null) product.setPrice(productRequestDTO.getPrice());
        if(!productRequestDTO.getOwnerId().isEmpty()) product.setOwnerId(productRequestDTO.getOwnerId());

        productRepository.save(product);
        return product;
    }

    public void delete(String id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }

    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    public List<ProductResponseDTO> findAll(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> modelMapper.map(product, ProductResponseDTO.class)).toList();
    }
}
