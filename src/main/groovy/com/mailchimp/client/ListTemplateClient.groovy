package com.mailchimp.client

import com.mailchimp.MailChimpClient
import com.mailchimp.MailChimpURI
import com.mailchimp.model.list.*
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

    ListActivity recentActivity(String listId) {
        this.mailChimpClient.getCall(format(MailChimpURI.LIST_ACTIVITY, listId), ListActivity.class)
    }

    ListClient clientDetails(String listId) {
        this.mailChimpClient.getCall(format(MailChimpURI.LIST_CLIENTS, listId), ListClient.class)
    }

    ListGrowthHistory growthHistoryDetails(String listId, String month) {
        if (!month) {
            this.mailChimpClient.getCall(format(MailChimpURI.LIST_GROWTH_HISTORY, listId), ListGrowthHistory.class)
        } else {
            this.mailChimpClient.getCall(format(MailChimpURI.LIST_GROWTH_HISTORY_MONTH, listId, month), ListGrowthHistory.class)
        }
    }

    ListAbuseReport abuseReports(String listId, String month) {
        if (!month) {
            this.mailChimpClient.getCall(format(MailChimpURI.LIST_ABUSE_REPORTS, listId), ListAbuseReport.class)
        } else {
            this.mailChimpClient.getCall(format(MailChimpURI.LIST_ABUSE_REPORTS_SPECIFIC, listId, month), ListAbuseReport.class)
        }
    }
}
