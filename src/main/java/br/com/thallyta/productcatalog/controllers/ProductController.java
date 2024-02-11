package br.com.thallyta.productcatalog.controllers;


import br.com.thallyta.productcatalog.models.Product;
import br.com.thallyta.productcatalog.models.dtos.request.ProductRequestDTO;
import br.com.thallyta.productcatalog.models.dtos.response.ProductResponseDTO;
import br.com.thallyta.productcatalog.services.ProductService;
import jakarta.websocket.server.PathParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProductService productService;


    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductRequestDTO productRequestDTO){
        Product product = productService.insert(productRequestDTO);
        ProductResponseDTO productResponseDTO = modelMapper.map(product, ProductResponseDTO.class);
        return ResponseEntity.ok().body(productResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable("id") String id,
                                                      @RequestBody ProductRequestDTO productRequestDTO){
        Product product = productService.update(id, productRequestDTO);
        ProductResponseDTO productResponseDTO = modelMapper.map(product, ProductResponseDTO.class);
        return ResponseEntity.ok().body(productResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll(){
        List<ProductResponseDTO> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> delete(@PathVariable("id") String id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
