package ru.hh.checkly.dto;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Pojo mail-сообщения для MailSenderServiceImpl-сервиса
 *
 * @author strelchm
 */
public class MailMessage {

    private final Map<String, File> files = new HashMap<>(); // словарь: ключ - название файла с расширением; значение - сам файл

    private String[] receiverAddresses;

    private String subject;

    private String body;

    private boolean htmlBody;

    /**
     * Конструктор mail-сообщения с обычным текстовым (не html) телом
     *
     * @param receiverAddresses - адреса получателя
     * @param subject         - заголовок
     * @param body            - тело сообщения (основная текстовая часть)
     */
    public MailMessage(String subject, String body, String... receiverAddresses) {
        this(subject, body, false, receiverAddresses);
    }

    /**
     * Конструктор mail-сообщения
     *
     * @param receiverAddresses - адреса получателя
     * @param subject         - заголовок
     * @param body            - тело сообщения (основная текстовая часть)
     * @param htmlBody        - true при html теле сообщения; false при обычном тексте
     */
    public MailMessage(String subject, String body, boolean htmlBody, String... receiverAddresses) {
        this.receiverAddresses = receiverAddresses;
        this.subject = subject;
        this.body = body;
        this.htmlBody = htmlBody;
    }

    /**
     * Приложения файла к сообщению
     *
     * @param fileName - имя файла (с расширением)
     * @param file     - файл
     */
    public File putFile(String fileName, File file) {
        return files.put(fileName, file);
    }

    public Map<String, File> getFiles() {
        return files;
    }

    public String[] getReceiverAddresses() {
        return receiverAddresses;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public boolean isHtmlBody() {
        return htmlBody;
    }
}