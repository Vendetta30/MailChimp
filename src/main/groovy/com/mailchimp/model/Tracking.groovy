package com.mailchimp.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Tracking {
    @JsonProperty("opens")
    Boolean opens
    @JsonProperty("html_clicks")
    Boolean htmlClicks
    @JsonProperty("text_clicks")
    Boolean textClicks
    @JsonProperty("goal_tracking")
    Boolean goalTracking
    Boolean ecomm360
    @JsonProperty("google_analytics")
    String googleAnalytics
    String clicktale
}
