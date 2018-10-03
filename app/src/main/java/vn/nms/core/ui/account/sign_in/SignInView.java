package vn.nms.core.ui.account.sign_in;

import vn.nms.core.base.MvpView;

interface SignInView extends MvpView {
    void onUserNameValid();

    void onUserNameInValid();

    void onPasswordValid();

    void onPasswordInValid();

    void onLoginSuccess();
}
