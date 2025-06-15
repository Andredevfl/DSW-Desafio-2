package com.agenciadeviagens.service;

import com.agenciadeviagens.model.Destination;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    private List<Destination> destinations = new ArrayList<>();

    // Cadastrar novo destino
    public Destination createDestination(String name, String location, String description) {
        Destination newDestination = new Destination(name, location, description);
        destinations.add(newDestination);
        return newDestination;
    }

    // Listar todos os destinos
    public List<Destination> getAllDestinations() {
        return destinations;
    }

    // Pesquisar destinos por nome ou localização
    public List<Destination> searchDestinations(String query) {
        List<Destination> result = new ArrayList<>();
        for (Destination dest : destinations) {
            if (dest.getName().contains(query) || dest.getLocation().contains(query)) {
                result.add(dest);
            }
        }
        return result;
    }

    // Visualizar detalhes de um destino específico
    public Optional<Destination> getDestinationDetails(String name) {
        return destinations.stream().filter(dest -> dest.getName().equalsIgnoreCase(name)).findFirst();
    }

    // Avaliar destino
    public boolean rateDestination(String name, int rating) {
        Optional<Destination> destination = getDestinationDetails(name);
        if (destination.isPresent() && rating >= 1 && rating <= 10) {
            destination.get().updateRating(rating);
            return true;
        }
        return false;
    }

    // Excluir destino
    public boolean deleteDestination(String name) {
        return destinations.removeIf(dest -> dest.getName().equalsIgnoreCase(name));
    }
}
