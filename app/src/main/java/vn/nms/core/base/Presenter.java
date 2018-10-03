package vn.nms.core.base;

interface Presenter<T extends MvpView> {
    void attachView(T mvpView);

    void detachView();
}