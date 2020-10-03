package by.itechart.library.service.api;

import org.antlr.stringtemplate.StringTemplate;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailSenderService {
    public void execute();
    public void sendRemindBefore7Days();
    public void sendRemindBefore1Day();
    public void sendRemindAfter1Day();
    public void send(StringTemplate mail) throws IOException, MessagingException;

}
