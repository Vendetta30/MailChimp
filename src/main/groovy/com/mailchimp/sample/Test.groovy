package com.mailchimp.sample

import com.mailchimp.MailChimpClient
import com.mailchimp.request.ContentRequest

class Test {
    public static void main(String[] args) {
        String url = "https://us18.api.mailchimp.com"
        String key = "bfbf396d994389360bb0a0b74feb1e34"
        /*RecipientRequest request = new RecipientRequest(listId: "3816c86404")
        SettingRequest settingRequest = new SettingRequest(subjectLine: "Your Purchase Receipt", replyTo: "mathur.akanksha2@gmail.com", fromName: "Akanksha mathur")
//f738a8099c
        CampaignRequest campaignRequest = new CampaignRequest(type: "regular", recipient: request, setting: settingRequest)*/

        ContentRequest request = new ContentRequest(html: "<h1>DATATDTATDTA</h1>")

        MailChimpClient client = new MailChimpClient(key, url)
        println client.sendCampaign("f738a8099c")
    }
}