package com.ssafy.togetherhomt.util.Mailing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@PropertySource("classpath:mailConfirm.properties")
@RequiredArgsConstructor
@Slf4j
public class MailingService {

    private final JavaMailSender emailSender;

    private static final Random random = new Random();
    private String ePw;

    @Value("${AdminMail.id}")
    private String ADMIN_ADDRESS;
    @Value("${AdminMail.name}")
    private String ADMIN_NAME;


    private MimeMessage createMessage(String to) throws Exception{
        System.out.println("보내는 대상 : " + to);
        System.out.println("인증 번호 : " + ePw);
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        mimeMessage.addRecipients(Message.RecipientType.TO, to); //보내는 대상
        mimeMessage.setSubject("투게더 홈트 이메일 인증"); //제목

        StringBuilder message = new StringBuilder();
        message.append("<div style='margin:100px;'>");
        message.append("<h1> 안녕하세요 투게더 홈트입니다. </h1>");
        message.append("<br>");
        message.append("<p>아래 코드를 서비스 화면으로 돌아가 입력해주세요<p>");
        message.append("<br>");
        message.append("<p>감사합니다!<p>");
        message.append("<br>");
        message.append("<div align='center' style='border:1px solid black; font-family:verdana';>");
        message.append("<h3 style='color:blue;'>서비스 이용 인증 코드입니다.</h3>");
        message.append("<div style='font-size:130%'>");
        message.append("CODE : <strong>");
        message.append(ePw+"</strong><div><br/> ");
        message.append("</div>");
        mimeMessage.setText(message.toString(), "utf-8", "html"); //내용
        mimeMessage.setFrom(new InternetAddress(ADMIN_ADDRESS, ADMIN_NAME)); //보내는 사람

        return mimeMessage;
    }

    public static String createKey() {
        StringBuilder key = new StringBuilder();

        final char[] UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        final char[] LOWER = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        final char[] DIGIT = "0123456789".toCharArray();
        final char[] SPECIAL = "~!@#$%^&*()_=".toCharArray();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            switch (random.nextInt(3)) {
                case 0: key.append(UPPER[random.nextInt(UPPER.length)]);
                    break;
                case 1: key.append(LOWER[random.nextInt(LOWER.length)]);
                    break;
                case 2: key.append(DIGIT[random.nextInt(DIGIT.length)]);
                    break;
                default: key.append(SPECIAL[random.nextInt(SPECIAL.length)]);
                    break;
            }
        }

        return key.toString();
    }

    public String sendSimpleMessage(String to) throws Exception {
        ePw = createKey();

        MimeMessage message = createMessage(to);
        try{
            emailSender.send(message);
        } catch(MailException es) {
            log.error("MailException", es);
            throw new IllegalArgumentException();
        }
        return ePw;
    }

}
