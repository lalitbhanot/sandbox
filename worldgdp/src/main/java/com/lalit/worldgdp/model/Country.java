package com.lalit.worldgdp.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class Country {
    @NotNull
    @Size(max = 3, min = 3)
    private String code;
    @NotNull
    @Size(max = 52)
    private String name;
    @NotNull
    private
    String continent;
    @NotNull
    @Size(max = 26)
    private String region;

    private Double surfaceArea;
    private Short indepYear;
    private Long population;
    private Double lifeExpectancy;
    private Double gnp;
    private String localName;
    private String governmentForm;
    private String headOfState;
    private City capital;
    private String code2;
}
