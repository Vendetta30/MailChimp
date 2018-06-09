package com.mailchimp.client

import com.mailchimp.MailChimpClient
import com.mailchimp.MailChimpURI
import com.mailchimp.model.Campaign
import com.mailchimp.model.content.Content
import com.mailchimp.request.CampaignRequest
import com.mailchimp.request.ContentRequest
import com.mailchimp.response.DeleteResponse
import com.mailchimp.response.ListResponse

import static java.lang.String.format

class CampaignClient {
    MailChimpClient mailChimpClient

    CampaignClient() {}

    CampaignClient(MailChimpClient mailChimpClient) {
        this.mailChimpClient = mailChimpClient
    }

    Campaign createCampaign(CampaignRequest request) {
        this.mailChimpClient.post(MailChimpURI.CAMPAIGN, request, Campaign.class)
    }

    ListResponse<Campaign> listAllCampaign() {
        this.mailChimpClient.list(MailChimpURI.CAMPAIGN, Campaign.class)
    }

    Campaign listCampaign(String campaignId) {
        this.mailChimpClient.getCall(format(MailChimpURI.SINGLE_CAMPAIGN, campaignId), Campaign.class)
    }

    DeleteResponse deleteCampaign(String campaignId) {
        this.mailChimpClient.delete(format(MailChimpURI.SINGLE_CAMPAIGN, campaignId), DeleteResponse.class)
    }

    Campaign updateCampaign(String campaignId, CampaignRequest request) {
        this.mailChimpClient.post(format(MailChimpURI.SINGLE_CAMPAIGN, campaignId), request, Campaign.class)
    }

    Content updateContentOfCampaign(String campaignId, ContentRequest request) {
        this.mailChimpClient.put(format(MailChimpURI.CAMPAIGN_CONTENT, campaignId), request, Content.class)
    }

    void cancelSendCampaign(String campaignId) {
        this.mailChimpClient.postAction(format(MailChimpURI.CANCEL_CAMPAIGN, campaignId))
    }

    void pauseCampaign(String campaignId) {
        this.mailChimpClient.postAction(format(MailChimpURI.PAUSE_CAMPAIGN, campaignId))
    }

    void replicateCampaign(String campaignId) {
        this.mailChimpClient.postAction(format(MailChimpURI.REPLICATE_CAMPAIGN, campaignId))
    }

    void resumeCampaign(String campaignId) {
        this.mailChimpClient.postAction(format(MailChimpURI.RESUME_CAMPAIGN, campaignId))
    }

    void scheduleCampaign(String campaignId) {
        this.mailChimpClient.postAction(format(MailChimpURI.SCHEDULE_CAMPAIGN, campaignId))
    }

    void sendCampaign(String campaignId) {
        this.mailChimpClient.postAction(format(MailChimpURI.SEND_CAMPAIGN, campaignId))
    }

    void unScheduleCampaign(String campaignId) {
        this.mailChimpClient.postAction(format(MailChimpURI.UNSCHEDULE_CAMPAIGN, campaignId))
    }
}
