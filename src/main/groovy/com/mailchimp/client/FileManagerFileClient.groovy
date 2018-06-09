package com.mailchimp.client

import com.mailchimp.MailChimpClient
import com.mailchimp.MailChimpURI
import com.mailchimp.model.fileManager.FileManagerFile
import com.mailchimp.request.FileManagerFileRequest
import com.mailchimp.response.DeleteResponse
import com.mailchimp.response.ListResponse

import static java.lang.String.format

class FileManagerFileClient {
    MailChimpClient mailChimpClient

    FileManagerFileClient() {}

    FileManagerFileClient(MailChimpClient mailChimpClient) {
        this.mailChimpClient = mailChimpClient
    }

    FileManagerFile createFileManagerFile(FileManagerFileRequest request) {
        this.mailChimpClient.post(MailChimpURI.FILE_MANAGER_SPECIFIC, request, FileManagerFile.class)
    }

    ListResponse<FileManagerFile> listAllFileManagerFile() {
        this.mailChimpClient.list(MailChimpURI.FILE_MANAGER_FILES, FileManagerFile.class)
    }

    FileManagerFile listFileManagerFile(String fileManagerFileId) {
        this.mailChimpClient.getCall(format(MailChimpURI.FILE_MANAGER_SPECIFIC, fileManagerFileId), FileManagerFile.class)
    }

    DeleteResponse deleteFileManagerFile(String fileManagerFileId) {
        this.mailChimpClient.delete(format(MailChimpURI.FILE_MANAGER_SPECIFIC, fileManagerFileId), DeleteResponse.class)
    }

    FileManagerFile updateFileManagerFile(String fileManagerFileId, FileManagerFileRequest request) {
        this.mailChimpClient.post(format(MailChimpURI.FILE_MANAGER_SPECIFIC, fileManagerFileId), request, FileManagerFile.class)
    }
}
