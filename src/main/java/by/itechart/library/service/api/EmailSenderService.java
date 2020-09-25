package by.itechart.library.service.api;

import by.itechart.library.service.exception.ServiceException;

public interface EmailSenderService {

    public void sendRemindBefore7Days() throws ServiceException;

    public void sendRemindBefore1Day() throws ServiceException;

    public void sendRemindAfter1Day() throws ServiceException;
}
