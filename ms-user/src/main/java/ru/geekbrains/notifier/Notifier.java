package ru.geekbrains.notifier;

public abstract class Notifier {
    public final void notify(String address, String subject, String message) {
        login();
        send(address, subject, message);
        logout();
    }

    protected abstract void login();

    protected abstract void send(String address, String subject, String message);

    protected abstract void logout();


}
