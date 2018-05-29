package com.mailchimp.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Campaign {
    String id
    @JsonProperty("web_id")
    String webId
    String type
    @JsonProperty("create_time")
    String createTime
    @JsonProperty("archive_url")
    String archiveUrl
    @JsonProperty("long_archive_url")
    String longArchiveUrl
    String status
    @JsonProperty("emails_sent")
    String emailsSent
    @JsonProperty("send_time")
    String sendTime
    @JsonProperty("content_type")
    String contentType
    @JsonProperty("needs_block_refresh")
    Boolean needsBlockRefresh
    @JsonProperty("recipients")
    List<Recipient> recipients
    @JsonProperty("settings")
    List<Setting> settings
    @JsonProperty("delivery_status")
    DeliveryStatus deliveryStatus
    @JsonProperty("_links")
    List<Link> links
}
