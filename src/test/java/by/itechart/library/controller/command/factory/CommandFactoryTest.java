package by.itechart.library.controller.command.factory;

import by.itechart.library.controller.command.Command;
import by.itechart.library.controller.command.impl.*;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommandFactoryTest {


    CommandFactory commandFactory = CommandFactory.getInstance();
    String commandName;
    Command command;

    @Test
    public void createCommand_AddBook_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof AddBookCommand);
    }

    @Test
    public void createCommand_AddBook_valid() {
        commandName = CommandName.COMMAND_ADD_BOOK;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof AddBookCommand);
    }

    @Test
    public void createCommand_AddBorrowRecord_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof AddBorrowRecordCommand);
    }

    @Test
    public void createCommand_AddBorrowRecord_valid() {
        commandName = CommandName.COMMAND_ADD_BORROW_RECORD;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof AddBorrowRecordCommand);
    }

    @Test
    public void createCommand_ChangeBookDeleteStatus_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof ChangeBookDeletedStatusCommand);
    }

    @Test
    public void createCommand_ChangeBookDeleteStatus_valid() {
        commandName = CommandName.COMMAND_CHANGE_BOOK_DELETED_STATUS;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof ChangeBookDeletedStatusCommand);
    }

    @Test
    public void createCommand_ChangeUserDeleteStatus_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof ChangeUserDeletedStatusCommand);
    }

    @Test
    public void createCommand_ChangeUserDeleteStatus_valid() {
        commandName = CommandName.COMMAND_CHANGE_USER_DELETED_STATUS;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof ChangeUserDeletedStatusCommand);
    }

    @Test
    public void createCommand_EditBook_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof EditBookCommand);
    }

    @Test
    public void createCommand_EditBook_valid() {
        commandName = CommandName.COMMAND_EDIT_BOOK;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof EditBookCommand);
    }

    @Test
    public void createCommand_EditBorrowRecordByAdmin_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof EditBorrowRecordByAdminCommand);
    }

    @Test
    public void createCommand_EditBorrowRecordByAdmin_valid() {
        commandName = CommandName.COMMAND_EDIT_BORROW_RECORD_BY_ADMIN;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof EditBorrowRecordByAdminCommand);
    }

    @Test
    public void createCommand_EditBorrowRecordByUser_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof EditBorrowRecordByUserCommand);
    }

    @Test
    public void createCommand_EditBorrowRecordByUser_valid() {
        commandName = CommandName.COMMAND_EDIT_BORROW_RECORD_BY_USER;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof EditBorrowRecordByUserCommand);
    }

    @Test
    public void createCommand_EditProfile_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof EditProfileCommand);
    }

    @Test
    public void createCommand_EditProfile_valid() {
        commandName = CommandName.COMMAND_EDIT_PROFILE;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof EditProfileCommand);
    }

    @Test
    public void createCommand_ForwardEditBook_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof ForwardEditBookCommand);
    }

    @Test
    public void createCommand_ForwardEditBook_valid() {
        commandName = CommandName.COMMAND_FORWARD_EDIT_BOOK;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof ForwardEditBookCommand);
    }

    @Test
    public void createCommand_ForwardEditBorrowRecord_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof ForwardEditBorrowRecordCommand);
    }

    @Test
    public void createCommand_ForwardEditBorrowRecord_valid() {
        commandName = CommandName.COMMAND_FORWARD_EDIT_BORROW_RECORD;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof ForwardEditBorrowRecordCommand);
    }

    @Test
    public void createCommand_ForwardEditProfile_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof ForwardEditProfileCommand);
    }

    @Test
    public void createCommand_ForwardEditProfile_valid() {
        commandName = CommandName.COMMAND_FORWARD_EDIT_PROFILE;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof ForwardEditProfileCommand);
    }

    @Test
    public void createCommand_ForwardToMainPage_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof ForwardToMainPageCommand);
    }

    @Test
    public void createCommand_ForwardToMainPage_valid() {
        commandName = CommandName.COMMAND_FORWARD_TO_MAIN_PAGE;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof ForwardToMainPageCommand);
    }

    @Test
    public void createCommand_SearchBook_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof SearchBookCommand);
    }

    @Test
    public void createCommand_SearchBook_valid() {
        commandName = CommandName.COMMAND_SEARCH_BOOK;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof SearchBookCommand);
    }

    @Test
    public void createCommand_SignIn_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof SignInCommand);
    }

    @Test
    public void createCommand_SignIn_valid() {
        commandName = CommandName.COMMAND_SIGN_IN;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof SignInCommand);
    }

    @Test
    public void createCommand_SignOut_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof SignOutCommand);
    }

    @Test
    public void createCommand_SignOut_valid() {
        commandName = CommandName.COMMAND_SIGN_OUT;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof SignOutCommand);
    }

    @Test
    public void createCommand_SignUp_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof SignUpCommand);
    }

    @Test
    public void createCommand_SignUp_valid() {
        commandName = CommandName.COMMAND_SIGN_UP;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof SignUpCommand);
    }

    @Test
    public void createCommand_ViewAllBooks_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof ViewAllBooksCommand);
    }

    @Test
    public void createCommand_ViewAllBooks_valid() {
        commandName = CommandName.COMMAND_VIEW_ALL_BOOKS;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof ViewAllBooksCommand);
    }

    @Test
    public void createCommand_ViewAllBorrowRecordsByAdmin_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof ViewAllBorrowRecordsByAdminCommand);
    }

    @Test
    public void createCommand_ViewAllBorrowRecordsByAdmin_valid() {
        commandName = CommandName.COMMAND_VIEW_ALL_BORROW_RECORDS_BY_ADMIN;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof ViewAllBorrowRecordsByAdminCommand);
    }

    @Test
    public void createCommand_ViewAllBorrowRecordsByUser_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof ViewAllBorrowRecordsByUserCommand);
    }

    @Test
    public void createCommand_ViewAllBorrowRecordsByUser_valid() {
        commandName = CommandName.COMMAND_VIEW_ALL_BORROW_RECORDS_BY_USER;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof ViewAllBorrowRecordsByUserCommand);
    }

    @Test
    public void createCommand_ViewAllUsers_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof ViewAllUsersCommand);
    }

    @Test
    public void createCommand_ViewAllUsers_valid() {
        commandName = CommandName.COMMAND_VIEW_ALL_USERS;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof ViewAllUsersCommand);
    }

    @Test
    public void createCommand_ViewBook_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof ViewBookCommand);
    }

    @Test
    public void createCommand_ViewBook_valid() {
        commandName = CommandName.COMMAND_VIEW_BOOK;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof ViewBookCommand);
    }

    @Test
    public void createCommand_ViewProfile_invalid() {
        commandName = "";
        command = commandFactory.createCommand(commandName);
        assertFalse(command instanceof ViewProfileCommand);
    }

    @Test
    public void createCommand_ViewProfile_valid() {
        commandName = CommandName.COMMAND_VIEW_PROFILE;
        command = commandFactory.createCommand(commandName);
        assertTrue(command instanceof ViewProfileCommand);
    }
}