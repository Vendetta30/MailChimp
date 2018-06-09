package com.mailchimp.model.fileManager

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.mailchimp.model.Link

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class FileManagerFile {
    Integer id
    Integer folderId
    String type
    String name
    String fullSizeUrl
    String thumbnailUrl
    Integer size
    String createdAt
    String createdBy
    Integer width
    Integer height
    List<Link> links
}
