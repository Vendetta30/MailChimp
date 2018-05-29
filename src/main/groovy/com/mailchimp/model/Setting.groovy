package com.mailchimp.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class Setting {
    @JsonProperty("subject_line")
    String subjectLine
    @JsonProperty("preview_text")
    String previewText
    String title
    @JsonProperty("from_name")
    String fromName
    @JsonProperty("reply_to")
    String replyTo
    @JsonProperty("use_conversation")
    Boolean useConversation
    @JsonProperty("to_name")
    String toName
    @JsonProperty("folder_id")
    String folderId
    Boolean authenticate
    @JsonProperty("auto_footer")
    Boolean autoFooter
    @JsonProperty("inline_css")
    Boolean inlineCSS
    @JsonProperty("auto_tweet")
    Boolean autoTweet
    @JsonProperty("auto_fb_post")
    List<String> autoFbPosts
    @JsonProperty("fb_comments")
    Boolean fbComments
    @JsonProperty("timewarp")
    Boolean timeWrap
    @JsonProperty("template_id")
    Integer templateId
    @JsonProperty("drag_and_drop")
    Boolean dragAndDrop
}
