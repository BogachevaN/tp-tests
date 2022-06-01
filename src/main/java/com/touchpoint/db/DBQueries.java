package com.touchpoint.db;

public final class DBQueries {
    public static final String SELECT_LAST_TEST_EMAIL = "select email from users where email like '%%%s%%' " +
            "order by created_at desc limit 1";
}
