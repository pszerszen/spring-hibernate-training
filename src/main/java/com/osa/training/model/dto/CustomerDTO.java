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
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    private ContactData contactData;

    private Set<PurchaseDTO> purchases;

    @Builder
    public CustomerDTO(Long id,
                       Long version,
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

