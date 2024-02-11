package br.com.thallyta.productcatalog.repositories;

import br.com.thallyta.productcatalog.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
