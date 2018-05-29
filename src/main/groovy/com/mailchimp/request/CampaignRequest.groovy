package com.mailchimp.request

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class CampaignRequest {
    @JsonProperty("campaign_id")
    String id
    String type
    @JsonProperty("recipients")
    RecipientRequest recipient
    @JsonProperty("settings")
    SettingRequest setting
}
