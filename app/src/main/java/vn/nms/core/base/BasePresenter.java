package vn.nms.core.base;

import vn.nms.core.utils.RxUtils;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends MvpView>
        implements Presenter<V> {
    protected V mMvpView;
    protected CompositeDisposable disposableList;

    @Override
    public void attachView(V mvpView) {
        mMvpView = mvpView;
        disposableList = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        mMvpView = null;
        RxUtils.dispose(disposableList);
    }

    protected boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public boolean checkViewAttached() {
        return isViewAttached();
    }

    protected CompositeDisposable getDisposable() {
        return disposableList;
    }
}