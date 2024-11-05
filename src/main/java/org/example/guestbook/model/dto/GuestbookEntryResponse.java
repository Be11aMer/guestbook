package org.example.guestbook.model.dto;

import java.util.Date;

import lombok.Data;
import lombok.Setter;
@Data
public class GuestbookEntryResponse {
    private Long id;
    private String title;
    private String comment;
    private String commenter;
    private Date date;
}
