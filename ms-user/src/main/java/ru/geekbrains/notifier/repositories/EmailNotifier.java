package ru.geekbrains.notifier.repositories;

import org.springframework.stereotype.Repository;
import ru.geekbrains.notifier.Notifier;

@Repository
public class EmailNotifier extends Notifier {
    @Override
    protected void login() {

    }

    @Override
    protected void send(String address, String subject, String message) {

    }

    @Override
    protected void logout() {

    }
}
