package com.mailchimp.client

import com.mailchimp.MailChimpClient
import com.mailchimp.MailChimpURI
import com.mailchimp.model.template.TemplateFolder
import com.mailchimp.request.TemplateFolderRequest
import com.mailchimp.response.DeleteResponse
import com.mailchimp.response.ListResponse

import static java.lang.String.format

class TemplateFolderClient {
    MailChimpClient mailChimpClient

    TemplateFolderClient() {}

    TemplateFolderClient(MailChimpClient mailChimpClient) {
        this.mailChimpClient = mailChimpClient
    }

    TemplateFolder createTemplateFolder(TemplateFolderRequest request) {
        this.mailChimpClient.post(MailChimpURI.TEMPLATE_FOLDER, request, TemplateFolder.class)
    }

    ListResponse<TemplateFolder> listAllTemplateFolder() {
        this.mailChimpClient.list(MailChimpURI.TEMPLATE_FOLDER, TemplateFolder.class)
    }

    TemplateFolder listTemplateFolder(String templateFolderId) {
        this.mailChimpClient.getCall(format(MailChimpURI.TEMPLATE_FOLDER_SINGLE, templateFolderId), TemplateFolder.class)
    }

    DeleteResponse deleteTemplateFolder(String templateFolderId) {
        this.mailChimpClient.delete(format(MailChimpURI.TEMPLATE_FOLDER_SINGLE, templateFolderId), DeleteResponse.class)
    }

    TemplateFolder updateTemplateFolder(String templateFolderId, TemplateFolderRequest request) {
        this.mailChimpClient.post(format(MailChimpURI.TEMPLATE_FOLDER_SINGLE, templateFolderId), request, TemplateFolder.class)
    }
}
