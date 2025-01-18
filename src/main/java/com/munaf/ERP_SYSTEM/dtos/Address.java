package com.munaf.ERP_SYSTEM.dtos;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Address {
    @NotBlank(message = "area can not be blank")
    private String area;
    @NotBlank(message = "city can not be blank")
    private String city;
    @NotBlank(message = "state can not be blank")
    private String state;
    @NotBlank(message = "pinCode can not be blank")
    @Size(min = 3, max = 25, message = "pinCode be in the range of 3 to 25")
    private String pinCode;
}
