package ru.geekbrains.notifier.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.notifier.CommunicationType;
import ru.geekbrains.notifier.repositories.EmailNotifier;
import ru.geekbrains.notifier.repositories.FacebookNotifier;

@Service
@RequiredArgsConstructor
public class NotificationService {
    public final EmailNotifier emailNotifier;

    public final FacebookNotifier facebookNotifier;

    public void getNotification(CommunicationType type, String address, String subject, String message) throws Exception {
        if (type.equals(CommunicationType.FACEBOOK)) {
            emailNotifier.notify(address, subject, message);
        }
        if (type.equals(CommunicationType.EMAIL)) {
            facebookNotifier.notify(address, subject, message);
        }
        throw new Exception("данный тип рассылки не поддерживается");
    }
}
