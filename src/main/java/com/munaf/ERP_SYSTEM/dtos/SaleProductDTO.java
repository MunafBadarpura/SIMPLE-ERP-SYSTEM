package com.munaf.ERP_SYSTEM.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serializable;


@Data
public class SaleProductDTO implements Serializable {

    @NotNull(message = "id can not be null")
    @Positive(message = "id Always be Positive")
    private Long id;

    @NotNull(message = "Quantity can not be null")
    @Positive(message = "Quantity Always be Positive")
    private Long productQuantity;

}
