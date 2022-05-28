package be.intecbrussel.bbjja.api;


public class EndPoints {

	/* EMPLOYEE ENDPOINTS */

	public static final String EMPLOYEE_CLASS_LEVEL = "api/employees";
	public static final String EMPLOYEE_LIST_ALL = EMPLOYEE_CLASS_LEVEL + "/all";
	public static final String EMPLOYEE_LIST_IN_PAGES = EMPLOYEE_CLASS_LEVEL + "/pages/{page}";
	public static final String EMPLOYEE_LIST_SORTED = EMPLOYEE_CLASS_LEVEL + "/all/sorted";
	public static final String EMPLOYEE_CREATE = EMPLOYEE_CLASS_LEVEL + "/create";
	public static final String EMPLOYEE_UPDATE_BY_EXAMPLE = EMPLOYEE_CLASS_LEVEL + "/update";
	public static final String EMPLOYEE_UPDATE_BY_ID = EMPLOYEE_CLASS_LEVEL + "/update/{id}";
	public static final String EMPLOYEE_DELETE_BY_ID = EMPLOYEE_CLASS_LEVEL + "/delete/{id}";
	public static final String EMPLOYEE_GET_BY_ID = EMPLOYEE_CLASS_LEVEL + "/{id}";
	public static final String EMPLOYEE_GET_BY_EMAIL = EMPLOYEE_CLASS_LEVEL + "/search/{email}";
	public static final String EMPLOYEES_COUNT = EMPLOYEE_CLASS_LEVEL + "/count";

	/* USER ENDPOINTS */

	public static final String USER_CLASS_LEVEL = "api/users";
	public static final String USER_LIST_ALL = USER_CLASS_LEVEL + "/all";
	public static final String USERS_LIST_IN_PAGES = USER_CLASS_LEVEL + "/pages/{page}";
	public static final String USERS_LIST_SORTED = USER_CLASS_LEVEL + "/all/sorted";
	public static final String USERS_CREATE = USER_CLASS_LEVEL + "/create";

	public static final String USER_CHANGE_PASSWORD_NO_OLD = USER_CLASS_LEVEL + "change_password_no_validation";
	public static final String USER_CHANGE_PASSWORD_WITH_VALIDATION = USER_CLASS_LEVEL + "change_password_with_validation";
	public static final String USER_UPDATE_BY_EXAMPLE = USER_CLASS_LEVEL + "/update";
	public static final String USER_UPDATE_BY_ID =USER_CLASS_LEVEL + "/update/{id}";
	public static final String USER_DELETE_BY_ID = USER_CLASS_LEVEL + "/delete/{id}";
	public static final String USER_GET_BY_ID = USER_CLASS_LEVEL + "/{id}";
	public static final String USER_GET_BY_EMAIL = USER_CLASS_LEVEL + "/search/{email}";
	public static final String USERS_COUNT = USER_CLASS_LEVEL + "/count";

	/* SUBSCRIBER ENDPOINTS */

	public static final String SUBSCRIBER_CLASS_LEVEL = "api/subscribers";
	public static final String SUBSCRIBERS_LIST_ALL = SUBSCRIBER_CLASS_LEVEL + "/all";
	public static final String SUBSCRIBERS_LIST_IN_PAGES = SUBSCRIBER_CLASS_LEVEL + "/pages/{page}";
	public static final String SUBSCRIBERS_LIST_SORTED = SUBSCRIBER_CLASS_LEVEL + "/all/sorted";
	public static final String SUBSCRIBER_CREATE = SUBSCRIBER_CLASS_LEVEL + "/create";
	public static final String SUBSCRIBER_UPDATE_BY_EXAMPLE = SUBSCRIBER_CLASS_LEVEL + "/update";
	public static final String SUBSCRIBER_UPDATE_BY_ID = SUBSCRIBER_CLASS_LEVEL + "/update/{id}";
	public static final String SUBSCRIBER_DELETE_BY_ID = SUBSCRIBER_CLASS_LEVEL + "/delete/{id}";
	public static final String SUBSCRIBER_GET_BY_ID = SUBSCRIBER_CLASS_LEVEL + "/{id}";
	public static final String SUBSCRIBER_GET_BY_EMAIL = SUBSCRIBER_CLASS_LEVEL + "/search/{email}";
	public static final String SUBSCRIBERS_COUNT = SUBSCRIBER_CLASS_LEVEL + "/count";

	/* PAGE ENDPOINTS */

	public static final String PAGE_CLASS_LEVEL = "api/pages";
	public static final String PAGE_LIST_ALL = PAGE_CLASS_LEVEL + "/all";
	public static final String PAGE_LIST_IN_PAGES = PAGE_CLASS_LEVEL + "/pages/{page}";
	public static final String PAGE_LIST_SORTED = PAGE_CLASS_LEVEL + "/all/sorted";
	public static final String PAGE_CREATE = PAGE_CLASS_LEVEL + "/create";
	public static final String PAGE_UPDATE_BY_EXAMPLE = PAGE_CLASS_LEVEL + "/update";
	public static final String PAGE_UPDATE_BY_ID = PAGE_CLASS_LEVEL + "/update/{id}";
	public static final String PAGE_DELETE_BY_ID = PAGE_CLASS_LEVEL + "/delete/{id}";
	public static final String PAGE_GET_BY_ID = PAGE_CLASS_LEVEL + "/{id}";
	public static final String PAGE_GET_BY_EMAIL = PAGE_CLASS_LEVEL + "/search/{email}";
	public static final String PAGES_COUNT = PAGE_CLASS_LEVEL + "/count";

	/* SLIDE ENDPOINTS */

	public static final String SLIDE_CLASS_LEVEL = "api/slides";
	public static final String SLIDE_LIST_ALL = SLIDE_CLASS_LEVEL + "/all";
	public static final String SLIDE_LIST_IN_SLIDES = SLIDE_CLASS_LEVEL + "/pages/{slide}";
	public static final String SLIDE_LIST_SORTED = SLIDE_CLASS_LEVEL + "/all/sorted";
	public static final String SLIDE_CREATE = SLIDE_CLASS_LEVEL + "/create";
	public static final String SLIDE_UPDATE_BY_EXAMPLE = SLIDE_CLASS_LEVEL + "/update";
	public static final String SLIDE_UPDATE_BY_ID = SLIDE_CLASS_LEVEL + "/update/{id}";
	public static final String SLIDE_DELETE_BY_ID = SLIDE_CLASS_LEVEL + "/delete/{id}";
	public static final String SLIDE_GET_BY_ID = SLIDE_CLASS_LEVEL + "/{id}";
	public static final String SLIDE_GET_BY_EMAIL = SLIDE_CLASS_LEVEL + "/search/{email}";
	public static final String SLIDES_COUNT = SLIDE_CLASS_LEVEL + "/count";

	/* GRAPPLING ENDPOINTS */

	public static final String GRAPPLING_CLASS_LEVEL = "api/grappling";
	public static final String GRAPPLING_LIST_ALL = GRAPPLING_CLASS_LEVEL + "/all";
	public static final String GRAPPLING_LIST_SCHOOL = GRAPPLING_CLASS_LEVEL + "/school";
	public static final String GRAPPLING_LIST_STREET = GRAPPLING_CLASS_LEVEL + "/street";
	public static final String GRAPPLING_LIST_IN_PAGES = GRAPPLING_CLASS_LEVEL + "/pages/{page}";
	public static final String GRAPPLING_LIST_SORTED = GRAPPLING_CLASS_LEVEL + "/all/sorted";
	public static final String GRAPPLING_CREATE = GRAPPLING_CLASS_LEVEL + "/create";
	public static final String GRAPPLING_UPDATE_BY_EXAMPLE = GRAPPLING_CLASS_LEVEL + "/update";
	public static final String GRAPPLING_UPDATE_BY_ID = GRAPPLING_CLASS_LEVEL + "/update/{id}";
	public static final String GRAPPLING_DELETE_BY_ID = GRAPPLING_CLASS_LEVEL + "/delete/{id}";
	public static final String GRAPPLING_GET_BY_ID = GRAPPLING_CLASS_LEVEL + "/{id}";
	public static final String GRAPPLING_GET_BY_EMAIL = GRAPPLING_CLASS_LEVEL + "/search/{email}";
	public static final String GRAPPLING_COUNT = GRAPPLING_CLASS_LEVEL + "/count";

	/* OFFER ENDPOINTS */

	public static final String OFFER_CLASS_LEVEL = "api/offers";
	public static final String OFFERS_LIST_ALL = OFFER_CLASS_LEVEL + "/all";
	public static final String OFFERS_LIST_IN_PAGES = OFFER_CLASS_LEVEL + "/pages/{page}";
	public static final String OFFERS_LIST_SORTED = OFFER_CLASS_LEVEL + "/all/sorted";
	public static final String OFFER_CREATE = OFFER_CLASS_LEVEL + "/create";
	public static final String OFFER_UPDATE_BY_EXAMPLE = OFFER_CLASS_LEVEL + "/update";
	public static final String OFFER_UPDATE_BY_ID = OFFER_CLASS_LEVEL + "/update/{id}";
	public static final String OFFER_DELETE_BY_ID = OFFER_CLASS_LEVEL + "/delete/{id}";
	public static final String OFFER_GET_BY_ID = OFFER_CLASS_LEVEL + "/{id}";
	public static final String OFFER_GET_BY_EMAIL = OFFER_CLASS_LEVEL + "/search/{email}";
	public static final String OFFERS_COUNT = OFFER_CLASS_LEVEL + "/count";

	/* SCHOOL ENDPOINTS */

	public static final String SCHOOL_CLASS_LEVEL = "api/schools";
	public static final String SCHOOLS_LIST_ALL = SCHOOL_CLASS_LEVEL + "/all";
	public static final String SCHOOLS_LIST_IN_PAGES = SCHOOL_CLASS_LEVEL + "/pages/{page}";
	public static final String SCHOOLS_LIST_SORTED = SCHOOL_CLASS_LEVEL + "/all/sorted";
	public static final String SCHOOL_CREATE = SCHOOL_CLASS_LEVEL + "/create";
	public static final String SCHOOL_UPDATE_BY_EXAMPLE = SCHOOL_CLASS_LEVEL + "/update";
	public static final String SCHOOL_UPDATE_BY_ID = SCHOOL_CLASS_LEVEL + "/update/{id}";
	public static final String SCHOOL_DELETE_BY_ID = SCHOOL_CLASS_LEVEL + "/delete/{id}";
	public static final String SCHOOL_GET_BY_ID = SCHOOL_CLASS_LEVEL + "/{id}";
	public static final String SCHOOL_GET_BY_EMAIL = SCHOOL_CLASS_LEVEL + "/search/{email}";
	public static final String SCHOOLS_COUNT = SCHOOL_CLASS_LEVEL + "/count";

}
