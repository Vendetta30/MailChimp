package com.mailchimp.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrorResponse {
    String type
    String title
    Integer status
    String detail
    String instance

    List<MailChimpError> errors
}
