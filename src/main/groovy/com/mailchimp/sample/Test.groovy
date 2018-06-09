package com.mailchimp.sample

import com.mailchimp.MailChimpClient
import com.mailchimp.client.CampaignClient
import com.mailchimp.request.CampaignRequest
import com.mailchimp.request.RecipientRequest
import com.mailchimp.request.SettingRequest

class Test {
    public static void main(String[] args) {
        String url = "https://us18.api.mailchimp.com"
        String key = "bfbf396d994389360bb0a0b74feb1e34"

        RecipientRequest recipientRequest = new RecipientRequest(listId: "3816c86404")
        SettingRequest settingRequest = new SettingRequest(subjectLine: "Your Purchase Receipt", replyTo: "mathur.akanksha2@gmail.com", fromName: "Akanksha mathur")
        CampaignRequest campaignRequest = new CampaignRequest(type: "regular", recipient: recipientRequest, setting: settingRequest)

//        ContentRequest recipientRequest = new ContentRequest(html: "<h1>DATATDTATDTA</h1>")

        MailChimpClient client = new MailChimpClient(key, url)
        CampaignClient campaignClient = new CampaignClient(client)
        println campaignClient.sendCampaign(campaignRequest.id)
    }
}