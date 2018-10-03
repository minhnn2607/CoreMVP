package vn.nms.core.base;

import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

public interface MvpView {
    void showMessageDialog(Throwable throwable);

    void showMessageDialog(String message);

    void showMessageDialog(String title, String message, String cancel,
                           @Nullable DialogInterface.OnClickListener cancelListener);

    void showMessageDialog(String title, String message,
                           String confirm, String cancel,
                           @Nullable DialogInterface.OnClickListener confirmListener,
                           @Nullable DialogInterface.OnClickListener cancelListener);

    void hideMessageDialog();

    void showLoading(@StringRes int resId);

    void hideLoading();

    void onHideKeyboard();

    void onShowKeyboard();

    void showLoadMore();

    void showToast(String msg);

}
