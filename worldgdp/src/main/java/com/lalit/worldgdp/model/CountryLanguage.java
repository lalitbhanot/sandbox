package com.lalit.worldgdp.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CountryLanguage {
    @NotNull
    private String countryCode;
    @NotNull
    @Size(max = 30)
    private String language;
    @NotNull
    @Size(max = 1, min = 1)
    private String isOfficial;
    @NotNull
    private Double percentage;
}