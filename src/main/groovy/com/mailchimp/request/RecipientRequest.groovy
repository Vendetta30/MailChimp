package com.mailchimp.request

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class RecipientRequest {
    @JsonProperty("list_id")
    String listId
}
