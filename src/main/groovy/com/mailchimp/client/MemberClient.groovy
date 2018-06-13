package com.mailchimp.client

import com.mailchimp.MailChimpClient
import com.mailchimp.MailChimpURI
import com.mailchimp.model.member.MemberResponse
import com.mailchimp.model.template.Template
import com.mailchimp.request.TemplateRequest

import static java.lang.String.format

class MemberClient {
    MailChimpClient mailChimpClient

    MemberClient() {}

    MemberClient(MailChimpClient mailChimpClient) {
        this.mailChimpClient = mailChimpClient
    }

    Template createTemplate(TemplateRequest request) {
        this.mailChimpClient.post(MailChimpURI.TEMPLATE, request, Template.class)
    }

    MemberResponse listMember() {
        this.mailChimpClient.getCall(format(MailChimpURI.SEARCH_MEMBERS), MemberResponse.class)
    }
}
