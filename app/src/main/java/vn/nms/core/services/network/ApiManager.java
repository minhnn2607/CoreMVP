package vn.nms.core.services.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import vn.nms.core.AppSettings;
import vn.nms.core.BuildConfig;
import vn.nms.core.Constant;
import vn.nms.core.base.BaseApp;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static final String BASE_URL = BuildConfig.HOST;
    private ApiInterface apiInterface;
    private static ApiManager instance = null;

    public static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }
        return instance;
    }

    private ApiManager() {
        buildRetrofit();
    }

    private void buildRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor(new ChuckInterceptor(BaseApp.getAppContext()).showNotification(true));
        builder.connectTimeout(AppSettings.TIMEOUT_CONNECT, TimeUnit.SECONDS);
        builder.readTimeout(AppSettings.TIMEOUT_CONNECT, TimeUnit.SECONDS);
        builder.writeTimeout(AppSettings.TIMEOUT_CONNECT, TimeUnit.SECONDS);
        builder.addInterceptor(new AppInterceptor());

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat(Constant.DATE_FORMAT_PATTERN)
                .create();

        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory
                .createWithScheduler(Schedulers.io());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .build();
        this.apiInterface = retrofit.create(ApiInterface.class);
    }

    public ApiInterface getApiInterface() {
        return this.apiInterface;
    }

}
