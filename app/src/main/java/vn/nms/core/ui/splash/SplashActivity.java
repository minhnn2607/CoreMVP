package vn.nms.core.ui.splash;

import android.os.Bundle;
import android.os.Handler;

import vn.nms.core.R;
import vn.nms.core.base.BaseActivity;
import vn.nms.core.data.entity.User;
import vn.nms.core.services.local.UserManager;
import vn.nms.core.ui.account.sign_in.SignInActivity;
import vn.nms.core.ui.home.HomeActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> {
            User loginEntity = UserManager.getInstance().getUserInfo();
            if (loginEntity == null) {
                SignInActivity.start(this);
                finish();
            } else {
                HomeActivity.start(SplashActivity.this);
                finish();
            }

        }, 500);
    }
}
