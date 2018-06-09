package com.mailchimp.model.list

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.mailchimp.model.Link

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class AbuseReport {
    String id
    @JsonProperty("campaign_id")
    String campaignId
    @JsonProperty("list_id")
    String listId
    @JsonProperty("email_id")
    String emailId
    @JsonProperty("email_address")
    String emailAddress
    @JsonProperty("merge_fields")
    Object mergeField
    Boolean vip
    String date
    @JsonProperty("_links")
    List<Link> links
}
