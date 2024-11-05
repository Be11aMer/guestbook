package org.example.guestbook.model.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.example.guestbook.model.GuestbookEntry;
import org.example.guestbook.repository.GuestbookEntryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuestbookService {

    private final GuestbookEntryRepository guestbookEntryRepository;

    public GuestbookEntry create(GuestbookEntry request) {
        return guestbookEntryRepository.save(request);
    }

    public List<GuestbookEntry> readAllOrderedByIdDesc() {
        return guestbookEntryRepository.findAllByOrderByIdDesc();
    }

    public void delete(long id) {
        guestbookEntryRepository.deleteById(id);
    }

    public GuestbookEntry readById(long id) {
        return guestbookEntryRepository.findById(id).orElse(null);
    }

    public GuestbookEntry update(GuestbookEntry guestbookEntry) {
        GuestbookEntry serializedGuestbookEntry = readById(guestbookEntry.getId());
        serializedGuestbookEntry.setTitle(guestbookEntry.getTitle());
        serializedGuestbookEntry.setComment(guestbookEntry.getComment());
        serializedGuestbookEntry.setCommenter(guestbookEntry.getCommenter());
        return guestbookEntryRepository.save(serializedGuestbookEntry);
    }
}
