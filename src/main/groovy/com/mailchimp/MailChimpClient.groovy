package com.mailchimp

import com.mailchimp.connection.Connection
import com.mailchimp.connection.HttpClientConnection
import com.mailchimp.connection.Response
import com.mailchimp.exception.MailChimpException
import com.mailchimp.model.Campaign
import com.mailchimp.request.CampaignRequest
import com.mailchimp.response.DeleteResponse
import com.mailchimp.response.ErrorResponse
import com.mailchimp.response.ListResponse
import com.mailchimp.util.ObjectSerializer

import static java.lang.String.format

class MailChimpClient {
    static String VERSION = "3.0"
    static String SERVER_URL = "https://us18.api.mailchimp.com"

    static String endpoint = "$SERVER_URL/$VERSION"

    static String CAMPAIGN_FOLDER = "/campaign-folders"
    static String CAMPAIGN = "/campaigns"
    static String SINGLE_CAMPAIGN = "/campaigns/%s/"
    static String CANCEL_CAMPAIGN = "/campaigns/%s/actions/cancel-send"
    static String PAUSE_CAMPAIGN = "/campaigns/%s/actions/pause"
    static String REPLICATE_CAMPAIGN = "/campaigns/%s/actions/replicate"
    static String RESUME_CAMPAIGN = "/campaigns/%s/actions/resume"
    static String SCHEDULE_CAMPAIGN = "/campaigns/%s/actions/schedule"
    static String UNSCHEDULE_CAMPAIGN = "/campaigns/%s/actions/unschedule"
    static String SEND_CAMPAIGN = "/campaigns/%s/actions/send"
    static String CAMPAIGN_CONTENT = "/campaigns/%s/content"
    static String CAMPAIGN_FEEDBACK = "/campaigns/%s/feedback"
    static String CAMPAIGN_FEEDBACK_MESSAGE = "/campaigns/%s/feedback/%s"
    static String CAMPAIGN_SEND_CHECKLIST = "/campaigns/%s/send-checklist"
    static String SEARCH_CAMPAIGN = "/search-campaigns"

    static String FILE_MANAGER_FILES = "/file-manager/files"
    static String FILE_MANAGER_SPECIFIC = "/file-manager/files/%s"

    static String LIST = "/lists"
    static String LIST_SPECIFIC = "/lists/%s"
    static String LIST_ACTIVITY = "/lists/%s/activity"
    static String LIST_CLIENTS = "/lists/%s/clients"
    static String LIST_LOCATIONS = "/lists/%s/locations"
    static String LIST_MEMBERS = "/lists/%s/members"
    static String LIST_SEGMENTS = "/lists/%s/segments"
    static String LIST_WEBHOOKS = "/lists/%s/webhooks"
    static String SEARCH_MEMBERS = "/search-members"

    static String TEMPLATE_FOLDER = "/template-folders"
    static String TEMPLATE_FOLDER_SINGLE = "/template-folders/%s"

    static String TEMPLATE = "/templates"
    static String TEMPLATE_SINGLE = "/templates/%s"
    static String DEFAULT_TEMPLATE_SINGLE = "/templates/%s/default-content"

    Connection connection
    String apiKey

    final ObjectSerializer objectSerializer = ObjectSerializer.INSTANCE

    MailChimpClient() {
        this(null)
    }

    MailChimpClient(String apiKey) {
        this(apiKey, new HttpClientConnection())
    }

    MailChimpClient(String apiKey, Connection connection) {
        this.apiKey = apiKey
        this.connection = connection
    }

    //Campaign API
    Campaign createCampaign(CampaignRequest request) {
        return post(CAMPAIGN, request, Campaign.class)
    }

    ListResponse<Campaign> listAllCampaign() {
        return list(CAMPAIGN, Campaign.class)
    }

    Campaign listCampaign(String campaignId) {
        return getCall(format(SINGLE_CAMPAIGN, campaignId), Campaign.class)
    }

    DeleteResponse deleteCampaign(String campaignId) {
        return delete(format(SINGLE_CAMPAIGN, campaignId), DeleteResponse.class)
    }

    Campaign updateCampaign(String campaignId, CampaignRequest request) {
        return post(format(SINGLE_CAMPAIGN, campaignId), request, Campaign.class)
    }

    void cancelSendCampaign(String campaignId, CampaignRequest request) {
        post(format(CANCEL_CAMPAIGN, campaignId), request, Campaign.class)
    }

    void pauseCampaign(String campaignId, CampaignRequest request) {
        post(format(PAUSE_CAMPAIGN, campaignId), request, Campaign.class)
    }

    void replicateCampaign(String campaignId, CampaignRequest request) {
        post(format(REPLICATE_CAMPAIGN, campaignId), request, Campaign.class)
    }

    void resumeCampaign(String campaignId, CampaignRequest request) {
        post(format(RESUME_CAMPAIGN, campaignId), request, Campaign.class)
    }

    void scheduleCampaign(String campaignId, CampaignRequest request) {
        post(format(SCHEDULE_CAMPAIGN, campaignId), request, Campaign.class)
    }

    void sendCampaign(String campaignId, CampaignRequest request) {
        post(format(SEND_CAMPAIGN, campaignId), request, Campaign.class)
    }

    void unScheduleCampaign(String campaignId, CampaignRequest request) {
        post(format(UNSCHEDULE_CAMPAIGN, campaignId), request, Campaign.class)
    }

    protected <T> T getCall(String path, Class<T> responseClass) {
        return getCall(endpoint, path, responseClass)
    }

    protected <T> T getCall(String endpoint, String path, Class<T> responseClass) {
        Response response = connection.get(endpoint + path, buildHeaders())
        ensureSuccess(response)
        return objectSerializer.deserialize(response.body, responseClass)
    }

    protected <T> T post(String path, Object request, Class<T> responseClass) {
        String requestBody = objectSerializer.serialize(request)
        Response response = connection.post(endpoint + path, requestBody, buildHeaders())
        ensureSuccess(response)
        return objectSerializer.deserialize(response.body, responseClass)
    }

    protected <T> ListResponse<T> list(String path, Class<T> elementClass) {
        return list(endpoint, path, null, elementClass)
    }

    protected <T> ListResponse<T> list(String path, Object request, Class<T> elementClass) {
        return list(endpoint, path, request, elementClass)
    }

    protected <T> ListResponse<T> list(String endpoint, String path, Object request, Class<T> elementClass) {
        String url = buildQueryString(endpoint + path, request)
        Response response = connection.get(url, buildHeaders())
        ensureSuccess(response)
        return objectSerializer.deserializeList(response.body, elementClass)
    }

    protected <T> T delete(String path, Class<T> responseClass) {
        return delete(path, null, responseClass)
    }

    protected <T> T delete(String path, Object request, Class<T> responseClass) {
        String url = buildQueryString(endpoint + path, request)
        Response response = connection.delete(url, buildHeaders())
        ensureSuccess(response)
        return objectSerializer.deserialize(response.body, responseClass)
    }

    private Response ensureSuccess(Response response) {
        println(response.body)
        if (response.status != 200) {
            ErrorResponse error = objectSerializer.deserialize(response.body, ErrorResponse.class)
            throw new MailChimpException(error)
        }
        return response
    }

    private String buildQueryString(String url, Object request) {
        if (request == null) {
            return url
        }

        return url + objectSerializer.serializeToQueryString(request)
    }

    protected Map<String, String> buildHeaders() {
        Map<String, String> headers = new HashMap<>()

        headers.put("Authorization", "Bearer $apiKey")
        headers.put("Content-Type", "application/json")
        return headers
    }
}
