package com.mailchimp.exception

class ConnectionException extends RuntimeException {

    private static final long serialVersionUID = 1L

    public ConnectionException(Throwable cause) {
        super(cause)
    }
}
