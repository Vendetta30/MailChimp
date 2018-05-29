package com.mailchimp.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class Template {
    Integer id
    String type
    String name
    Boolean dragAndDrop
    Boolean responsive
    String category
    String dateCreated
    String createdBy
    Boolean active
    String folderId
    String thumbnail
    String shareUrl
    List<Link> links
}
