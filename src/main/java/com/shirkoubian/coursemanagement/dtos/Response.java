package com.shirkoubian.coursemanagement.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response <T>{
    private String message;
    private int statusCode;
    private LocalDateTime timeStamp;
    private T data;
    private int pageSize;
    private int pageNumber;

    // Private constructor
    private Response(Builder<T> builder) {
        this.message = builder.message;
        this.statusCode = builder.statusCode;
        this.timeStamp = builder.timeStamp != null ? builder.timeStamp : LocalDateTime.now();
        this.data = builder.data;
        this.pageSize = builder.pageSize;
        this.pageNumber = builder.pageNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {
        private String message;
        private int statusCode;
        private LocalDateTime timeStamp;
        private T data;
        private int pageSize;
        private int pageNumber;

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder<T> timeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder<T> pageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public Response<T> build() {
            return new Response<>(this);
        }
    }

}
