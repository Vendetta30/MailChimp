package com.mailchimp.connection

import com.mailchimp.exception.ConnectionException
import org.apache.http.client.methods.*
import org.apache.http.entity.StringEntity
import org.apache.http.entity.mime.MultipartEntityBuilder
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils

import java.nio.charset.Charset
import java.util.Map.Entry

class HttpClientConnection implements Connection {
    static final Charset UTF8 = Charset.forName("UTF-8")

    final CloseableHttpClient httpClient

    HttpClientConnection() {
        this(HttpClientBuilder.create().build())
    }

    HttpClientConnection(CloseableHttpClient httpClient) {
        this.httpClient = httpClient
    }

    Response get(String url, Map<String, String> headers) {
        return execute(new HttpGet(url), headers)
    }

    Response post(String url, String requestBody, Map<String, String> headers) {
        HttpPost post = new HttpPost(url)
        if (requestBody != null) {
            post.setEntity(new StringEntity(requestBody, UTF8))
        }

        return execute(post, headers)
    }

    Response postAction(String url, String requestBody, Map<String, String> headers) {
        HttpPost post = new HttpPost(url)
        post.setEntity(new StringEntity(requestBody, UTF8))
        return execute(post, headers)
    }

    Response put(String url, String requestBody, Map<String, String> headers) {
        HttpPut put = new HttpPut(url)
        if (requestBody != null) {
            put.setEntity(new StringEntity(requestBody, UTF8))
        }

        return execute(put, headers)
    }

    Response delete(String url, Map<String, String> headers) {
        return execute(new HttpDelete(url), headers)
    }


    Response multipart(String url, Map<String, File> files, Map<String, String> form, Map<String, String> headers) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create()
        for (Entry<String, File> entry : files.entrySet()) {
            builder.addBinaryBody(entry.getKey(), entry.getValue())
        }

        for (Entry<String, String> entry : form.entrySet()) {
            builder.addTextBody(entry.getKey(), entry.getValue())
        }

        HttpPost httpPost = new HttpPost(url)
        httpPost.setEntity(builder.build())

        headers.remove("Content-Type")
        return execute(httpPost, headers)
    }

    private Response execute(HttpUriRequest request, Map<String, String> headers) {
        for (Entry<String, String> header : headers.entrySet()) {
            String headerName = header.getKey()
            String headerValue = header.getValue()

            if ("User-Agent".equalsIgnoreCase(headerName)) {
                headerValue += " HttpClient"
            }

            request.addHeader(headerName, headerValue)
        }

        try {
            CloseableHttpResponse response = httpClient.execute(request)
            return new Response(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), UTF8));
        } catch (IOException e) {
            throw new ConnectionException(e)
        }
    }

    public void close() throws IOException {
        httpClient.close()
    }
}
