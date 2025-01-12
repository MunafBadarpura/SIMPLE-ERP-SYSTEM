package com.munaf.ERP_SYSTEM.entities.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Category {
    ELECTRONICS("ELECTRONICS"),
    CLOTHING("CLOTHING"),
    SPORTS("SPORTS"),
    HARDWARE("HARDWARE"),
    OTHER("OTHER");

    private final String category;

    Category(String category) {
        this.category = category;
    }

}
