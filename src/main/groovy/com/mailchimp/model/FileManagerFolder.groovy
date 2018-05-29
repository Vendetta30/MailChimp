package com.mailchimp.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class FileManagerFolder {
    Integer id
    Integer fileCount
    String name
    String createdAt
    String createdBy
    List<Link> links
}
