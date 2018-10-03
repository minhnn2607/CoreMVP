package vn.nms.core.ui.account.sign_in;

import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Flowable;
import timber.log.Timber;
import vn.nms.core.R;
import vn.nms.core.base.BaseApp;
import vn.nms.core.base.BasePresenter;
import vn.nms.core.data.entity.User;
import vn.nms.core.services.local.AppDataBase;
import vn.nms.core.services.local.UserManager;
import vn.nms.core.services.network.ApiInterface;
import vn.nms.core.services.network.ApiManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class SignInPresenter extends BasePresenter<SignInView> {

    private final ApiInterface mApi;

    SignInPresenter() {
        mApi = ApiManager.getInstance().getApiInterface();
    }

    public void addTextWatcher(final EditText etUserName, final EditText etPassword) {
        disposableList.add(RxTextView.afterTextChangeEvents(etUserName).subscribe(
                text -> mMvpView.onUserNameValid()));
        disposableList.add(RxTextView.afterTextChangeEvents(etPassword).subscribe(
                text -> mMvpView.onPasswordValid()));
        etPassword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String userName = etUserName.getText().toString().trim();
                String password = etPassword.getText().toString();
                doSignIn(userName, password);
            }
            return false;
        });
    }

    public void doSignIn(String userName, String password) {
        if (validate(userName, password)) {
            mMvpView.onHideKeyboard();
            mMvpView.showLoading(R.string.processing_sign_in);
            disposableList.add(mApi.login(userName, password,
                    "password")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if (isViewAttached()) {
                            mMvpView.hideLoading();
                            if (response.getCode() == 200) {
                                saveUserInfo(response.getData());
                                UserManager.getInstance().setUserInfo(response.getData());
                                mMvpView.onLoginSuccess();
                            } else {
                                mMvpView.showMessageDialog(response.getMessage());
                            }
                        }
                    }, throwable -> {
                        if (isViewAttached()) {
                            mMvpView.showMessageDialog(throwable);
                            mMvpView.hideLoading();
                        }
                    }));
        }
    }

    private void saveUserInfo(User user) {
        getDisposable().add(Flowable.fromCallable(() ->
                AppDataBase.getAppDatabase(BaseApp.getAppContext())
                        .userDao().insert(user))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> Timber.d("DB insert:%s", result),
                        error-> Timber.d("DB insert error:%s", error.getMessage())));
    }

    private boolean validate(String userName, String password) {
        boolean result = true;
        if (TextUtils.isEmpty(userName)) {
            mMvpView.onUserNameInValid();
            result = false;
        }
        if (TextUtils.isEmpty(password)) {
            mMvpView.onPasswordInValid();
            result = false;
        }
        return result;
    }

}
