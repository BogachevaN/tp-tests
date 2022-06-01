package com.touchpoint.services;


import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.touchpoint.email.EmailHelper.*;

public class EmailService {
    public String getConfirmationCode() throws MessagingException, IOException {
        Message message = getLastEmail();
        Multipart mp = (Multipart) message.getContent();
        BodyPart bp = mp.getBodyPart(0);
        String content = bp.getContent().toString();
        Pattern pattern = Pattern.compile("\s[0-9]+\s");
        Matcher matcher = pattern.matcher(content);
        String code = new String();
        while (matcher.find()) {
            code = content.substring(matcher.start(), matcher.end());
        }
        code = code.trim();
        return code;
    }
}
