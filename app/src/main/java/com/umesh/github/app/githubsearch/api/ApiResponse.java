package com.umesh.github.app.githubsearch.api;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;
/**
 * Created by Umesh.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse implements Serializable {

    public static class ApiError implements Serializable {

        private static final long serialVersionUID = 1L;
        private static final String GEN_ERROR = "Oops, something went wrong.\nPlease try again later.";
        private static final String NET_ERROR = "Oops, something went wrong while communicating with server.\nPlease check your network connection or try again later.";

        private String code;
        private String message;

        public ApiError() {
        }

        public ApiError(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static final ApiError GENERAL_ERROR = new ApiError(500 + "", GEN_ERROR);
        public static final ApiError NETWORK_ERROR = new ApiError(500 + "", NET_ERROR);

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    private boolean success;
    private ApiError errors;

    private long total_count;
    private List<Object> items;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ApiError getErrors() {
        return errors;
    }

    public ApiResponse setErrors(ApiError errors) {
        this.errors = errors;
        if (errors != null) {
            success = false;
        }
        return this;
    }

    public long getTotal_count() {
        return total_count;
    }

    public void setTotal_count(long total_count) {
        this.total_count = total_count;
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }
}
