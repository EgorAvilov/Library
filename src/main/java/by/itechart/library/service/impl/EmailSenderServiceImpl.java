package by.itechart.library.service.impl;

import by.itechart.library.dao.DAOFactory;
import by.itechart.library.dao.api.BorrowRecordDAO;
import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.service.dto.EmailSenderDto;
import by.itechart.library.service.exception.ServiceException;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

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
import java.util.TimerTask;

//поставить ежесуточный таймер
/* Timer timer = new Timer("Timer");
         long delay  = 3000L;
         long period = 3000L;
         timer.scheduleAtFixedRate(repeatedTask, delay, period);*/
public class EmailSenderServiceImpl implements Job {


    private DAOFactory daoFactory = DAOFactory.getInstance();
    private BorrowRecordDAO borrowRecordDAO = daoFactory.getBorrowRecordDAO();


    public void sendRemindBefore7Days() throws ServiceException {
        List<EmailSenderDto> emailSenderDtoList;
        try {
            emailSenderDtoList = borrowRecordDAO.getAllBorrowRecordsForRemind(LocalDate.now()
                                                                                       .plusDays(7));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        TimerTask repeatedTask = new TimerTask() {
            @Override
            public void run() {
                for (EmailSenderDto emailSenderDto : emailSenderDtoList) {
                    try {
                        //  if (emailSenderDto.getDueDate().isEqual(LocalDate.now().plusDays(7))) {
                        StringTemplateGroup group = new StringTemplateGroup("myGroup", "src/main/resources", DefaultTemplateLexer.class);
                        StringTemplate helloAgain = group.getInstanceOf("RemindBefore");

                        helloAgain.setAttribute("firstName", emailSenderDto.getUserFirstName());
                        helloAgain.setAttribute("bookTitle", emailSenderDto.getBookTitle());
                        helloAgain.setAttribute("dueDate", LocalDate.now()
                                                                    .plusDays(7));

                        Properties properties = new Properties();
                        properties.load(EmailSenderServiceImpl.class.getClassLoader()
                                                                    .getResourceAsStream("mail.properties"));

                        Session session = Session.getDefaultInstance(properties);
                        MimeMessage message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(properties.get("mail.smtps.user")
                                                                      .toString()));
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress("egoravilov999@mail.ru"));
                        message.setSubject("Test");
                        message.setText(helloAgain.toString());

                        Transport tr = session.getTransport();
                        tr.connect("egoravilov99@gmail.com", "7920178@mail.ru");
                        tr.sendMessage(message, message.getAllRecipients());
                        tr.close();
                    } catch (IOException | MessagingException e) {
                        e.printStackTrace();
                    }
                    // }
                }
            }
        };

    }


    public void sendRemindBefore1Day() throws ServiceException {
        List<EmailSenderDto> emailSenderDtoList;
        try {
            emailSenderDtoList = borrowRecordDAO.getAllBorrowRecordsForRemind(LocalDate.now()
                                                                                       .plusDays(1));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        TimerTask repeatedTask = new TimerTask() {
            @Override
            public void run() {
                for (EmailSenderDto emailSenderDto : emailSenderDtoList) {
                    try {
                        //  if (emailSenderDto.getDueDate().isEqual(LocalDate.now().plusDays(7))) {
                        StringTemplateGroup group = new StringTemplateGroup("myGroup", "src/main/resources", DefaultTemplateLexer.class);
                        StringTemplate helloAgain = group.getInstanceOf("RemindBefore");

                        helloAgain.setAttribute("firstName", emailSenderDto.getUserFirstName());
                        helloAgain.setAttribute("bookTitle", emailSenderDto.getBookTitle());
                        helloAgain.setAttribute("dueDate", LocalDate.now()
                                                                    .plusDays(1));

                        Properties properties = new Properties();
                        properties.load(EmailSenderServiceImpl.class.getClassLoader()
                                                                    .getResourceAsStream("mail.properties"));

                        Session session = Session.getDefaultInstance(properties);
                        MimeMessage message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(properties.get("mail.smtps.user")
                                                                      .toString()));
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress("egoravilov999@mail.ru"));
                        message.setSubject("Test");
                        message.setText(helloAgain.toString());

                        Transport tr = session.getTransport();
                        tr.connect("egoravilov99@gmail.com", "7920178@mail.ru");
                        tr.sendMessage(message, message.getAllRecipients());
                        tr.close();
                    } catch (IOException | MessagingException e) {
                        e.printStackTrace();
                    }
                    // }
                }
            }
        };

    }


    public void sendRemindAfter1Day() throws ServiceException {
        List<EmailSenderDto> emailSenderDtoList;
        try {
            emailSenderDtoList = borrowRecordDAO.getAllBorrowRecordsForRemind(LocalDate.now()
                                                                                       .minusDays(1));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        TimerTask repeatedTask = new TimerTask() {
            @Override
            public void run() {
                for (EmailSenderDto emailSenderDto : emailSenderDtoList) {
                    try {
                        //  if (emailSenderDto.getDueDate().isEqual(LocalDate.now().plusDays(7))) {
                        StringTemplateGroup group = new StringTemplateGroup("myGroup", "src/main/resources", DefaultTemplateLexer.class);
                        StringTemplate helloAgain = group.getInstanceOf("RemindAfter");

                        helloAgain.setAttribute("firstName", emailSenderDto.getUserFirstName());
                        helloAgain.setAttribute("bookTitle", emailSenderDto.getBookTitle());
                        helloAgain.setAttribute("dueDate", LocalDate.now()
                                                                    .minusDays(1));

                        Properties properties = new Properties();
                        properties.load(EmailSenderServiceImpl.class.getClassLoader()
                                                                    .getResourceAsStream("mail.properties"));

                        Session session = Session.getDefaultInstance(properties);
                        MimeMessage message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(properties.get("mail.smtps.user")
                                                                      .toString()));
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress("egoravilov999@mail.ru"));
                        message.setSubject("Test");
                        message.setText(helloAgain.toString());

                        Transport tr = session.getTransport();
                        tr.connect("egoravilov99@gmail.com", "7920178@mail.ru");
                        tr.sendMessage(message, message.getAllRecipients());
                        tr.close();
                    } catch (IOException | MessagingException e) {
                        e.printStackTrace();
                    }
                    // }
                }
            }
        };
    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            sendRemindBefore7Days();
            sendRemindBefore1Day();
            sendRemindAfter1Day();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}

