package com.lalit.worldgdp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CountryGDP {
    private Short year;
    private Double value;
}