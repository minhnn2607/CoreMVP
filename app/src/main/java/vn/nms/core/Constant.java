package vn.nms.core;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Constant {
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String PREF_NAME = "appPref";
    public static final String PREF_KEY = "coreprefkey@18";
    public static final String DB_NAME = "appDb";
    public static final int DB_VERSION = 1;
    public static final String PREF_USER_INFO = "userInfo";
    public static final String PREF_TIME_INSTALL = "time_install";

    public static final String EXTRA_TYPE = "type";
    public static final String EXTRA_USER_NAME = "userName";
    public static final String EXTRA_DATA = "data";

    public static final int CALENDAR_REQUEST_CODE = 1001;

    public static final int DELAY_REFRESH = 500;

    @StringDef({Gender.MALE, Gender.FEMALE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Gender {
        String MALE = "Male";
        String FEMALE = "Female";
    }

}