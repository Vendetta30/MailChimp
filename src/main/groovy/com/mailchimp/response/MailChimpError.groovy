package com.mailchimp.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class MailChimpError {
    String field
    String message

    String toString() {
        return "${this.field}:${this.message}"
    }
}
