package com.transport.controller;

import com.transport.dto.TransportRequest;
import com.transport.dto.TransportResponse;
import com.transport.service.TransportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transports")
public class TransportController {
    @Autowired
    private TransportService transportService;

    // Create Transport
    @PostMapping
    public ResponseEntity<TransportResponse> createTransport(@Valid @RequestBody TransportRequest request) {
        TransportResponse response = transportService.createTransport(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get All Transports
    @GetMapping
    public ResponseEntity<List<TransportResponse>> getAllTransports() {
        List<TransportResponse> transports = transportService.getAllTransports();
        return new ResponseEntity<>(transports, HttpStatus.OK);
    }

    // Get Transport By ID
    @GetMapping("/{id}")
    public ResponseEntity<TransportResponse> getTransportById(@PathVariable Long id) {
        TransportResponse response = transportService.getTransportById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update Transport
    @PutMapping("/{id}")
    public ResponseEntity<TransportResponse> updateTransport(@PathVariable Long id,
                                                             @Valid @RequestBody TransportRequest request) {
        TransportResponse response = transportService.updateTransport(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Delete Transport
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransport(@PathVariable Long id) {
        transportService.deleteTransport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
