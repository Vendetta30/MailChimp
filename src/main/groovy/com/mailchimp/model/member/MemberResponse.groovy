package com.mailchimp.model.member

import com.mailchimp.model.Link

class MemberResponse {
    ExactMatch exact_matches
    ExactMatch full_search
    List<Link> _links
}
