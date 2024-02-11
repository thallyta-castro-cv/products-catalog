package br.com.thallyta.productcatalog.services;

import br.com.thallyta.productcatalog.config.exceptions.CategoryNotFoundException;
import br.com.thallyta.productcatalog.models.Category;
import br.com.thallyta.productcatalog.models.dtos.request.CategoryRequestDTO;
import br.com.thallyta.productcatalog.models.dtos.response.CategoryResponseDTO;
import br.com.thallyta.productcatalog.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Category insert(CategoryRequestDTO categoryRequestDTO){
        Category category = modelMapper.map(categoryRequestDTO, Category.class);
        return categoryRepository.save(category);
    }

    public List<CategoryResponseDTO> findAll(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> modelMapper.map(category, CategoryResponseDTO.class)).toList();
    }

    public Category update(String id, CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

        if(!categoryRequestDTO.getTitle().isEmpty()) category.setTitle(categoryRequestDTO.getTitle());
        if(!categoryRequestDTO.getDescription().isEmpty()) category.setDescription(categoryRequestDTO.getDescription());

        categoryRepository.save(category);
        return category;
    }

    public void delete(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        categoryRepository.delete(category);
    }

    public Optional<Category> findById(String id) {
        return categoryRepository.findById(id);
    }

}
