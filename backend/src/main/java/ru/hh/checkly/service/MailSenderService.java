package ru.hh.checkly.service;

import ru.hh.checkly.dto.MailMessage;

import javax.mail.MessagingException;

public interface MailSenderService {

  void sendEmailWithAttachment(MailMessage message);
}