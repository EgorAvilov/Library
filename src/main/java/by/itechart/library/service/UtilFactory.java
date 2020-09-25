package by.itechart.library.service;

import by.itechart.library.service.util.BookValidator;
import by.itechart.library.service.util.BorrowRecordValidator;
import by.itechart.library.service.util.UserValidator;
import by.itechart.library.service.util.impl.BookValidatorImpl;
import by.itechart.library.service.util.impl.BorrowRecordValidatorImpl;
import by.itechart.library.service.util.impl.UserValidatorImpl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UtilFactory {
    @Getter
    private static final UtilFactory instance = new UtilFactory();
    @Getter
    private UserValidator userValidator = new UserValidatorImpl();
    @Getter
    private BookValidator bookValidator = new BookValidatorImpl();
    @Getter
    private BorrowRecordValidator borrowRecordValidator = new BorrowRecordValidatorImpl();

}
