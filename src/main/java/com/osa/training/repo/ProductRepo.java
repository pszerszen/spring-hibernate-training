package com.osa.training.repo;

import com.osa.training.model.persistance.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<ProductEntity, Long> {
}
