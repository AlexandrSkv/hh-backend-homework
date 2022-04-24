package ru.hh.checkly.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.hh.checkly.dto.MailMessage;
import ru.hh.checkly.service.MailSenderService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * Сервис отправки mail-сообщений
 *
 * @author strelchm
 */
@Service
public class MailSenderServiceImpl implements MailSenderService {

  private final JavaMailSender mailSender;
  private static final Logger LOGGER = LoggerFactory.getLogger(MailSenderServiceImpl.class);

  @Autowired
  public MailSenderServiceImpl(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  /**
   * Отправка сообщения (с файлами-вложениями)
   *
   * @param message - pojo письма
   * @throws MessagingException - разные варианты ошибок отправки сообщения
   */
  @Override
  public void sendEmailWithAttachment(MailMessage message) {
    try {
      MimeMessage msg = mailSender.createMimeMessage();

      MimeMessageHelper helper = new MimeMessageHelper(msg, true);

      helper.setTo(message.getReceiverAddresses());
      helper.setSubject(message.getSubject());
      helper.setText(message.getBody(), message.isHtmlBody());

      for (Map.Entry<String, File> entry : message.getFiles().entrySet()) {
        helper.addAttachment(entry.getKey(), entry.getValue());
      }

      mailSender.send(msg);
    } catch (Exception ex) {
      LOGGER.error(String.format("Error during message %s sending", message), ex);
    }
  }
}