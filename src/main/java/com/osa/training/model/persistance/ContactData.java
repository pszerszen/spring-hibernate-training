package com.osa.training.model.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContactData {
    @Column(length = 50, nullable = false)
    private String city;

    @Column(length = 50)
    private String street;

    @Column(length = 5, nullable = false)
    private String numberOfHouse;

    @Column(length = 5)
    private Integer numberOfFlat;

    @Column(length = 6, nullable = false)
    private String postcode;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 9, nullable = false)
    private String phoneNumber;
}
