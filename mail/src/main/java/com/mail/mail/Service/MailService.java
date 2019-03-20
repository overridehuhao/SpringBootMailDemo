package com.mail.mail.Service;


import javax.mail.MessagingException;

/**
 * Created by asus on 2019/3/19.
 */
public interface MailService {
    //发送简单文本邮件
    public void sendSimpleMail(String to,String subject,String content);

    //发送html文件
    public void sendHtmlMail(String to,String subject,String content) throws MessagingException;

    //发送带附件的邮件，若有多个不同附件，可以将filePath设置为数组，然后在方法里遍历数组
    public void sendAttachmentsMail(String to,String subject,String content,String filePath) throws MessagingException;

    //发送图片邮件，若有多个不同图片，可以将rscPath和rscId设置为数组，然后在方法里遍历数组
    public void sendInlinResourceMail(String to,String subject,String content,String rscPath,String rscId) throws MessagingException;
}
