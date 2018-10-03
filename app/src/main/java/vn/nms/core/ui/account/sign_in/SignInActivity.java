package vn.nms.core.ui.account.sign_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import vn.nms.core.R;
import vn.nms.core.base.BaseActivity;
import vn.nms.core.ui.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends BaseActivity<SignInPresenter> implements SignInView {

    @BindView(R.id.etUserName)
    AutoCompleteTextView etUserName;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.tilUserName)
    TextInputLayout tilUserName;
    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        unbinder = ButterKnife.bind(this);
        mPresenter = new SignInPresenter();
        mPresenter.attachView(this);
        initView();
    }

    private void initView() {
        mPresenter.addTextWatcher(etUserName, etPassword);
    }

    private void doSignIn() {
        String userName = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString();
        mPresenter.doSignIn(userName, password);
    }

    public static void start(Activity activity) {
        Intent starter = new Intent(activity, SignInActivity.class);
        activity.startActivity(starter);
    }

    @OnClick({R.id.btnSignIn, R.id.container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSignIn:
                doSignIn();
                break;
            case R.id.container:
                onHideKeyboard();
                break;
        }
    }

    @Override
    public void onUserNameValid() {
        tilUserName.setError(null);
    }

    @Override
    public void onUserNameInValid() {
        tilUserName.setError(getString(R.string.error_empty_field));
    }

    @Override
    public void onPasswordValid() {
        tilPassword.setError(null);
    }

    @Override
    public void onPasswordInValid() {
        tilPassword.setError(getString(R.string.error_empty_field));
    }

    @Override
    public void onLoginSuccess() {
        HomeActivity.start(this);
        finish();
    }

}
