package by.itechart.library.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class BorrowRecord implements Serializable {
    private static final long serialVersionUID = -7741242839891790101L;

    private long id;
    private long userId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private BorrowRecordStatus recordStatus;
    private String comment;
    private long bookId;
}
