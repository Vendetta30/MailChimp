package com.mailchimp.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class Contact {
    String company
    String address1
    String address2
    String city
    String state
    String zip
    String country
    String phone
}
