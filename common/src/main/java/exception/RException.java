package exception;

public class RException extends RuntimeException{
    private Integer code;//错误码

    private String msg;//提示信息

    public RException(String message) {
        this.msg = message;
        code = 500;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
