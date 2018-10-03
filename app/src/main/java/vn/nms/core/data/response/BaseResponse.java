package vn.nms.core.data.response;

import io.reactivex.annotations.Nullable;

public class BaseResponse<T> {
    private int code;
    private String message = "";
    private @Nullable  T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
