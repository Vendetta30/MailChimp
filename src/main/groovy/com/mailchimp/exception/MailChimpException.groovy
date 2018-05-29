package com.mailchimp.exception

import com.mailchimp.response.ErrorResponse

class MailChimpException extends RuntimeException {
    static final long serialVersionUID = 1L

    String message
    Integer status
    String type
    String title
    String detail

    public MailChimpException(Integer status, String type, String title, String detail, String message) {
        super(message)
        this.status = status
        this.type = type
        this.title = title
        this.detail = detail
    }

    public MailChimpException(ErrorResponse errorResponse) {
        this(errorResponse.status, errorResponse.type, errorResponse.title, errorResponse.detail, errorResponse.errors.join(","))
    }
}
