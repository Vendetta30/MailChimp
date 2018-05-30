package com.mailchimp

import com.mailchimp.connection.Connection
import com.mailchimp.connection.HttpClientConnection
import com.mailchimp.connection.Response
import com.mailchimp.exception.MailChimpException
import com.mailchimp.model.Campaign
import com.mailchimp.model.content.Content
import com.mailchimp.request.CampaignRequest
import com.mailchimp.request.ContentRequest
import com.mailchimp.response.DeleteResponse
import com.mailchimp.response.ErrorResponse
import com.mailchimp.response.ListResponse
import com.mailchimp.util.ObjectSerializer

import static java.lang.String.format

class MailChimpClient {
    Connection connection
    String apiKey
    String serverUrl
    String endpoint

    final ObjectSerializer objectSerializer = ObjectSerializer.INSTANCE

    MailChimpClient() {
        this(null, null)
    }

    MailChimpClient(String apiKey, String serverUrl) {
        this(apiKey, serverUrl, new HttpClientConnection())
    }

    MailChimpClient(String apiKey, String serverUrl, Connection connection) {
        this.apiKey = apiKey
        this.serverUrl = serverUrl
        this.connection = connection
        this.endpoint = "${this.serverUrl}/${MailChimpURI.VERSION}"
    }

    Campaign createCampaign(CampaignRequest request) {
        post(MailChimpURI.CAMPAIGN, request, Campaign.class)
    }

    ListResponse<Campaign> listAllCampaign() {
        list(MailChimpURI.CAMPAIGN, Campaign.class)
    }

    Campaign listCampaign(String campaignId) {
        getCall(format(MailChimpURI.SINGLE_CAMPAIGN, campaignId), Campaign.class)
    }

    DeleteResponse deleteCampaign(String campaignId) {
        delete(format(MailChimpURI.SINGLE_CAMPAIGN, campaignId), DeleteResponse.class)
    }

    Campaign updateCampaign(String campaignId, CampaignRequest request) {
        post(format(MailChimpURI.SINGLE_CAMPAIGN, campaignId), request, Campaign.class)
    }

    Content updateContentOfCampaign(String campaignId, ContentRequest request) {
        put(format(MailChimpURI.CAMPAIGN_CONTENT, campaignId), request, Content.class)
    }

    void cancelSendCampaign(String campaignId) {
        postAction(format(MailChimpURI.CANCEL_CAMPAIGN, campaignId))
    }

    void pauseCampaign(String campaignId) {
        postAction(format(MailChimpURI.PAUSE_CAMPAIGN, campaignId))
    }

    void replicateCampaign(String campaignId) {
        postAction(format(MailChimpURI.REPLICATE_CAMPAIGN, campaignId))
    }

    void resumeCampaign(String campaignId) {
        postAction(format(MailChimpURI.RESUME_CAMPAIGN, campaignId))
    }

    void scheduleCampaign(String campaignId) {
        postAction(format(MailChimpURI.SCHEDULE_CAMPAIGN, campaignId))
    }

    void sendCampaign(String campaignId) {
        postAction(format(MailChimpURI.SEND_CAMPAIGN, campaignId))
    }

    void unScheduleCampaign(String campaignId) {
        postAction(format(MailChimpURI.UNSCHEDULE_CAMPAIGN, campaignId))
    }

    protected <T> T getCall(String path, Class<T> responseClass) {
        getCall(endpoint, path, responseClass)
    }

    protected <T> T getCall(String endpoint, String path, Class<T> responseClass) {
        Response response = connection.get(endpoint + path, buildHeaders())
        ensureSuccess(response)
        objectSerializer.deserialize(response.body, responseClass)
    }

    protected <T> T post(String path, Object request, Class<T> responseClass) {
        String requestBody = objectSerializer.serialize(request)
        Response response = connection.post(endpoint + path, requestBody, buildHeaders())
        ensureSuccess(response)
        objectSerializer.deserialize(response.body, responseClass)
    }

    protected <T> T put(String path, Object request, Class<T> responseClass) {
        String requestBody = objectSerializer.serialize(request)
        Response response = connection.put(endpoint + path, requestBody, buildHeaders())
        ensureSuccess(response)
        objectSerializer.deserialize(response.body, responseClass)
    }

    protected void postAction(String path) {
        Response response = connection.post(endpoint + path, "", buildHeaders())
        ensureSuccess(response)
    }

    protected <T> ListResponse<T> list(String path, Class<T> elementClass) {
        list(endpoint, path, null, elementClass)
    }

    protected <T> ListResponse<T> list(String path, Object request, Class<T> elementClass) {
        list(endpoint, path, request, elementClass)
    }

    protected <T> ListResponse<T> list(String endpoint, String path, Object request, Class<T> elementClass) {
        String url = buildQueryString(endpoint + path, request)
        Response response = connection.get(url, buildHeaders())
        ensureSuccess(response)
        objectSerializer.deserializeList(response.body, elementClass)
    }

    protected <T> T delete(String path, Class<T> responseClass) {
        delete(path, null, responseClass)
    }

    protected <T> T delete(String path, Object request, Class<T> responseClass) {
        String url = buildQueryString(endpoint + path, request)
        Response response = connection.delete(url, buildHeaders())
        ensureSuccess(response)
        objectSerializer.deserialize(response.body, responseClass)
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
