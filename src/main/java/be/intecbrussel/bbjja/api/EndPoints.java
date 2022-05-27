package be.intecbrussel.bbjja.api;


public class EndPoints {

	public static final String EMPLOYEE_CLASS_LEVEL = "api/employees";
	public static final String EMPLOYEE_LIST_ALL = EMPLOYEE_CLASS_LEVEL + "/all";
	public static final String EMPLOYEE_LIST_IN_PAGES = EMPLOYEE_CLASS_LEVEL + "/pages/{page}";
	public static final String EMPLOYEE_LIST_SORTED = EMPLOYEE_CLASS_LEVEL + "/all/sorted";
	public static final String EMPLOYEE_CREATE = EMPLOYEE_CLASS_LEVEL + "/create";
	public static final String EMPLOYEE_UPDATE_BY_EXAMPLE = "EMPLOYEE_CLASS_LEVEL + /update";
	public static final String EMPLOYEE_UPDATE_BY_ID = "EMPLOYEE_CLASS_LEVEL + /update/{id}";
	public static final String EMPLOYEE_DELETE_BY_ID = EMPLOYEE_CLASS_LEVEL + "/delete/{id}";
	public static final String EMPLOYEE_GET_BY_ID = EMPLOYEE_CLASS_LEVEL + "/{id}";
	public static final String EMPLOYEE_GET_BY_EMAIL = EMPLOYEE_CLASS_LEVEL + "/search/{email}";
	public static final String EMPLOYEES_COUNT = EMPLOYEE_CLASS_LEVEL + "/count";


}
