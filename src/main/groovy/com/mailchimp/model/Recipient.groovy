package com.mailchimp.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Recipient {
    @JsonProperty("list_id")
    String listId
    @JsonProperty("list_is_active")
    Boolean listIsActive
    @JsonProperty("list_name")
    String listName
    @JsonProperty("segment_text")
    String segmentText
    @JsonProperty("recipient_count")
    Integer recipientCount
    @JsonProperty("segment_opts")
    SegmentOption segmentsOpts
}
