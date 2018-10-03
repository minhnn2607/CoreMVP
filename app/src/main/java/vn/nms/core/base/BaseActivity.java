package vn.nms.core.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import vn.nms.core.R;
import vn.nms.core.ui.dialog.LoadingDialog;
import vn.nms.core.ui.widget.HeaderView;
import vn.nms.core.utils.DialogUtils;
import vn.nms.core.utils.ErrorHandler;
import vn.nms.core.utils.KeyboardUtils;

import java.lang.reflect.Field;

import butterknife.Unbinder;
import icepick.Icepick;

@SuppressLint("Registered")
public class BaseActivity<P extends BasePresenter>
        extends AppCompatActivity implements MvpView, HeaderView.HeaderListener {
    private AlertDialog mDialog = null;
    private LoadingDialog loadingDialog = null;
    protected Unbinder unbinder;
    protected P mPresenter;


    public void initHeader() {
        HeaderView headerView = findViewById(R.id.headerView);
        if (headerView != null)
            headerView.setListener(this);
    }

    protected void setTitle(String title) {
        HeaderView headerView = findViewById(R.id.headerView);
        if (headerView != null)
            headerView.setTitle(title);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            Icepick.restoreInstanceState(this, savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onDestroy() {
        fixInputMethod(this);
        hideDialog(mDialog);
        hideDialog(loadingDialog);
        mDialog = null;
        loadingDialog = null;
        if (unbinder != null) unbinder.unbind();
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showMessageDialog(Throwable throwable) {
        hideDialog(mDialog);
        mDialog = DialogUtils.createMessageDialog(this, null,
                ErrorHandler.getErrorMessage(throwable), null, null);
        if (mDialog.getWindow() != null)
            mDialog.getWindow().getAttributes().windowAnimations = R.style.MessageDialogAnimation;
        mDialog.setOnShowListener(dialog -> {
            Button negativeButton = ((AlertDialog) dialog)
                    .getButton(AlertDialog.BUTTON_NEGATIVE);
            negativeButton.setTextColor(getResources().getColor(R.color.colorPrimary));
        });
        mDialog.show();
    }

    @Override
    public void showMessageDialog(String message) {
        hideDialog(mDialog);
        if (message == null) message = "";
        mDialog = DialogUtils.createMessageDialog(this, null, message, null, null);
        if (mDialog.getWindow() != null)
            mDialog.getWindow().getAttributes().windowAnimations = R.style.MessageDialogAnimation;
        mDialog.setOnShowListener(dialog -> {
            Button negativeButton = ((AlertDialog) dialog)
                    .getButton(AlertDialog.BUTTON_NEGATIVE);
            negativeButton.setTextColor(getResources().getColor(R.color.colorPrimary));
        });
        mDialog.show();

    }

    @Override
    public void showMessageDialog(String title, String message, String cancel,
                                  @Nullable DialogInterface.OnClickListener cancelListener) {
        hideDialog(mDialog);
        if (message == null) message = "";
        mDialog = DialogUtils.createCustomMessageDialog(this, title, message, cancel, cancelListener);
        if (mDialog.getWindow() != null)
            mDialog.getWindow().getAttributes().windowAnimations = R.style.MessageDialogAnimation;
        mDialog.setOnShowListener(dialog -> {
            Button negativeButton = ((AlertDialog) dialog)
                    .getButton(AlertDialog.BUTTON_NEGATIVE);
            negativeButton.setTextColor(getResources().getColor(R.color.colorPrimary));
        });
        mDialog.show();
    }

    @Override
    public void showMessageDialog(String title, String message,
                                  String confirm, String cancel,
                                  @Nullable DialogInterface.OnClickListener confirmListener,
                                  @Nullable DialogInterface.OnClickListener cancelListener) {
        hideDialog(mDialog);
        mDialog = DialogUtils.createMessageDialog(this, title, message, confirm, cancel,
                confirmListener, cancelListener);
        if (mDialog.getWindow() != null)
            mDialog.getWindow().getAttributes().windowAnimations = R.style.MessageDialogAnimation;
        mDialog.setOnShowListener(dialog -> {
            Button positiveButton = ((AlertDialog) dialog)
                    .getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setTextColor(getResources().getColor(R.color.colorPrimary));

            Button negativeButton = ((AlertDialog) dialog)
                    .getButton(AlertDialog.BUTTON_NEGATIVE);
            negativeButton.setTextColor(getResources().getColor(R.color.gray));
        });
        mDialog.show();
    }

    @Override
    public void hideMessageDialog() {
        hideDialog(mDialog);
        mDialog =null;
    }

    @Override
    public void showLoading(@StringRes int resId) {
        hideDialog(loadingDialog);
        if (resId != 0) {
            loadingDialog = new LoadingDialog(this, resId);
        } else {
            loadingDialog = new LoadingDialog(this);
        }
        loadingDialog.show();
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setCancelable(true);
    }

    @Override
    public void hideLoading() {
        hideDialog(loadingDialog);
    }

    @Override
    public void onHideKeyboard() {
        KeyboardUtils.hideKeyboard(this);
    }

    @Override
    public void onShowKeyboard() {
        KeyboardUtils.showKeyboard(this);
    }

    private void hideDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadMore() {
    }

    @Override
    public void onHeaderLeftImagePressed() {
        finish();
    }

    @Override
    public void onHeaderLeftTextPressed() {
        finish();
    }

    @Override
    public void onHeaderRightImagePressed() {

    }

    @Override
    public void onHeaderRightTextPressed() {

    }

    private static void fixInputMethod(Context context) {
        if (context == null) {
            return;
        }
        InputMethodManager inputMethodManager = null;
        try {
            inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (inputMethodManager == null) {
            return;
        }
        Field[] declaredFields = inputMethodManager.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            try {
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                Object obj = declaredField.get(inputMethodManager);
                if (!(obj instanceof View)) {
                    continue;
                }
                View view = (View) obj;
                if (view.getContext() == context) {
                    declaredField.set(inputMethodManager, null);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
