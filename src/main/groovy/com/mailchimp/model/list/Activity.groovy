package com.mailchimp.model.list

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.mailchimp.model.Link

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class Activity {
    String day
    @JsonProperty("emails_sent")
    Integer emailsSent
    @JsonProperty("unique_opens")
    Integer uniqueOpens
    @JsonProperty("recipient_clicks")
    Integer recipientClicks
    @JsonProperty("hard_bounce")
    Integer hardBounce
    @JsonProperty("soft_bounce")
    Integer softBounce
    Integer subs
    Integer unsubs
    @JsonProperty("other_adds")
    Integer otherAdds
    @JsonProperty("other_removes")
    Integer otherRemoves
    @JsonProperty("_links")
    List<Link> links
}
