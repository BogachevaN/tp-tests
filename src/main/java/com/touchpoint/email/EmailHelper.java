package com.touchpoint.email;

import javax.mail.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

import static com.touchpoint.environment.PropertyManager.getProperty;
import static com.touchpoint.services.BaseService.getScenarioEnvironment;

public final class EmailHelper {
    private static final String IMAP_AUTH_EMAIL = String.valueOf(getScenarioEnvironment().getVar("email"));
    private static final String IMAP_AUTH_PWD = getProperty("passwordEmail");
    private static final String IMAP_HOST = "imap.gmail.com";
    private static final String IMAP_PORT = "993";

    public static Message getLastEmail () {
        Properties props = System.getProperties();
        props.put("mail.store.protocol" , "imaps"  );
        props.put("mail.imap.port", IMAP_PORT);
        props.put("mail.imap.ssl.enable", "true");

        EmailAuthenticator auth = new EmailAuthenticator(IMAP_AUTH_EMAIL, IMAP_AUTH_PWD);

        Session session = Session.getDefaultInstance(props, auth);
        session.setDebug(false);
        Message message = null;
        try {
            Store store = session.getStore();
            store.connect(IMAP_HOST, IMAP_AUTH_EMAIL, IMAP_AUTH_PWD);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            message = inbox.getMessage(inbox.getMessageCount());
            Address[] addresses = message.getAllRecipients();

            while (!Arrays.stream(addresses).anyMatch(x -> x.toString().equals(IMAP_AUTH_EMAIL))) {
                message = inbox.getMessage(inbox.getMessageCount());
                 addresses = message.getAllRecipients();
            }

        } catch (NoSuchProviderException e) {
            System.err.println(e.getMessage());
        } catch (MessagingException e) {
            System.err.println(e.getMessage());
        }
        return message;
    }

}
