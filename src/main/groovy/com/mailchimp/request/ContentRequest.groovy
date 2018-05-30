package com.mailchimp.request

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class ContentRequest {
    @JsonProperty("plain_text")
    String plainText
    String html
    String url
    TemplateRequest template
}
