package com.osa.training.mapper;

import com.osa.training.model.dto.CustomerDTO;
import com.osa.training.model.persistance.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toSet;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerMapper extends AbstractMapper<CustomerEntity, CustomerDTO> {

    private final PurchaseMapper purchaseMapper;

    @Override
    CustomerEntity toEntity(CustomerDTO dto) {
        CustomerEntity entity = new CustomerEntity();

        entity.setContactData(dto.getContactData());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPurchases(dto.getPurchases().stream()
                .map(purchaseMapper::toEntity)
                .collect(toSet()));

        return entity;
    }

    @Override
    CustomerDTO toDto(CustomerEntity entity) {
        return CustomerDTO.builder()
                .contactData(entity.getContactData())
                .dateOfBirth(entity.getDateOfBirth())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .purchases(entity.getPurchases().stream()
                        .map(purchaseMapper::toDto)
                        .collect(toSet()))
                .build();
    }
}
