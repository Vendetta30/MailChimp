package com.mailchimp

import com.mailchimp.connection.Connection
import com.mailchimp.connection.HttpClientConnection
import com.mailchimp.connection.Response
import com.mailchimp.exception.MailChimpException
import com.mailchimp.response.ErrorResponse
import com.mailchimp.response.ListResponse
import com.mailchimp.util.ObjectSerializer

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

    public <T> T getCall(String path, Class<T> responseClass) {
        getCall(endpoint, path, responseClass)
    }

    public <T> T getCall(String endpoint, String path, Class<T> responseClass) {
        Response response = connection.get(endpoint + path, buildHeaders())
        ensureSuccess(response)
        objectSerializer.deserialize(response.body, responseClass)
    }

    public <T> T post(String path, Object request, Class<T> responseClass) {
        String requestBody = objectSerializer.serialize(request)
        Response response = connection.post(endpoint + path, requestBody, buildHeaders())
        ensureSuccess(response)
        objectSerializer.deserialize(response.body, responseClass)
    }

    public <T> T put(String path, Object request, Class<T> responseClass) {
        String requestBody = objectSerializer.serialize(request)
        Response response = connection.put(endpoint + path, requestBody, buildHeaders())
        ensureSuccess(response)
        objectSerializer.deserialize(response.body, responseClass)
    }

    public void postAction(String path) {
        Response response = connection.postAction(endpoint + path, "", buildHeaders())
        ensureSuccess(response)
    }

    public <T> ListResponse<T> list(String path, Class<T> elementClass) {
        list(endpoint, path, null, elementClass)
    }

    public <T> ListResponse<T> list(String path, Object request, Class<T> elementClass) {
        list(endpoint, path, request, elementClass)
    }

    public <T> ListResponse<T> list(String endpoint, String path, Object request, Class<T> elementClass) {
        String url = buildQueryString(endpoint + path, request)
        Response response = connection.get(url, buildHeaders())
        ensureSuccess(response)
        objectSerializer.deserializeList(response.body, elementClass)
    }

    public <T> T delete(String path, Class<T> responseClass) {
        delete(path, null, responseClass)
    }

    public <T> T delete(String path, Object request, Class<T> responseClass) {
        String url = buildQueryString(endpoint + path, request)
        Response response = connection.delete(url, buildHeaders())
        ensureSuccess(response)
        objectSerializer.deserialize(response.body, responseClass)
    }

    public Response ensureSuccess(Response response) {
        println(response.body)
        if (response.status != 200) {
            ErrorResponse error = objectSerializer.deserialize(response.body, ErrorResponse.class)
            throw new MailChimpException(error)
        }
        return response
    }

    public String buildQueryString(String url, Object request) {
        if (request == null) {
            return url
        }

        return url + objectSerializer.serializeToQueryString(request)
    }

    public Map<String, String> buildHeaders() {
        Map<String, String> headers = new HashMap<>()

        headers.put("Authorization", "Bearer $apiKey")
        headers.put("Content-Type", "application/json")
        return headers
    }
}
