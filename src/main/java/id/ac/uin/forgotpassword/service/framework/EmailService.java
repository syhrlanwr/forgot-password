package id.ac.uin.forgotpassword.service.framework;

import id.ac.uin.forgotpassword.entity.Mail;

public interface EmailService {
    void send(Mail mail);
}
