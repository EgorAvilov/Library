package by.itechart.library.service.impl;

import by.itechart.library.dao.DAOFactory;
import by.itechart.library.dao.api.BorrowRecordDAO;
import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.service.api.EmailSenderService;
import by.itechart.library.service.dto.EmailSenderDto;
import lombok.extern.log4j.Log4j;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

@Log4j
public class EmailSenderServiceImpl implements EmailSenderService {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private BorrowRecordDAO borrowRecordDAO = daoFactory.getBorrowRecordDAO();

    @Override
    public void sendRemindBefore7Days() {
        TimerTask repeatedTask = new TimerTask() {
            @Override
            public void run() {
                List<EmailSenderDto> emailSenderDtoList = null;
                try {
                    emailSenderDtoList = borrowRecordDAO.getAllBorrowRecordsForRemind(LocalDate.now()
                            .plusDays(7));
                } catch (DAOException e) {
                    e.printStackTrace();
                }
                if (emailSenderDtoList != null) {
                    for (EmailSenderDto emailSenderDto : emailSenderDtoList) {
                        StringTemplateGroup group = new StringTemplateGroup("src/main/resources", DefaultTemplateLexer.class);
                        StringTemplate mail = group.getInstanceOf("RemindBefore");

                        mail.setAttribute("firstName", emailSenderDto.getUserFirstName());
                        mail.setAttribute("bookTitle", emailSenderDto.getBookTitle());
                        mail.setAttribute("dueDate", LocalDate.now()
                                .plusDays(7));
                        try {
                            send(mail, emailSenderDto);
                        } catch (IOException | MessagingException e) {
                            log.error(e);
                        }
                    }
                }
            }
        };
        Timer timer = new Timer("Timer");
        long delay = 1000L;
        long period = 1000L * 60L * 60L * 24L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);

    }

    @Override
    public void sendRemindBefore1Day() {
        TimerTask repeatedTask = new TimerTask() {
            @Override
            public void run() {
                List<EmailSenderDto> emailSenderDtoList = null;
                try {
                    emailSenderDtoList = borrowRecordDAO.getAllBorrowRecordsForRemind(LocalDate.now()
                            .plusDays(1));
                } catch (DAOException e) {
                    e.printStackTrace();
                }
                if (emailSenderDtoList != null) {
                    for (EmailSenderDto emailSenderDto : emailSenderDtoList) {

                        StringTemplateGroup group = new StringTemplateGroup("src/main/resources", DefaultTemplateLexer.class);
                        StringTemplate mail = group.getInstanceOf("RemindBefore");

                        mail.setAttribute("firstName", emailSenderDto.getUserFirstName());
                        mail.setAttribute("bookTitle", emailSenderDto.getBookTitle());
                        mail.setAttribute("dueDate", LocalDate.now()
                                .plusDays(1));
                        try {
                            send(mail, emailSenderDto);
                        } catch (IOException | MessagingException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        };
        Timer timer = new Timer("Timer");
        long delay = 1000L;
        long period = 1000L * 60L * 60L * 24L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);

    }

    @Override
    public void sendRemindAfter1Day() {

        TimerTask repeatedTask = new TimerTask() {

            @Override
            public void run() {
                List<EmailSenderDto> emailSenderDtoList = null;
                try {
                    emailSenderDtoList = borrowRecordDAO.getAllBorrowRecordsForRemind(LocalDate.now()
                            .minusDays(1));
                } catch (DAOException e) {
                    e.printStackTrace();
                }
                if (emailSenderDtoList != null) {
                    for (EmailSenderDto emailSenderDto : emailSenderDtoList) {

                        StringTemplateGroup group = new StringTemplateGroup("src/main/resources", DefaultTemplateLexer.class);
                        StringTemplate mail = group.getInstanceOf("RemindAfter");

                        mail.setAttribute("firstName", emailSenderDto.getUserFirstName());
                        mail.setAttribute("bookTitle", emailSenderDto.getBookTitle());
                        mail.setAttribute("dueDate", LocalDate.now()
                                .minusDays(1));
                        try {
                            send(mail, emailSenderDto);
                        } catch (IOException | MessagingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        Timer timer = new Timer("Timer");
        long delay = 1000L;
        long period = 1000L * 60L * 60L * 24L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

    @Override
    public void execute() {
        sendRemindBefore7Days();
        sendRemindBefore1Day();
        sendRemindAfter1Day();
    }

    @Override
    public void send(StringTemplate mail, EmailSenderDto emailSenderDto) throws IOException, MessagingException {
        Properties properties = new Properties();
        properties.load(EmailSenderServiceImpl.class.getClassLoader()
                .getResourceAsStream("mail.properties"));
        mail.setAttribute("libraryEmail", properties.get("mail.smtps.user").toString());
        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(properties.get("mail.smtps.user").toString()));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailSenderDto.getUserEmail()));
        message.setSubject("Library Borrow Record Remind");
        message.setText(mail.toString());

        Transport tr = session.getTransport();
        tr.connect(properties.get("mail.smtps.user").toString(), properties.get("mail.smtps.password").toString());
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
    }
}

