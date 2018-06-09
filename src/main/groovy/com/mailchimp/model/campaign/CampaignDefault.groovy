package com.mailchimp.model.campaign

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CampaignDefault {
    @JsonProperty("from_name")
    String fromName
    @JsonProperty("from_email")
    String fromEmail
    String subject
    String language
}
