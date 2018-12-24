package com.osa.training.model.dto;

import com.osa.training.model.persistance.ContactData;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.constraints.NotNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class CustomerDTO extends AbstractDTO {

    @NotNull
    private final String firstName;
    @NotNull
    private final String lastName;
    @NotNull
    private final LocalDate dateOfBirth;
    @NotNull
    private final ContactData contactData;

    private final Set<PurchaseDTO> purchases;

    @Builder
    public CustomerDTO(Long id,
                       int version,
                       LocalDateTime createDate,
                       LocalDateTime updateDate,
                       @NotNull String firstName,
                       @NotNull String lastName,
                       @NotNull LocalDate dateOfBirth,
                       @NotNull ContactData contactData,
                       Set<PurchaseDTO> purchases) {
        super(id, version, createDate, updateDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.contactData = contactData;
        this.purchases = purchases;
    }
}

