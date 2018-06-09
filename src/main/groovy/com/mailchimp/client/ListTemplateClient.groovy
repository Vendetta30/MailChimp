package com.mailchimp.client

import com.mailchimp.MailChimpClient
import com.mailchimp.MailChimpURI
import com.mailchimp.model.ListTemplate
import com.mailchimp.response.DeleteResponse
import com.mailchimp.response.ListResponse

import static java.lang.String.format

class ListTemplateClient {
    MailChimpClient mailChimpClient

    ListTemplateClient() {}

    ListTemplateClient(MailChimpClient mailChimpClient) {
        this.mailChimpClient = mailChimpClient
    }

    ListTemplate createList(ListTemplate request) {
        this.mailChimpClient.post(MailChimpURI.LIST, request, ListTemplate.class)
    }

    ListResponse<ListTemplate> listAllList() {
        this.mailChimpClient.list(MailChimpURI.LIST, ListTemplate.class)
    }

    ListTemplate listList(String listId) {
        this.mailChimpClient.getCall(format(MailChimpURI.LIST_SPECIFIC, listId), ListTemplate.class)
    }

    DeleteResponse deleteList(String listId) {
        this.mailChimpClient.delete(format(MailChimpURI.LIST_SPECIFIC, listId), DeleteResponse.class)
    }

    ListTemplate updateCampaign(String listId, ListTemplate request) {
        this.mailChimpClient.post(format(MailChimpURI.LIST_SPECIFIC, listId), request, ListTemplate.class)
    }
}
