package com.nexteducation.dependencyTool.util;

public interface Constants {
	public static final String USER_KEY = "user";
	public static final String UCID_KEY = "UCID";
	public static final String ADMIN_USER_KEY = "adminuser";
	public static final String AFFILIATE_KEY = "affiliate";
	public static final String STICKY_USER_EMAIL_ID = "StickyUserId";
	public static final String STICKY_USER_PASSWORD = "StickyUserH";
	public static final String STICKY_ADMIN_USER_EMAIL_ID = "StickyAdminUserId";
	public static final String STICKY_ADMIN_USER_PASSWORD = "StickyAdminUserH";
	public static final String STICKY_AFFILIATE_EMAIL_ID = "StickyAffId";
	public static final String STICKY_AFFILIATE_PASSWORD = "StickyAffH";

	public static final String SN_USER_KEY = "snuser";
	public static final String STICKY_SCHOOLNEXT_USER_ID = "StickySNUserId";
	public static final String STICKY_SCHOOLNEXT_USER_PASSWORD = "StickySNUserH";
	public static final String BRAND_TN = "tn";
	public static final String BRAND_SN = "sn";
	public static final String BOARD_SN = "snboard";
	public static final String STICKY_SCHOOLNEXT_BOARD_ID = "StickySNBoardId";

	public static final String SESSION_VALID = "valid";
	public static final String SESSION_INVALID = "invalid";
	public static final String SESSION_LOGOUT = "logout";
	public static final String SESSION_FORCED_LOGOUT = "forcedLogout";
	public static final String USER_SESSION_ID = "sessionID";

	// subscribable unit related constants
	public static final int MAX_TRIAL_UNITS_PER_USER = 3;

	interface StatusResults {
		public static final int STUDENT = 1;
		public static final int PARENT = 2;
	}

	public static final int INVALID_USERNAME = -1;
	public static final int INVALID_PASSWORD = -2;
	public static final int INVALID_NODE = -3;
	public static final int LOGIN_SUCCESS = 0;
	public static final int LOGIN_FAILED = -1;
	public static final int LOGIN_SUSPENDED = -4;
	public static final int INVALID_CLASSSECTION = -5;
	public static final int INVALID_NS_LICENSE = -6;

	public static final String[] NODE_HIREARCHY = { "Subject", "Chapter", "Lesson", "Section", "Topic" };

	// Javaswf Parser related constants
	public static final int TAG_DEFINEFONT3 = 75;
	public static final int TAG_DEFINESCENEINFO = 86;
	// public static final String mStringEncoding =
	// SWFConstants.STRING_ENCODING_MX;

	/** Mailing system constants */

	// Template type constants
	public static final String EMAIL_VALIDATION = "EMAIL_VALIDATION";
	public static final String CHANGE_EMAIL = "CHANGE_EMAIL";
	public static final String CHANGE_EMAIL_CONFIRM = "CHANGE_EMAIL_CONFIRM";
	public static final String WELCOME = "WELCOME";
	public static final String ACTIVATION = "ACTIVATION";
	public static final String INVITE_TO_GROUP = "INVITE_TO_GROUP";
	public static final String FORUM_FORWARD_THREAD = "FORUM_FORWARD_THREAD";
	public static final String CHANGE_PASSWORD = "CHANGE_PASSWORD";
	public static final String POST_ACTIVATION = "POST_ACTIVATION";
	public static final String TELL_FRIEND = "TELL_FRIEND";
	public static final String SUBSCRIPTION = "SUBSCRIPTION";
	public static final String REF_SUB_INTIMATION = "REF_SUB_INTIMATION";
	public static final String LEAD_FORM = "LEAD_FORM";
	public static final String DOWNLOAD = "DOWNLOAD";
	public static final String ASK_TEACHER = "ASK_TEACHER";
	public static final String CHANGE_EMAIL_UID = "CHANGE_EMAIL_UID";
	public static final String CHANGE_EMAIL_EID = "CHANGE_EMAIL_EID";
	public static final String CHANGE_EMAIL_AID = "CHANGE_EMAIL_AID";
	public static final String CHANGE_EMAIL_AEID = "CHANGE_EMAIL_AEID";
	public static final String CONFIRMATION_CODE = "CONFIRMATION_CODE";

	public static final String AFFILIATE_EMAIL_VALIDATION = "AFFILIATE_EMAIL_VALIDATION";
	public static final String AFFILIATE_WELCOME = "AFFILIATE_WELCOME";
	public static final String AFFILIATE_CHANGE_EMAIL = "AFFILIATE_CHANGE_EMAIL";
	public static final String AFFILIATE_CHANGE_EMAIL_CONFIRM = "AFFILIATE_CHANGE_EMAIL_CONFIRM";
	public static final String AFFILIATE_FORGOT_PASSWORD = "AFFILIATE_FORGOT_PASSWORD";
	// for blue dart
	public static final String BLUEDART_TRACK = "BLUEDART_TRACK";
	public static final String ACTIVATION_PROCESS = "ACTIVATION_PROCESS";
	public static final String SMS_ACTIVATION_PROCESS = "SMS_ACTIVATION_PROCESS";
	// for EOD CS Mailer
	public static final String EOD_CS_MAILER = "EOD_CS_MAILER";

	// for telemarketing
	public static final String TELE_GENERIC = "TELE_GENERIC";
	public static final long NOPC_DEADLEAD_RESOLUTION_TYPE = 21;
	public static final long SALE_CLOSED_RESOLUTION_TYPE = 19;
	public static final String CONFIRM_BULK_ORDER = "CONFIRM_BULK_ORDER";
	public static final String IMAGE_MODE_URL = "url";
	public static final String IMAGE_MODE_EMBEDDED = "embedded";

	public static final String RUN_MODE_CTR = "container";
	public static final String RUN_MODE_STANDALONE = "standalone";

	public static final int MAIL_STATUS_PENDING = 0;
	public static final int MAIL_STATUS_SENT_TO_MX = 1;
	public static final int MAIL_STATUS_DELIVERED = 2;
	public static final int MAIL_STATUS_PICKED = 3;
	public static final int MAIL_STATUS_ERROR_OTHER = -98;
	public static final int MAIL_STATUS_ERROR_MX = -99;
	public static final int MAIL_STATUS_ERROR_FILE_IO = -100;
	public static final int MAIL_STATUS_DEFERRED = -101;
	public static final int MAIL_STATUS_BOUNCED = -102;

	public static final String MAIL_STATUS_ALL = "10";
	public static final String CONFIRM_WP_ORDER = "CONFIRM_WP_ORDER";
	public static final String CONFIRM_WP_ORDER_SUCCESS = "CONFIRM_WP_ORDER_SUCCESS";
	/** End of mailing system constants */

	/** Report constants */
	public static final int CONTENT_SECTION = 0;
	public static final int SYLLABUS_NODE = 1;
	/** End of report constants */

	/** Site Search Constants */
	public static final String NAVIGATING_SITE = "site";
	public static final String NAVIGATING_COMMINITY = "community";
	public static final String SEARCH_RESULTS = "searchResults";
	public static final String SEARCH_PAGE_COUNT = "pageCount";
	public static final String THIS_PAGE_NO = "thisPageNumber";
	public static final String SEARCH_WORD = "searchWord";
	public static final String GOOGLE = "google";
	public static final String YAHOO = "yahoo";

	/** End of site search constants */

	public static final double PERCENTAGE_LIMIT = 80;

	// Encryption related constants
	// TODO
	public static final String ENCRYPTION_KEY = "FVXkLA+U9juHpd8OjzLnUvIMrYk9Npe8"; //
	public static final String CLIENT_ASSET_LIST = "clientAssetList";
	public static final String CLIENT_UPGRADER_LINK = "/bin/Upgrader.exe";
	public static final String FETCH_METHOD = "fetch";
	public static final String STORE_METHOD = "store";
	public static final String DELETE_METHOD = "delete";
	public static final int PROFILE_UPDATE_PARTIAL = 1;
	public static final int PROFILE_UPDATE_SUBSCRIPTION = 2;
	public static final int PROFILE_UPDATE_DEMOCD = 3;
	public static final int SCIENCE_BLUEPRINT_QUESTION_COUNT = 27;
	public static final int MATHS_BLUEPRINT_QUESTION_COUNT = 30;
	public static final String APP_PAYMENT_ENCRYPTION_KEY = "keyStore.erp.app#@!1q2w3e4r5t";
	/* Client cofig related constants */

	public static final String[] SUPPORT_INFO_LOCATION = { "header", "footer", "login", "help" };
	public static final String[] SUBJECTS = { "Maths", "Science", "Maths-TSP", "Science-TSP", "Geography", "History",
			"English", "Environmental-Science", "Physics", "Chemistry", "Biology", "Civics", "Economics" };

	public static final String LANG_ENGLISH_ISOCODE = "ENG";

	public static final String LANG_HINDI_ISOCODE = "HIN";

	/* Iconcept custom struts results */
	public static final String NOTMODIFIED = "notmodified";

	public static final String DUMMY_DOMAIN = "lnext.com";

	/*
	 * offline client related constants
	 */
	public static final int UCID_HASH_BITS = 32;

	public static final int EMAILID_HASH_BITS = 16;

	public static final int PKGID_BITS = 10;

	public static final int PARITY_BITS = 4;

	public static final int TIME_BITS = 6;

	public static final int USERID_BITS = 20;

	public static final int HASH_CODE_BITS = 32;

	public static final int CLIENT_VERSION_BITS = 2;

	public static final int NO_EMAIL_HASH = 43382;// "noemail@learnnext.com"

	// Transaction related constants

	public static final String VAT_TAX_TYPE = "vatRate";
	public static final String SERVICE_TAX_TYPE = "serviceTaxRate";
	public static final String TAX_TYPE_VAT_CFORM = "cformVatRate";

	public static final String DELIVERY_NONE = "None";
	public static final String DELIVERY_USB = "USB";
	public static final String DELIVERY_SAMPLE_CD = "Sample_CD";
	public static final String DELIVERY_COD_LETTER = "COD_Letter";
	public static final String DELIVERY_CD = "CD";
	public static final String DELIVERY_RESHIPMENT_CD = "RESHIPMENT_CD";
	public static final String DELIVERY_DVD = "DVD";

	public static final String SCARTCH_CARD_DESC = "Scratch Card";
	public static final int MAX_AUTO_UCID_RELEASE_CNT = 3;
	public static final String VSC_PKG_ID = "999";

	public static final long DEFAULT_BOARD_ID = 2L;

	// ---- SMS related constants ----
	public static final int SMS_STATUS_IN_QUEUE = 0;
	public static final int SMS_STATUS_SENT_TO_SP = 1;
	public static final int SMS_STATUS_ERROR_OTHER = -98;
	public static final int SMS_STATUS_OPTED_OUT = -103;
	public static final int SMS_STATUS_INVALID_NUMBER = -102;

	public static final int SMS_STATUS_SP_IN_QUEUE = 10;
	public static final int SMS_STATUS_SP_SUBMITTED_TO_CARRIER = 11;
	public static final int SMS_STATUS_SP_DELIVERED = 2;
	public static final int SMS_STATUS_SP_PICKED = 3;
	// all these are end statuses
	public static final int SMS_STATUS_SP_UNDELIVERED = -12;
	public static final int SMS_STATUS_SP_EXPIRED = -13;
	public static final int SMS_STATUS_SP_REJECTED = -14;

	// SMS template constants
	public static final String SMS_GENERIC = "SMS_GENERIC";
	public static final String SMS_SEND_PROMOCODE = "SMS_SEND_PROMOCODE";
	public static final String SMS_ORDER_BLUEDART_VPP = "SMS_ORDER_BLUEDART_VPP";
	public static final String SMS_DEMOCD_BLUEDART_POST = "SMS_DEMOCD_BLUEDART_POST";
	public static final String SMS_SUBSCRIPTION_ACTIVATION = "SMS_SUBSCRIPTION_ACTIVATION";
	public static final String SMS_OFFLINE_ACTIVATION = "SMS_OFFLINE_ACTIVATION";
	public static final String SMS_SINGLE_ITEM_ORDER_GOT_MARKED_PENDING = "SMS_SINGLE_ITEM_ORDER_GOT_MARKED_PENDING";
	public static final String SMS_MULTI_ITEM_ORDER_GOT_MARKED_PENDING = "SMS_MULTI_ITEM_ORDER_GOT_MARKED_PENDING";
	public static final String SMS_VPP_REMINDER = "SMS_VPP_REMINDER";
	public static final String SMS_SUBSCRIPTION_OFFLINE_ACTIVATION = "SMS_SUBSCRIPTION_OFFLINE_ACTIVATION";
	public static final String SMS_SCP_UPLOAD = "SMS_SCP_UPLOAD";
	public static final String SMS_CONFIRMATION_CODE = "SMS_CONFIRMATION_CODE";
	public static final String SMS_VALID_LUCKY_CODE = "SMS_VALID_LUCKY_CODE";
	public static final String SMS_INVALID_LUCKY_CODE = "SMS_INVALID_LUCKY_CODE";
	public static final String SMS_CONFIRM_WP_ORDER = "SMS_CONFIRM_WP_ORDER";
	public static final String SMS_CONFIRM_WP_ORDER_SUCCESS = "SMS_CONFIRM_WP_ORDER_SUCCESS";
	// ---- End of SMS constants ----
	public static final String ASSESSMENT_PAGE = "assessmentPage";
	public static final String CLIENT_CONTENT_TYPE = "text/xml";

	// Internationa Pricing constants
	public static final String BASE_CURRENCY = "INR";
	public static final int EMAILID_MAX_HASH = 0xFFFF;
	public static final String RSYNC_SCRIPT = "rsyncusagedata.sh";
	public static final long TELEMARKETING_RESOLUTIONTYPE = 34L;
	public static final long TELEMARKETING_ISSUETYPE = 34L;
	public static final int DEFAULT_VALIDITY_PERIOD = 12;
	public static final String NONBLUEDARTLIST = "nonBluedartList";
	public static final String BLUEDARTORDERLIST = "blueDartOrderList";
	public static final long CREATE_SCHOOL_SUSPECT_ACTIVITY = 101L;
	public static final long SCHOOL_UPDATE_ACTIVITY = 100L;
	public static final long PRE_SALES_CONTACT = 1L;
	public static final long SYSTEM_ADMIN_ID = 239L;
	public static final long EXTEND_DUE_DATE_FOR_LNONLINE_RESOLUTION_TYPE = 52L;
	public static final long EXTEND_DUE_DATE_FOR_CLIENT_DW_RESOLUTION_TYPE = 53L;

	public static final String APPOINTMENT_CREATE_ACTIVITY = "create";
	public static final String APPOINTMENT_UPDATE_ACTIVITY = "update";
	public static final String APPOINTMENT_CANCEL_ACTIVITY = "cancel";
	public static final String APPOINTMENT_COMPLETE_ACTIVITY = "complete";
	public static final String APPOINTMENT_POSTPONE_ACTIVITY = "postpone";
	public static final int SIGNATURE_BITS = 32;

	public static final String DEFAULT_COUNTRY_CODE = "IN";
	public static final long DEFAULT_STUDYCLASS_ID = 2L;
	public static final String DEFAULT_REGISTER_AS = "Student";
	public static final long DEFAULT_ADDRESS_ID = 690L;
	public static final long SCHOOL_DEAD_LEAD_ACTIVITY = 102L;

	// SN CRM Constants
	public static final int SCHOOL_CONFIDENCE_PROPOSAL_SUBMITTED = 50;
	public static final int SCHOOL_CONFIDENCE_PROPOSAL_APPROVED = 65;
	public static final int SCHOOL_CONFIDENCE_PO_SUBMITTED = 97;
	public static final long SNSCHOOL_DEAD_STATUS = 15;
	public static final long SNSCHOOL_OPEN_STATUS = 2;
	public static final long SNSCHOOL_NEW_STATUS = 1;
	public static final long SNSCHOOL_PROPOSAL_TO_BE_SUBMITTED = 16L;
	public static final long SNSCHOOL_LOST_STATUS = 17;
	public static final long SNSCHOOL_ACCEPTED_STATUS = 18;
	public static final long SNSCHOOL_PO_COLLECTED = 9L;

	public static int PRE_SALES_MAX_ACTIVITY_ORDER = 6;
	public static int PROPOSAL_SUBMISSION_ORDER = 6;
	public static final long VALIDATE_ACTIVITY_PROPOSAL_SUBMIT = 6;
	public static final int EXPECTED_DEAL_CLOSURE_DAYS_INITIAL = 35;
	public static final int EXPECTED_DEAL_CLOSURE_DAYS_APPROVAL = 28;
	public static final int EXPECTED_DEAL_CLOSURE_DAYS_APPROVED = 21;
	public static final int EXPECTED_DEAL_CLOSURE_DAYS_PO_ENTERED = 14;
	public static final String GENERIC_PKG_ID = "519";

	// TN Box Management tasks
	// public static final String RSYNC_SCRIPT = "boxstartup.sh";
	public static final long SN_OTHER_CONTACT_REASON = 5L;
	public static final String SN_DIRECTOR_ISSUE = "SN_DIRECTOR_ISSUE";
	public static final String SN_SUBMIT_TO_OPS = "SN_SUBMIT_TO_OPS";

	// Partner related constants
	public static final int TATA_PARTNER_ID = 1;
	public static final String PARTNER_USER_ID = "partnerUserId";
	public static final String PARTNER_USER_KEY = "partnerUser";
	public static final int PROVISIONING_SUCCESS = 0;
	public static final int PROVISIONING_FAILURE = -1;
	public static final int AIRTEL_PARTNER_ID = 2;

	public static final int DEPROVISIONING_SUCCESS = 0;
	public static final int DEPROVISIONING_FAILURE = -1;
	public static final long BDB_ROLE_ID = 4L;
	public static final long PRESALES_ROLE_ID = 6L;
	public static final String SN_PRESALES_FIX_APPOINTMENT = "SN_PRESALES_FIX_APPOINTMENT";

	public static final String SUBJECT_NAME_DELETED_ITEMS = "DeletedItems";
	public static final String SYLLABUS_NAME_DELETED_ITEMS = "DeletedItems";
	public static final long STUDY_CLASS_IX_ID = 1;

	public static String MATHSLAB_SYLLABUS_ROOT = "mathsLab.syllabus.root";

	public static String MATHSLAB_CONTENT_ROOT = "mathsLab.content.root";

	public static final String SUBJECT_MATHS = "maths";
	public static final String SUBJECT_SCIENCE = "science";
	public static final long MATHS_SUBJECT_ID = 1L;
	public static final long SCIENCE_SUBJECT_ID = 8L;

	public static final long MATHS_LAB_SUBJECT_ID = 63L;
	public static final long SCIENCE_LAB_SUBJECT_ID = 69L;

	public static final long CLASS_NO_FOURTH = 4L;
	public static final long CLASS_NO_FIFTH = 5L;

	// param names
	public static final String PARAM_APPID = "appid";
	public static final String PARAM_SESSIONID = "sessionid";
	public static final String PARAM_USERID = "userid";
	public static final String PARAM_SESSIONKEY = "sessionkey";
	public static final String PARAM_SIGN = "sign";
	public static final String PARAM_TOKEN = "token";
	public static final String PARAM_REDIRECTURL = "redirectpage";

	public static final double MAX_STUYDHOUR_PER_DAY = 10 * 60; // In Minutes
	public static final long CUSTOM_STUDY_PLAN = 4L;

	public static final String DEFAULT_FORUM = "228c5a50-7b19-41b6-8d49-a134999406f2";
	public static final Long ASSESSMENT_RESOURCE_TYPE_ID = 8L;
	public static final Long ASSESSMENT_ASSET_TYPE_ID = 22L;
	
	// Add dependencyToolClientException Error Codes Here
	

}
