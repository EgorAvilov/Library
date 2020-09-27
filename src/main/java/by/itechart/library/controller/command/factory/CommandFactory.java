package by.itechart.library.controller.command.factory;


import by.itechart.library.controller.command.Command;
import by.itechart.library.controller.command.impl.*;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.Map;

@Log4j
public class CommandFactory {

    @Getter
    private static final CommandFactory instance = new CommandFactory();

    @Getter
    private final Map<String, Command> operations = new HashMap<>();

    private CommandFactory() {
        operations.put(CommandName.COMMAND_ADD_BOOK, new AddBookCommand());
        operations.put(CommandName.COMMAND_ADD_BORROW_RECORD, new AddBorrowRecordCommand());
        operations.put(CommandName.COMMAND_CHANGE_BOOK_DELETED_STATUS, new ChangeBookDeletedStatusCommand());
        operations.put(CommandName.COMMAND_CHANGE_USER_DELETED_STATUS, new ChangeUserDeletedStatusCommand());
        operations.put(CommandName.COMMAND_EDIT_BOOK, new EditBookCommand());
        operations.put(CommandName.COMMAND_EDIT_BORROW_RECORD_BY_ADMIN, new EditBorrowRecordByAdminCommand());
        operations.put(CommandName.COMMAND_EDIT_BORROW_RECORD_BY_USER, new EditBorrowRecordByUserCommand());
        operations.put(CommandName.COMMAND_EDIT_PROFILE, new EditProfileCommand());
        operations.put(CommandName.COMMAND_FORWARD_EDIT_BOOK, new ForwardEditBookCommand());
        operations.put(CommandName.COMMAND_FORWARD_EDIT_BORROW_RECORD, new ForwardEditBorrowRecordCommand());
        operations.put(CommandName.COMMAND_FORWARD_EDIT_PROFILE, new ForwardEditProfileCommand());
        operations.put(CommandName.COMMAND_FORWARD_TO_MAIN_PAGE, new ForwardToMainPageCommand());
        operations.put(CommandName.COMMAND_SEARCH_BOOK, new SearchBookCommand());
        operations.put(CommandName.COMMAND_SIGN_IN, new SignInCommand());
        operations.put(CommandName.COMMAND_SIGN_OUT, new SignOutCommand());
        operations.put(CommandName.COMMAND_SIGN_UP, new SignUpCommand());
        operations.put(CommandName.COMMAND_VIEW_ALL_BOOKS, new ViewAllBooksCommand());
        operations.put(CommandName.COMMAND_VIEW_ALL_BORROW_RECORDS_BY_ADMIN, new ViewAllBorrowRecordsByAdminCommand());
        operations.put(CommandName.COMMAND_VIEW_ALL_BORROW_RECORDS_BY_USER, new ViewAllBorrowRecordsByUserCommand());
        operations.put(CommandName.COMMAND_VIEW_ALL_USERS, new ViewAllUsersCommand());
        operations.put(CommandName.COMMAND_VIEW_BOOK, new ViewBookCommand());
        operations.put(CommandName.COMMAND_VIEW_PROFILE, new ViewProfileCommand());
    }

    public Command createCommand(String commandName) {
        Command command = null;
        try {
            command = operations.get(commandName);
        } catch (IllegalArgumentException e) {
            log.error(e);
        }
        return command;
    }
}
