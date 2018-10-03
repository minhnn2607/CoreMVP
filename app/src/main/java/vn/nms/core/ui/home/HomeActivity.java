package vn.nms.core.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import vn.nms.core.R;
import vn.nms.core.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initHeader();
    }

    public static void start(Activity activity) {
        Intent starter = new Intent(activity, HomeActivity.class);
        activity.startActivity(starter);
    }
}
