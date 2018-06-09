package com.mailchimp

import com.mailchimp.connection.Connection
import com.mailchimp.connection.HttpClientConnection
import com.mailchimp.connection.Response
import com.mailchimp.exception.MailChimpException
import com.mailchimp.model.*
import com.mailchimp.model.content.Content
import com.mailchimp.request.*
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

    TemplateFolder createTemplateFolder(TemplateFolderRequest request) {
        post(MailChimpURI.TEMPLATE_FOLDER, request, TemplateFolder.class)
    }

    ListResponse<TemplateFolder> listAllTemplateFolder() {
        list(MailChimpURI.TEMPLATE_FOLDER, TemplateFolder.class)
    }

    TemplateFolder listTemplateFolder(String templateFolderId) {
        getCall(format(MailChimpURI.TEMPLATE_FOLDER_SINGLE, templateFolderId), TemplateFolder.class)
    }

    DeleteResponse deleteTemplateFolder(String templateFolderId) {
        delete(format(MailChimpURI.TEMPLATE_FOLDER_SINGLE, templateFolderId), DeleteResponse.class)
    }

    TemplateFolder updateTemplateFolder(String templateFolderId, TemplateFolderRequest request) {
        post(format(MailChimpURI.TEMPLATE_FOLDER_SINGLE, templateFolderId), request, TemplateFolder.class)
    }

    Template createTemplate(TemplateRequest request) {
        post(MailChimpURI.TEMPLATE, request, Template.class)
    }

    ListResponse<Template> listAllTemplate() {
        list(MailChimpURI.TEMPLATE, Template.class)
    }

    Template listTemplate(String templateId) {
        getCall(format(MailChimpURI.TEMPLATE_SINGLE, templateId), Template.class)
    }

    DeleteResponse deleteTemplate(String templateId) {
        delete(format(MailChimpURI.TEMPLATE_SINGLE, templateId), DeleteResponse.class)
    }

    Template updateTemplate(String templateId, TemplateRequest request) {
        post(format(MailChimpURI.TEMPLATE_SINGLE, templateId), request, Template.class)
    }

    /*FileManagerFolder createFileManagerFolder(FileManagerFolderRequest request) {
        post(MailChimpURI.FILE_MANAGER_SPECIFIC, request, FileManagerFolder.class)
    }

    ListResponse<FileManagerFolder> listAllFileManagerFolder() {
        list(MailChimpURI.FILE_MANAGER_FILES, FileManagerFolder.class)
    }

    FileManagerFolder listFileManagerFolder(String fileManagerFolderId) {
        getCall(format(MailChimpURI.FILE_MANAGER_SPECIFIC, fileManagerFolderId), FileManagerFolder.class)
    }

    DeleteResponse deleteFileManagerFolder(String fileManagerFolderId) {
        delete(format(MailChimpURI.FILE_MANAGER_SPECIFIC, fileManagerFolderId), DeleteResponse.class)
    }

    FileManagerFolder updateFileManagerFolder(String fileManagerFolderId, FileManagerFolderRequest request) {
        post(format(MailChimpURI.FILE_MANAGER_SPECIFIC, fileManagerFolderId), request, FileManagerFolder.class)
    }
*/

    FileManagerFile createFileManagerFile(FileManagerFileRequest request) {
        post(MailChimpURI.FILE_MANAGER_SPECIFIC, request, FileManagerFile.class)
    }

    ListResponse<FileManagerFile> listAllFileManagerFile() {
        list(MailChimpURI.FILE_MANAGER_FILES, FileManagerFile.class)
    }

    FileManagerFile listFileManagerFile(String fileManagerFileId) {
        getCall(format(MailChimpURI.FILE_MANAGER_SPECIFIC, fileManagerFileId), FileManagerFile.class)
    }

    DeleteResponse deleteFileManagerFile(String fileManagerFileId) {
        delete(format(MailChimpURI.FILE_MANAGER_SPECIFIC, fileManagerFileId), DeleteResponse.class)
    }

    FileManagerFile updateFileManagerFile(String fileManagerFileId, FileManagerFileRequest request) {
        post(format(MailChimpURI.FILE_MANAGER_SPECIFIC, fileManagerFileId), request, FileManagerFile.class)
    }


    ListTemplate createList(ListTemplate request) {
        post(MailChimpURI.LIST, request, ListTemplate.class)
    }

    ListResponse<ListTemplate> listAllList() {
        list(MailChimpURI.LIST, ListTemplate.class)
    }

    ListTemplate listList(String listId) {
        getCall(format(MailChimpURI.LIST_SPECIFIC, listId), ListTemplate.class)
    }

    DeleteResponse deleteList(String listId) {
        delete(format(MailChimpURI.LIST_SPECIFIC, listId), DeleteResponse.class)
    }

    ListTemplate updateCampaign(String listId, ListTemplate request) {
        post(format(MailChimpURI.LIST_SPECIFIC, listId), request, ListTemplate.class)
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
        Response response = connection.postAction(endpoint + path, "", buildHeaders())
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
