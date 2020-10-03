package by.itechart.library.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Book implements Serializable {
    private static final long serialVersionUID = 7197102530298867633L;

    private long id;
    private String cover;
    private String title;
    private String authors;
    private String publisher;
    private LocalDate publishDate;
    private String genres;
    private int pageCount;
    private String ISBN;
    private String description;
    private int totalAmount;
    private boolean status;
    private boolean deletedStatus;
    private int availableAmount;
}
