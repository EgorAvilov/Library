package by.itechart.library.service.dto;

import by.itechart.library.entity.BorrowRecordStatus;
import lombok.Data;

import java.time.LocalDate;
@Data
public class BorrowRecordDto {
    long id;
    long bookId;
    String username;
    String bookTitle;
    LocalDate borrowDate;
    LocalDate dueDate;
    LocalDate returnDate;
    int statusId;
    String comment;

}
