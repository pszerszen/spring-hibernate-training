package com.osa.training.repo;

import com.osa.training.model.persistance.PurchaseEntity;

import java.util.List;

public interface SearchPurchaseRepo {

    List<PurchaseEntity> findPurchaseByCriteria(PurchaseSearchCriteria searchCriteria);
}
