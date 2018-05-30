package com.mailchimp.model.content

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class VariateContent {
    @JsonProperty("content_label")
    String contentLabel
    @JsonProperty("plain_text")
    String plainText
    String html
}
