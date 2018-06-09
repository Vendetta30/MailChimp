package com.mailchimp.client

import com.mailchimp.MailChimpClient
import com.mailchimp.MailChimpURI
import com.mailchimp.model.fileManager.FileManagerFolder
import com.mailchimp.request.FileManagerFolderRequest
import com.mailchimp.response.DeleteResponse
import com.mailchimp.response.ListResponse

import static java.lang.String.format

class FileManagerFolderClient {
    MailChimpClient mailChimpClient

    FileManagerFolderClient() {}

    FileManagerFolderClient(MailChimpClient mailChimpClient) {
        this.mailChimpClient = mailChimpClient
    }

    FileManagerFolder createFileManagerFolder(FileManagerFolderRequest request) {
        this.mailChimpClient.post(MailChimpURI.FILE_MANAGER_SPECIFIC, request, FileManagerFolder.class)
    }

    ListResponse<FileManagerFolder> listAllFileManagerFolder() {
        this.mailChimpClient.list(MailChimpURI.FILE_MANAGER_FILES, FileManagerFolder.class)
    }

    FileManagerFolder listFileManagerFolder(String fileManagerFolderId) {
        this.mailChimpClient.getCall(format(MailChimpURI.FILE_MANAGER_SPECIFIC, fileManagerFolderId), FileManagerFolder.class)
    }

    DeleteResponse deleteFileManagerFolder(String fileManagerFolderId) {
        this.mailChimpClient.delete(format(MailChimpURI.FILE_MANAGER_SPECIFIC, fileManagerFolderId), DeleteResponse.class)
    }

    FileManagerFolder updateFileManagerFolder(String fileManagerFolderId, FileManagerFolderRequest request) {
        this.mailChimpClient.post(format(MailChimpURI.FILE_MANAGER_SPECIFIC, fileManagerFolderId), request, FileManagerFolder.class)
    }
}
