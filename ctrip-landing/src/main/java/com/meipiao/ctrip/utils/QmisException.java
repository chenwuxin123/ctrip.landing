package com.meipiao.ctrip.utils;

public class QmisException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String module;

    private String code;

    private String description;

    /**
     * 构造方法
     */
    @Deprecated
    public QmisException() {
    }

    /**
     * 构造方法
     *
     * @param module
     * @param code
     * @param message
     */
    public QmisException(String module, String code, String message) {
        super(message);
        this.module = module;
        this.code = code;
    }

    /**
     * 构造方法
     *
     * @param module
     * @param code
     * @param message
     * @param description
     */
    public QmisException(String module, String code, String message, String description) {
        super(message);
        this.module = module;
        this.code = code;
        this.description = description;
    }

    /**
     * 异常模块
     *
     * @return
     */
    public String getModule() {
        return module;
    }

    /**
     * 错误码
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * 错误描述
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(": [").append(module).append("] - ");
        sb.append(code).append(" - ").append(getMessage());

        if (getDescription() != null) {
            sb.append(" - ");
            sb.append(getDescription());
        }

        return sb.toString();
    }
}
