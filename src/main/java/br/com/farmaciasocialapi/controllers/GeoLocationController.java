package br.com.farmaciasocialapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.farmaciasocialapi.dto.GeoDataDTO;
import br.com.farmaciasocialapi.service.MapQuestService;

@RestController
@CrossOrigin
@RequestMapping("/api/geo")
public class GeoLocationController {
    
    @Autowired
    private MapQuestService mapQuestService = new MapQuestService();

    @PostMapping("")
    public ResponseEntity<?> getReverseGeoLocationData(@RequestBody GeoDataDTO geoDTO ){
        String response = mapQuestService.getGeoLocationData(geoDTO.getLatitude(), geoDTO.getLongitude());        
        return ResponseEntity.ok(response);
    }
}
