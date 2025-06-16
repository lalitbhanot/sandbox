package com.lalit.urlshortener;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
@ConfigurationProperties(prefix = "app")
@Validated

public record AppProperties(
        @NotBlank
        @DefaultValue("http://localhost:9090")
        String baseUrl,
        @DefaultValue("30")
        @Min(1)
        @Max(365)
        int defaultExpiryInDays,
        @DefaultValue("true")
        boolean validateOriginalUrl,
         @DefaultValue("10")
        int pageSize
) {
}