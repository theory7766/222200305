package com.west2.DAO;

public class MyException extends Throwable {
    // 异常提示语句
    private String warning;

    public MyException(String warning) {
        this.warning = warning;
    }

    public String toString() {
        return warning;
    }
}
