package com.mail.mail.Service.Impl;

import com.mail.mail.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by asus on 2019/3/19.
 */
@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(to);//发送给目标to
        simpleMailMessage.setFrom(from);//发送人为from
        simpleMailMessage.setSubject(subject);//发送邮件的主题
        simpleMailMessage.setText(content);//发送邮件的内容

        mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage= mailSender.createMimeMessage();//使用mime协议的封装类

        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);//   //创建MimeMessageHelper实例，第2个参数表示是否为multipart邮件，第3个参数表示MimeMessage的编码

        helper.setTo(to);
        helper.setFrom(from);
        helper.setSubject(subject);
        helper.setText(content,true);//第一个参数为邮件内容，第二个参数代表是否是html格式

        mailSender.send(mimeMessage);

    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) throws MessagingException {
        MimeMessage mimeMessage= mailSender.createMimeMessage();

        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);

        helper.setTo(to);
        helper.setFrom(from);
        helper.setSubject(subject);
        helper.setText(content,true);

        FileSystemResource file=new FileSystemResource(new File(filePath));//读取文件到FileSystemResource类
        String fileName=file.getFilename();//获取文件名

        helper.addAttachment(fileName,file);//可以发送多个附件
        helper.addAttachment(fileName,file);

        mailSender.send(mimeMessage);
    }

    @Override
    public void sendInlinResourceMail(String to, String subject, String content, String rscPath, String rscId) throws MessagingException {
        MimeMessage mimeMessage= mailSender.createMimeMessage();

        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);

        helper.setTo(to);
        helper.setFrom(from);
        helper.setSubject(subject);
        helper.setText(content,true);

        FileSystemResource res=new FileSystemResource(new File(rscPath));//读取文件到FileSystemResource类
        helper.addInline(rscId,res);//设置内嵌资源id和资源

        mailSender.send(mimeMessage);
    }
}
