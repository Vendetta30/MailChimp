package com.mailchimp.model.member

import com.mailchimp.model.Link
import com.mailchimp.model.Location
import com.mailchimp.model.Stats

class Member {
    String id
    String emailAddress
    String unique_email_id
    String emailType
    String status
    String unsubscribeReason
    Object mergeFields
    Object interest
    Stats stats
    String ip_signup
    String timestamp_signup
    String ip_opt
    String timestamp_opt
    String last_changed
    String language
    String email_client
    String list_id
    Integer member_rating
    List<Link> _links
    Location location
}
