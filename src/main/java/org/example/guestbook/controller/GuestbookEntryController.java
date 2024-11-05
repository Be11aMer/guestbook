package org.example.guestbook.controller;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.example.guestbook.model.GuestbookEntry;
import org.example.guestbook.model.converter.GuestbookEntryConverter;
import org.example.guestbook.model.dto.GuestbookEntryRequest;
import org.example.guestbook.model.dto.GuestbookEntryResponse;
import org.example.guestbook.model.service.GuestbookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guestbook/entries")
@RequiredArgsConstructor
public class GuestbookEntryController {

    private final GuestbookService service;
    private final GuestbookEntryConverter converter;

    @PostMapping
    public ResponseEntity<GuestbookEntryResponse> addGuestbookEntry(@RequestBody GuestbookEntryRequest request) {
        GuestbookEntry guestbookEntryRequest = converter.convertRequestToModel(request);
        GuestbookEntry guestbookEntryResponse = service.create(guestbookEntryRequest);
        return new ResponseEntity<>(converter.convertModelToResponse(guestbookEntryResponse), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GuestbookEntryResponse> getGuestbookEntry(@PathVariable Long id) {
        GuestbookEntry guestbookEntryResponse = service.readById(id);
        return new ResponseEntity<>(converter.convertModelToResponse(guestbookEntryResponse), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GuestbookEntryResponse>> getAllGuestbookEntries() {
        List<GuestbookEntry> guestbookEntries = service.readAllOrderedByIdDesc();
        List<GuestbookEntryResponse> guestbookEntryResponses = new ArrayList<>();
        for (GuestbookEntry guestbookEntry : guestbookEntries) {
            guestbookEntryResponses.add(converter.convertModelToResponse(guestbookEntry));
        }
        return new ResponseEntity<>(guestbookEntryResponses, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteGuestbookEntry(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GuestbookEntryResponse> updateGuestbookEntry(@PathVariable Long id, @RequestBody GuestbookEntryRequest request) {
        GuestbookEntry guestbookEntryRequest = converter.convertRequestToModel(id, request);
        GuestbookEntry guestbookEntryResponse = service.update(guestbookEntryRequest);
        return new ResponseEntity<>(converter.convertModelToResponse(guestbookEntryResponse), HttpStatus.OK);
    }
}
