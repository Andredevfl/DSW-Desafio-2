package com.agenciadeviagens.controller;

import com.agenciadeviagens.dto.DestinationDTO;
import com.agenciadeviagens.dto.RatingDTO;
import com.agenciadeviagens.model.Destination;
import com.agenciadeviagens.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    // Endpoint para cadastrar destino
    @PostMapping
    public ResponseEntity<Destination> createDestination(@RequestBody @Valid DestinationDTO destinationDTO) {
        Destination newDestination = destinationService.createDestination(
                destinationDTO.getName(), destinationDTO.getLocation(), destinationDTO.getDescription());
        return new ResponseEntity<>(newDestination, HttpStatus.CREATED);
    }

    // Endpoint para listar todos os destinos
    @GetMapping
    public List<Destination> getAllDestinations() {
        return destinationService.getAllDestinations();
    }

    // Endpoint para pesquisar destinos
    @GetMapping("/search")
    public List<Destination> searchDestinations(@RequestParam String query) {
        return destinationService.searchDestinations(query);
    }

    // Endpoint para visualizar detalhes de um destino
    @GetMapping("/{name}")
    public ResponseEntity<Destination> getDestinationDetails(@PathVariable String name) {
        Optional<Destination> destination = destinationService.getDestinationDetails(name);
        return destination.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para avaliar um destino
    @PostMapping("/{name}/rate")
    public ResponseEntity<String> rateDestination(@PathVariable String name, @RequestBody RatingDTO ratingDTO) {
        boolean rated = destinationService.rateDestination(name, ratingDTO.getRating());
        if (rated) {
            return ResponseEntity.ok("Rating updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid rating or destination not found.");
        }
    }

    // Endpoint para excluir destino
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteDestination(@PathVariable String name) {
        boolean deleted = destinationService.deleteDestination(name);
        if (deleted) {
            return ResponseEntity.ok("Destination deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Destination not found.");
        }
    }
}
