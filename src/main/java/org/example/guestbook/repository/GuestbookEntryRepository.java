package org.example.guestbook.repository;

import java.util.List;

import org.example.guestbook.model.GuestbookEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestbookEntryRepository extends JpaRepository<GuestbookEntry, Long> {
List<GuestbookEntry> findAllByOrderByIdDesc();
}
