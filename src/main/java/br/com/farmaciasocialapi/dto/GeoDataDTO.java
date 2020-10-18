package br.com.farmaciasocialapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GeoDataDTO {
    private String latitude;
    private String longitude;
}
