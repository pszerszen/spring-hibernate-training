package com.osa.training.repo;

import com.osa.training.model.persistance.PurchaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepo extends CrudRepository<PurchaseEntity, Long> {
}
