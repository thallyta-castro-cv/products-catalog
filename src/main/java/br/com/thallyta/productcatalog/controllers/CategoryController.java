package br.com.thallyta.productcatalog.controllers;

import br.com.thallyta.productcatalog.models.Category;
import br.com.thallyta.productcatalog.models.dtos.request.CategoryRequestDTO;
import br.com.thallyta.productcatalog.models.dtos.response.CategoryResponseDTO;
import br.com.thallyta.productcatalog.services.CategoryService;
import jakarta.websocket.server.PathParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody CategoryRequestDTO categoryRequestDTO){
        Category category = categoryService.insert(categoryRequestDTO);
        CategoryResponseDTO categoryResponseDTO = modelMapper.map(category, CategoryResponseDTO.class);
        return ResponseEntity.ok().body(categoryResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable("id") String id,
                                                      @RequestBody CategoryRequestDTO categoryRequestDTO){
        Category category = categoryService.update(id, categoryRequestDTO);
        CategoryResponseDTO categoryResponseDTO = modelMapper.map(category, CategoryResponseDTO.class);
        return ResponseEntity.ok().body(categoryResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll(){
        List<CategoryResponseDTO> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> delete(@PathVariable("id") String id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
