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
        return post(MailChimpURI.CAMPAIGN, request, Campaign.class)
    }

    ListResponse<Campaign> listAllCampaign() {
        return list(MailChimpURI.CAMPAIGN, Campaign.class)
    }

    Campaign listCampaign(String campaignId) {
        return getCall(format(MailChimpURI.SINGLE_CAMPAIGN, campaignId), Campaign.class)
    }

    DeleteResponse deleteCampaign(String campaignId) {
        return delete(format(MailChimpURI.SINGLE_CAMPAIGN, campaignId), DeleteResponse.class)
    }

    Campaign updateCampaign(String campaignId, CampaignRequest request) {
        return post(format(MailChimpURI.SINGLE_CAMPAIGN, campaignId), request, Campaign.class)
    }

    void cancelSendCampaign(String campaignId, CampaignRequest request) {
        post(format(MailChimpURI.CANCEL_CAMPAIGN, campaignId), request, Campaign.class)
    }

    void pauseCampaign(String campaignId, CampaignRequest request) {
        post(format(MailChimpURI.PAUSE_CAMPAIGN, campaignId), request, Campaign.class)
    }

    void replicateCampaign(String campaignId, CampaignRequest request) {
        post(format(MailChimpURI.REPLICATE_CAMPAIGN, campaignId), request, Campaign.class)
    }

    void resumeCampaign(String campaignId, CampaignRequest request) {
        post(format(MailChimpURI.RESUME_CAMPAIGN, campaignId), request, Campaign.class)
    }

    void scheduleCampaign(String campaignId, CampaignRequest request) {
        post(format(MailChimpURI.SCHEDULE_CAMPAIGN, campaignId), request, Campaign.class)
    }

    void sendCampaign(String campaignId, CampaignRequest request) {
        post(format(MailChimpURI.SEND_CAMPAIGN, campaignId), request, Campaign.class)
    }

    void unScheduleCampaign(String campaignId, CampaignRequest request) {
        post(format(MailChimpURI.UNSCHEDULE_CAMPAIGN, campaignId), request, Campaign.class)
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
