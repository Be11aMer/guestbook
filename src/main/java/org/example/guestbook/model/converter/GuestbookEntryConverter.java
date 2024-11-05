package org.example.guestbook.model.converter;

import org.example.guestbook.model.GuestbookEntry;
import org.example.guestbook.model.dto.GuestbookEntryRequest;
import org.example.guestbook.model.dto.GuestbookEntryResponse;
import org.example.guestbook.model.service.GuestbookService;
import org.springframework.stereotype.Service;

@Service
public class GuestbookEntryConverter {

    private final GuestbookService service;

    public GuestbookEntryConverter(GuestbookService service) {
        this.service = service;
    }

    public GuestbookEntry convertRequestToModel(GuestbookEntryRequest request) {
        GuestbookEntry guestbookEntry = new GuestbookEntry();
        guestbookEntry.setTitle(request.getTitle());
        guestbookEntry.setComment(request.getComment());
        guestbookEntry.setCommenter(request.getCommenter());
        return guestbookEntry;
    }

    public GuestbookEntry convertRequestToModel(long id, GuestbookEntryRequest request) {
        GuestbookEntry guestbookEntry = service.readById(id);
        guestbookEntry.setTitle(request.getTitle());
        guestbookEntry.setComment(request.getComment());
        guestbookEntry.setCommenter(request.getCommenter());
        return guestbookEntry;

    }

    public GuestbookEntryResponse convertModelToResponse(GuestbookEntry model) {
        GuestbookEntryResponse guestbookEntryResponse = new GuestbookEntryResponse();
        guestbookEntryResponse.setId(model.getId());
        guestbookEntryResponse.setTitle(model.getTitle());
        guestbookEntryResponse.setComment(model.getComment());
        guestbookEntryResponse.setCommenter(model.getCommenter());
        guestbookEntryResponse.setDate(model.getDate());
        return guestbookEntryResponse;
    }
}
