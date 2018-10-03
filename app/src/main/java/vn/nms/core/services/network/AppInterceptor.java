package vn.nms.core.services.network;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import vn.nms.core.data.entity.User;
import vn.nms.core.services.local.UserManager;

class AppInterceptor implements Interceptor {
    @SuppressLint("CheckResult")
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        User loginEntity = UserManager.getInstance().getUserInfo();
        if (loginEntity != null) {
            newBuilder.addHeader("Authorization", loginEntity.getTokenType() + " "
                    + loginEntity.getAccessToken());
            Response mainResponse = chain.proceed(newBuilder.build());
            return mainResponse;
        } else {
            return chain.proceed(request);
        }
    }
}
