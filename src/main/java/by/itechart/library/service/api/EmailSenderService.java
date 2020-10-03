package by.itechart.library.service.api;

import org.antlr.stringtemplate.StringTemplate;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailSenderService {
    void execute();

    void sendRemindBefore7Days();

    void sendRemindBefore1Day();

    void sendRemindAfter1Day();

    void send(StringTemplate mail) throws IOException, MessagingException;

}
