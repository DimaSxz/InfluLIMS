package com.springboot.influlims.entity.enums;

public enum Role {

//	ROLE_SUPERUSER,
	ROLE_ADMIN,
	ROLE_USER,
//	ROLE_MODERATOR,
//	ROLE_VIEWER,
//	ROLE_TEST,

// individual rights
//	ROLE_VIEW_NO,
//	ROLE_VIEW_SELF,
//	ROLE_VIEW_TEST,
//	ROLE_VIEW_NOTEST,
//	ROLE_VIEW_ALL,
//
//	ROLE_ADD_ALL,
//	ROLE_ADD_NO,
//	ROLE_ADD_SAMPLE,
//	ROLE_ADD_EXTRACTION,
//	ROLE_ADD_PCR,
//
//	ROLE_MODIFY_NO,
//	ROLE_MODIFY_SELF,
//	ROLE_MODIFY_TEST,
//	ROLE_MODIFY_NOTEST,
//	ROLE_MODIFY_ALL,
//
//	ROLE_DELETE_NO,
//	ROLE_DELETE_SELF,
//	ROLE_DELETE_TEST,
//	ROLE_DELETE_NOTTEST,
//	ROLE_DELETE_ALL,
//
//	ROLE_MANAGE_USERS_NO,
//	ROLE_MANAGE_USERS_TEST,
//	ROLE_MANAGE_USERS_NOTEST,
//	ROLE_MANAGE_USERS_ALL,
//
//	ROLE_MANAGE_REGIONS_NO,
//	ROLE_MANAGE_REGIONS_SELF,
//	ROLE_MANAGE_REGIONS_TEST,
//	ROLE_MANAGE_REGIONS_NOTEST,
//	ROLE_MANAGE_REGIONS_ALL,
//
//	ROLE_MANAGE_PROVIDERS_NO,
//	ROLE_MANAGE_PROVIDERS_SELF,
//	ROLE_MANAGE_PROVIDERS_TEST,
//	ROLE_MANAGE_PROVIDERS_NOTEST,
//	ROLE_MANAGE_PROVIDERS_ALL,
//
//	ROLE_MANAGE_PROJECTS_NO,
//	ROLE_MANAGE_PROJECTS_SELF,
//	ROLE_MANAGE_PROJECTS_TEST,
//	ROLE_MANAGE_PROJECTS_NOTEST,
//	ROLE_MANAGE_PROJECTS_ALL,
//
//	ROLE_MANAGE_REAGENTS_NO,
//	ROLE_MANAGE_REAGENTS_SELF,
//	ROLE_MANAGE_REAGENTS_TEST,
//	ROLE_MANAGE_REAGENTS_NOTEST,
//	ROLE_MANAGE_REAGENTS_ALL
 ;

	public static final int length = Role.values().length;

}

/*
 * "all" - all data
 * "+" - all data without TEST (default)
 * "self" - only added him self data
 * "-" - none

"ROLES" {
	"ROLE_SUPERUSER {
		"view" : "all",
		"add" : "+",
		"modify" : "all",
		"delete"  : "all",
		"manage_users" : "all",
		"manage_regions" : "all",
		"manage_providers" : "all",
		"manage_projects" : "all"
	},
	"ROLE_ADMIN" {
		"view" : "all",
		"add" : "+",
		"modify" : "+",
		"delete"  : "+",
		"manage_users" : "MODERATOR and below",
		"manage_regions" : "+",
		"manage_providers" : "+",
		"manage_projects" : "+"
	},
	"ROLE_MODERATOR" {
		"view" : "+",
		"add" : "-",
		"modify" : "+",
		"delete"  : "+",
		"manage_users" : "USERS and below",
		"manage_regions" : "+",
		"manage_providers" : "+",
		"manage_projects" : "+"
	},
	"ROLE_USER" {
		"view" : "+",
		"add" : "+",
		"modify" : "self",
		"delete"  : "self",
		"manage_users" : "-",
		"manage_regions" : "-",
		"manage_providers" : "-",
		"manage_projects" : "-"
	},
	"ROLE_VIEWER" {
		"view" : "+",
		"add" : "-",
		"modify" : "none",
		"delete"  : "none",
		"manage_users" : "-",
		"manage_regions" : "-",
		"manage_providers" : "-",
		"manage_projects" : "-"
	},
	"ROLE_TEST" {
		"view" : "self",
		"add" : "+",
		"modify" : "self",
		"delete"  : "self",
		"manage_users" : "-",
		"manage_regions" : "self",
		"manage_providers" : "self",
		"manage_projects" : "self"
	}
}
 */