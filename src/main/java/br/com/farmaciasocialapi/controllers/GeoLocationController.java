package br.com.farmaciasocialapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.farmaciasocialapi.dto.GeoDataDTO;
import br.com.farmaciasocialapi.service.GoogleMapsService;

@RestController
@CrossOrigin
@RequestMapping("/api/geo")
public class GeoLocationController {
    
    @Autowired
    private GoogleMapsService googleMapsService = new GoogleMapsService();

    @PostMapping("")
    public ResponseEntity<?> getReverseGeoLocationData(@RequestBody GeoDataDTO geoDTO ){
        String response = googleMapsService.getGeoLocationData(geoDTO.getAddress());        
        return ResponseEntity.ok(response);
    }
}
