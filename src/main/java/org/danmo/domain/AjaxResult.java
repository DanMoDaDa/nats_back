package org.danmo.domain;

import org.springframework.util.ObjectUtils;

import java.util.HashMap;

public class AjaxResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    public static final String CODE_TAG = "code";
    public static final String MSG_TAG = "msg";
    public static final String DATA_TAG = "data";

    public AjaxResult() {
    }

    public AjaxResult(int code, String msg) {
        super.put("code", code);
        super.put("msg", msg);
    }

    public AjaxResult(int code, String msg, Object data) {
        super.put("code", code);
        super.put("msg", msg);
        if (!ObjectUtils.isEmpty(data)) {
            super.put("data", data);
        }

    }

    public static AjaxResult success() {
        return success("操作成功");
    }

    public static AjaxResult success(Object data) {
        return success("操作成功", data);
    }

    public static AjaxResult success(String msg) {
        return success(msg, (Object)null);
    }

    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(200, msg, data);
    }

    public static AjaxResult error() {
        return error("操作失败");
    }

    public static AjaxResult error(String msg) {
        return error(msg, (String)null);
    }

    public static AjaxResult checkException(String msg) {
        return new AjaxResult(999, msg);
    }

    public static AjaxResult bizException(String msg) {
        return new AjaxResult(998, msg);
    }

    public static AjaxResult error(String msg, String data) {
        return new AjaxResult(500, msg, data);
    }

    public static AjaxResult toAjax(int rows) {
        return rows > 0 ? success() : error();
    }

    public static AjaxResult toAjax(boolean success) {
        return success ? success() : error();
    }

    public boolean isSuccess() {
        return Integer.valueOf(200).equals(this.get("code"));
    }

    public boolean isError() {
        return !Integer.valueOf(200).equals(this.get("code"));
    }

    public String getMsg() {
        return String.valueOf(this.get("msg"));
    }
}
