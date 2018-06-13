package com.mailchimp

interface MailChimpURI {
    static String VERSION = "3.0"
    static String CAMPAIGN = "/campaigns"
    static String SINGLE_CAMPAIGN = "/campaigns/%s/"
    static String CAMPAIGN_CONTENT = "/campaigns/%s/content"
    static String CAMPAIGN_FEEDBACK = "/campaigns/%s/feedback"
    static String CAMPAIGN_FEEDBACK_MESSAGE = "/campaigns/%s/feedback/%s"
    static String CAMPAIGN_SEND_CHECKLIST = "/campaigns/%s/send-checklist"
    static String CANCEL_CAMPAIGN = "/campaigns/%s/actions/cancel-send"
    static String PAUSE_CAMPAIGN = "/campaigns/%s/actions/pause"
    static String REPLICATE_CAMPAIGN = "/campaigns/%s/actions/replicate"
    static String RESUME_CAMPAIGN = "/campaigns/%s/actions/resume"
    static String SCHEDULE_CAMPAIGN = "/campaigns/%s/actions/schedule"
    static String UNSCHEDULE_CAMPAIGN = "/campaigns/%s/actions/unschedule"
    static String SEND_CAMPAIGN = "/campaigns/%s/actions/send"

    static String CAMPAIGN_FOLDER = "/campaign-folders"

    static String SEARCH_CAMPAIGN = "/search-campaigns"

    static String FILE_MANAGER_FILES = "/file-manager/files"
    static String FILE_MANAGER_SPECIFIC = "/file-manager/files/%s"

    static String TEMPLATE_FOLDER = "/template-folders"
    static String TEMPLATE_FOLDER_SINGLE = "/template-folders/%s"

    static String TEMPLATE = "/templates"
    static String TEMPLATE_SINGLE = "/templates/%s"
    static String DEFAULT_TEMPLATE_SINGLE = "/templates/%s/default-content"

    //List URL
    static String LIST = "/lists"
    static String LIST_SPECIFIC = "/lists/%s"
    static String LIST_ACTIVITY = "/lists/%s/activity"
    static String LIST_CLIENTS = "/lists/%s/clients"
    static String LIST_ABUSE_REPORTS = "/lists/%s/abuse-reports"
    static String LIST_ABUSE_REPORTS_SPECIFIC = "/lists/%s/abuse-reports/%s"
    static String LIST_GROWTH_HISTORY = "/lists/%s/growth-history"
    static String LIST_GROWTH_HISTORY_MONTH = "/lists/%s/growth-history/%s"
    static String LIST_LOCATIONS = "/lists/%s/locations"
    static String LIST_MEMBERS = "/lists/%s/members"
    static String LIST_SEGMENTS = "/lists/%s/segments"
    static String LIST_WEBHOOKS = "/lists/%s/webhooks"

    //Member URLs
    static String SEARCH_MEMBERS = "/search-members"
}