package com.mailchimp.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class AbuseReportRequest {
    @JsonProperty("fields")
    List<String> fields
    @JsonProperty("exclude_fields")
    List<String> excludeFields
    Integer count
    Integer offset
}
