package vn.nms.core.services.local;

import vn.nms.core.Constant;
import vn.nms.core.base.BaseApp;
import com.securepreferences.SecurePreferences;

public class SharedPrefs {
    private final static SharedPrefs mInstance = new SharedPrefs();
    private final SecurePreferences mSharedPreferences;

    private SharedPrefs() {
        mSharedPreferences = new SecurePreferences(BaseApp.getAppContext(),
                Constant.PREF_KEY, Constant.PREF_NAME);
    }

    public static SharedPrefs getInstance() {
        return mInstance;
    }

    public <T> void put(String key, T data) {
        SecurePreferences.Editor editor = mSharedPreferences.edit();
        if (data instanceof String) {
            editor.putString(key, (String) data);
        } else if (data instanceof Boolean) {
            editor.putBoolean(key, (Boolean) data);
        } else if (data instanceof Float) {
            editor.putFloat(key, (Float) data);
        } else if (data instanceof Integer) {
            editor.putInt(key, (Integer) data);
        } else if (data instanceof Long) {
            editor.putLong(key, (Long) data);
        } else {
            editor.putString(key, BaseApp.getAppContext().getGSon().toJson(data));
        }
        editor.apply();
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> anonymousClass) {

        if (anonymousClass == String.class) {
            try {
                return (T) mSharedPreferences.getString(key, "");
            } catch (ClassCastException e) {
                return (T) "";
            }
        } else if (anonymousClass == Boolean.class) {
            try {
                return (T) Boolean.valueOf(mSharedPreferences.getBoolean(key, false));
            } catch (ClassCastException e) {
                return (T) Boolean.FALSE;
            }
        } else if (anonymousClass == Float.class) {
            try {
                return (T) Float.valueOf(mSharedPreferences.getFloat(key, 0));
            } catch (ClassCastException e) {
                return (T) Float.valueOf(0);
            }
        } else if (anonymousClass == Integer.class) {
            try {
                return (T) Integer.valueOf(mSharedPreferences.getInt(key, 0));
            } catch (ClassCastException e) {
                return (T) Integer.valueOf(0);
            }
        } else if (anonymousClass == Long.class) {
            try {
                return (T) Long.valueOf(mSharedPreferences.getLong(key, 0));
            } catch (ClassCastException e) {
                return (T) Long.valueOf(0);
            }
        } else {
            return BaseApp.getAppContext().getGSon()
                    .fromJson(mSharedPreferences.getString(key, ""), anonymousClass);
        }

    }

    public void remove(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }

    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }
}
