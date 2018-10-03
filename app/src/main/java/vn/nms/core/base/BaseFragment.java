package vn.nms.core.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import vn.nms.core.R;
import vn.nms.core.ui.widget.HeaderView;

import butterknife.BindView;
import butterknife.Unbinder;

public class BaseFragment<P extends BasePresenter> extends Fragment implements MvpView,
        HeaderView.HeaderListener {

    @Nullable
    protected P mPresenter;
    protected Unbinder unbinder;

    @Nullable
    @BindView(R.id.headerView)
    public HeaderView headerView;

    protected void setTitle(String title) {
        if (headerView != null)
            headerView.setTitle(title);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (headerView != null)
            headerView.setListener(this);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null)
            mPresenter.detachView();
        if (unbinder != null) unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void showMessageDialog(Throwable throwable) {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).showMessageDialog(throwable);
    }

    @Override
    public void showMessageDialog(String message) {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).showMessageDialog(message);
    }

    @Override
    public void showMessageDialog(String title, String message, String confirm,
                                  @Nullable DialogInterface.OnClickListener confirmListener) {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).showMessageDialog(title, message, confirm, confirmListener);
    }

    @Override
    public void showMessageDialog(String title, String message, String confirm, String cancel,
                                  @Nullable DialogInterface.OnClickListener confirmListener,
                                  @Nullable DialogInterface.OnClickListener cancelListener) {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).showMessageDialog(title, message, confirm, cancel,
                    confirmListener, cancelListener);
    }

    @Override
    public void hideMessageDialog() {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).hideMessageDialog();
    }

    @Override
    public void showLoading(int resId) {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).showLoading(resId);
    }

    @Override
    public void hideLoading() {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).hideLoading();
    }

    @Override
    public void onHideKeyboard() {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).onHideKeyboard();
    }

    @Override
    public void onShowKeyboard() {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).onShowKeyboard();
    }

    @Override
    public void showLoadMore() {

    }

    @Override
    public void showToast(String message) {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).showToast(message);
    }

    public void addFragment(Fragment fragment, int rootId) {
        getChildFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .add(rootId, fragment).addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onHeaderLeftImagePressed() {
        if (getActivity() != null)
            getActivity().onBackPressed();
    }

    @Override
    public void onHeaderLeftTextPressed() {

    }

    @Override
    public void onHeaderRightImagePressed() {

    }

    @Override
    public void onHeaderRightTextPressed() {

    }
}