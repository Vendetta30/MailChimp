package com.mailchimp.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class Stats {
    @JsonProperty("member_count")
    Integer memberCount
    @JsonProperty("unsubscribe_count")
    Integer unsubscribeCount
    @JsonProperty("cleaned_count")
    Integer cleanedCount
    @JsonProperty("member_count_since_send")
    Integer memberCountSinceSend
    @JsonProperty("unsubscribe_count_since_send")
    Integer unsubscribeCountSinceSend
    @JsonProperty("cleaned_count_since_send")
    Integer cleanedCountSinceSend
    @JsonProperty("campaign_count")
    Integer campaignCount
    @JsonProperty("campaign_last_sent")
    String campaignLastSent
    @JsonProperty("merge_field_count")
    Integer mergeFieldCount
    @JsonProperty("avg_sub_rate")
    Number avgSubRate
    @JsonProperty("avg_unsub_rate")
    Number avgUnsubRate
    @JsonProperty("target_sub_rate")
    Number targetSubRate
    @JsonProperty("open_rate")
    Number openRate
    @JsonProperty("click_rate")
    Number clickRate
    @JsonProperty("last_sub_date")
    String lastSubDate
    @JsonProperty("last_unsub_date")
    String lastUnsubDate
}
