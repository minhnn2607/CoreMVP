package vn.nms.core.utils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Min on 22/03/2018.
 */

public class RxUtils {
    public static void dispose(CompositeDisposable compositeDisposable){
        if(compositeDisposable!=null && !compositeDisposable.isDisposed()){
            compositeDisposable.clear();
            compositeDisposable.dispose();
        }
    }

    public static void dispose(Disposable disposable){
        if(disposable!=null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
