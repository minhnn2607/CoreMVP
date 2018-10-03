package vn.nms.core.utils;

import vn.nms.core.R;
import vn.nms.core.base.BaseApp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ErrorHandler {
    public static String getErrorMessage(Throwable e) {

        if (e instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) e).response().errorBody();
            if (responseBody != null)
                return getErrorMessage(((HttpException) e).code(), responseBody);
        } else if (e instanceof SocketTimeoutException) {
            return BaseApp.getAppContext().getString(R.string.error_timeout_connect);
        } else if (e instanceof IOException) {
            return BaseApp.getAppContext().getString(R.string.error_no_internet);
        }
        return BaseApp.getAppContext().getString(R.string.error_unknown);
    }

    private static String getErrorMessage(int errorCode, ResponseBody responseBody) {
        String defaultMsg = BaseApp.getAppContext().getString(R.string.error_unknown);
        String errorBody = defaultMsg;
        try {
            errorBody = responseBody.string().replace("\"", "");
            if (!errorBody.equals("")) {
                JSONObject jsonObject = new JSONObject(errorBody);
                return jsonObject.optString("message");
            } else {
                return defaultMsg + "[" + errorCode + "]";
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return errorBody;
    }
}
