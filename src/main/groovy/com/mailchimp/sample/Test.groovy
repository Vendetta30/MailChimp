package com.mailchimp.sample

import com.mailchimp.MailChimpClient
import com.mailchimp.request.CampaignRequest
import com.mailchimp.request.RecipientRequest
import com.mailchimp.request.SettingRequest

class Test {
    public static void main(String[] args) {
        RecipientRequest request = new RecipientRequest(listId: "3816c86404")
        SettingRequest settingRequest = new SettingRequest(subjectLine: "Your Purchase Receipt", replyTo: "mathur.akanksha2@gmail.com", fromName: "Akanksha mathur")

        CampaignRequest campaignRequest = new CampaignRequest(type: "regular", recipient: request, setting: settingRequest)
        MailChimpClient client = new MailChimpClient("bfbf396d994389360bb0a0b74feb1e34")
        println client.createCampaign(campaignRequest)
    }
}
