package com.osa.training.repo;

import com.osa.training.model.persistance.OrderProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepo extends CrudRepository<OrderProductEntity, Long> {
}
