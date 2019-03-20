package com.mail.mail.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.naming.Context;

/**
 * Created by asus on 2019/3/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Resource
    private MailService mailService;

    @Resource
    TemplateEngine templateEngine;

    private String to="填写发送目标";

    @Test
    public void sendSimpleMail(){
        mailService.sendSimpleMail("2080483970@qq.com","测试","这是一封测试邮件");
    }

    @Test
    public void sendHtmlMail(){
        String content="<html>\n<body>\n<h3>测试html邮件</h3>\n</body>\n</html>";
        try {
            mailService.sendHtmlMail(to,"测试html邮件",content);
        }catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendAttachmentMail(){
        String content="<html>\n<body>\n<h3>测试附件邮件</h3>\n</body>\n</html>";
        String path="D:\\ideaJava代码\\mail\\src\\main\\resources\\噬神者.jpg";
        try {
            mailService.sendAttachmentsMail(to,"测试附件邮件",content,path);
        }catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendInlinResourceMail(){
        String rsc="D:\\ideaJava代码\\mail\\src\\main\\resources\\噬神者.jpg";
        String rscId="image001";
        String content="<html><body>测试图片邮件<img src=\'cid:"+rscId+"\'></img></body></html>";
        try {
            mailService.sendInlinResourceMail(to,"测试图片邮件",content,rsc,rscId);
        }catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendTemplateMail(){
        org.thymeleaf.context.Context context=new org.thymeleaf.context.Context();
        context.setVariable("tab","repositories");
        String content=templateEngine.process("emailTemplate",context);
        try {
            mailService.sendHtmlMail(to,"模板邮件",content);
        }catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
