package com.mailchimp.client

import com.mailchimp.MailChimpClient
import com.mailchimp.MailChimpURI
import com.mailchimp.model.template.Template
import com.mailchimp.request.TemplateRequest
import com.mailchimp.response.DeleteResponse
import com.mailchimp.response.ListResponse

import static java.lang.String.format

class TemplateClient {
    MailChimpClient mailChimpClient

    TemplateClient() {}

    TemplateClient(MailChimpClient mailChimpClient) {
        this.mailChimpClient = mailChimpClient
    }

    Template createTemplate(TemplateRequest request) {
        this.mailChimpClient.post(MailChimpURI.TEMPLATE, request, Template.class)
    }

    ListResponse<Template> listAllTemplate() {
        this.mailChimpClient.list(MailChimpURI.TEMPLATE, Template.class)
    }

    Template listTemplate(String templateId) {
        this.mailChimpClient.getCall(format(MailChimpURI.TEMPLATE_SINGLE, templateId), Template.class)
    }

    DeleteResponse deleteTemplate(String templateId) {
        this.mailChimpClient.delete(format(MailChimpURI.TEMPLATE_SINGLE, templateId), DeleteResponse.class)
    }

    Template updateTemplate(String templateId, TemplateRequest request) {
        this.mailChimpClient.post(format(MailChimpURI.TEMPLATE_SINGLE, templateId), request, Template.class)
    }
}
