package com.mailchimp.request

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class ArchiveRequest {
    @JsonProperty("archive_content")
    String archiveContent
    @JsonProperty("archive_type")
    String archiveType
}
