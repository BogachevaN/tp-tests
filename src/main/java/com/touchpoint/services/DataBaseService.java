package com.touchpoint.services;

import com.touchpoint.db.DBQueries;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.touchpoint.environment.PropertyManager.getProperty;
import static com.touchpoint.db.DBHelper.*;

public class DataBaseService {
    public String getEmailValue(String value, String numberOfCharacters) {
        String fieldValue = null;
        switch (value) {
            case "testEmail":
                String testEmail = getProperty("app.testEmail");
                String query = String.format(DBQueries.SELECT_LAST_TEST_EMAIL, testEmail);
                String lastEmail = getSingleResult(query, "email");
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(lastEmail);
                int emailNumber = 0;
                while (matcher.find()) {
                    emailNumber = Integer.parseInt(lastEmail.substring(matcher.start(), matcher.end()));
                    emailNumber++;
                }
                fieldValue = testEmail + "+" + emailNumber + "@" + getProperty("app.domain");
                break;
        }
        return fieldValue;
    }
}
