package org.example.guestbook.model.dto;

import lombok.Getter;

@Getter
public class GuestbookEntryRequest {

    private String title;
    private String comment;
    private String commenter;
}
