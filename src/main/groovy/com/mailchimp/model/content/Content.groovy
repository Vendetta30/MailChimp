package com.mailchimp.model.content

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.mailchimp.model.Link

@JsonInclude(JsonInclude.Include.NON_NULL)
class Content {
    @JsonProperty("variate_contents")
    List<VariateContent> variateContents
    @JsonProperty("plain_text")
    String plainText
    String html
    @JsonProperty("archive_html")
    String archiveHtml
    @JsonProperty("_links")
    List<Link> links
}
