package com.transport.service;

import com.transport.dto.TransportRequest;
import com.transport.dto.TransportResponse;
import com.transport.entity.Transport;
import com.transport.exception.ResourceNotFoundException;
import com.transport.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportService {

    @Autowired
    private TransportRepository transportRepository;

    /**
     * Create a new transport
     */
    public TransportResponse createTransport(TransportRequest request) {
        Transport transport = Transport.builder()
                .transportType(request.getTransportType())
                .providerName(request.getProviderName())
                .source(request.getSource())
                .destination(request.getDestination())
                .price(request.getPrice())
                .availableSeats(request.getAvailableSeats())
                .description(request.getDescription())
                .build();

        Transport savedTransport = transportRepository.save(transport);
        return convertToResponse(savedTransport);
    }

    /**
     * Get all transports
     */
    public List<TransportResponse> getAllTransports() {
        return transportRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get transport by ID
     *
     */
    public TransportResponse getTransportById(Long id) {
        return transportRepository.findById(id)
                .map(this::convertToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Transport not found with ID: " + id));
    }

    /**
     * Update transport
     */
    public TransportResponse updateTransport(Long id, TransportRequest request) {
        Transport transport = transportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transport not found with ID: " + id));

        transport.setTransportType(request.getTransportType());
        transport.setProviderName(request.getProviderName());
        transport.setSource(request.getSource());
        transport.setDestination(request.getDestination());
        transport.setPrice(request.getPrice());
        transport.setAvailableSeats(request.getAvailableSeats());
        transport.setDescription(request.getDescription());

        Transport updatedTransport = transportRepository.save(transport);
        return convertToResponse(updatedTransport);
    }

    /**
     * Delete transport by ID
     */
    public void deleteTransport(Long id) {
        if (!transportRepository.existsById(id)) {
            throw new ResourceNotFoundException("Transport not found with ID: " + id);
        }
        transportRepository.deleteById(id);
    }

    /**
     * Convert Transport entity to TransportResponse DTO
     */
    private TransportResponse convertToResponse(Transport transport) {
        return TransportResponse.builder()
                .transportId(transport.getTransportId())
                .transportType(transport.getTransportType())
                .providerName(transport.getProviderName())
                .source(transport.getSource())
                .destination(transport.getDestination())
                .price(transport.getPrice())
                .availableSeats(transport.getAvailableSeats())
                .description(transport.getDescription())
                .build();
    }
}
