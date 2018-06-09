package com.mailchimp.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.mailchimp.model.campaign.CampaignDefault

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class ListTemplate {
    String id
    @JsonProperty("web_id")
    Integer webId
    String name
    Contact contact
    @JsonProperty("permission_reminder")
    String permissionReminder
    @JsonProperty("use_archive_bar")
    Boolean useArchiveBar
    @JsonProperty("campaign_defaults")
    CampaignDefault campaignDefault
    @JsonProperty("notify_on_subscribe")
    String notifyOnSubscribe
    @JsonProperty("notify_on_unsubscribe")
    String notifyOnUnsubscribe
    @JsonProperty("date_created")
    String dateCreated
    @JsonProperty("list_rating")
    Integer listRating
    @JsonProperty("email_type_option")
    Boolean emailTypeOption
    @JsonProperty("subscribe_url_short")
    String subscribeUrlShort
    @JsonProperty("subscribe_url_long")
    String subscribeUrlLong
    @JsonProperty("beamer_address")
    String beamerAddress
    String visibility
    List modules
    @JsonProperty("_links")
    List<Link> links
}
