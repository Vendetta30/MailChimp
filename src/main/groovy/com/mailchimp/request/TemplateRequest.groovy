package com.mailchimp.request

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class TemplateRequest {
    String id
    List<TemplateSectionRequest> sections
}
