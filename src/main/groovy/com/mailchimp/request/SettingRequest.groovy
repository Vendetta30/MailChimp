package com.mailchimp.request

import com.fasterxml.jackson.annotation.JsonProperty

class SettingRequest {
    @JsonProperty("subject_line")
    String subjectLine
    @JsonProperty("from_name")
    String fromName
    @JsonProperty("reply_to")
    String replyTo
}
