package br.com.thallyta.productcatalog.services;

import br.com.thallyta.productcatalog.config.exceptions.CategoryNotFoundException;
import br.com.thallyta.productcatalog.config.exceptions.ProductNotFoundException;
import br.com.thallyta.productcatalog.models.Category;
import br.com.thallyta.productcatalog.models.Product;
import br.com.thallyta.productcatalog.models.dtos.MessageDTO;
import br.com.thallyta.productcatalog.models.dtos.request.ProductRequestDTO;
import br.com.thallyta.productcatalog.models.dtos.response.ProductResponseDTO;
import br.com.thallyta.productcatalog.repositories.ProductRepository;
import br.com.thallyta.productcatalog.services.aws.AwsSnsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AwsSnsService awsSnsService;

    public Product insert(ProductRequestDTO productRequestDTO){
        Category category = categoryService.findById(productRequestDTO.getCategoryId()).orElseThrow(CategoryNotFoundException::new);
        Product product = new Product();

        product.setTitle(productRequestDTO.getTitle());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setCategoryId(category.getId());
        product.setOwnerId(productRequestDTO.getOwnerId());

        awsSnsService.publish(new MessageDTO(product.toString()));
        return productRepository.save(product);
    }


    public Product update(String id, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        if(product.getCategoryId() != null) {
            Category category = categoryService.findById(productRequestDTO.getCategoryId()).orElseThrow(CategoryNotFoundException::new);
            product.setCategoryId(category.getId());
        }

        if(!productRequestDTO.getTitle().isEmpty()) product.setTitle(productRequestDTO.getTitle());
        if(!productRequestDTO.getDescription().isEmpty()) product.setDescription(productRequestDTO.getDescription());
        if(productRequestDTO.getPrice() != null) product.setPrice(productRequestDTO.getPrice());
        if(!productRequestDTO.getOwnerId().isEmpty()) product.setOwnerId(productRequestDTO.getOwnerId());

        awsSnsService.publish(new MessageDTO(product.toString()));
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
