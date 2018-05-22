package com.springboot.influlims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RouteController {

//	private Collection<? extends GrantedAuthority> getRoles() {
//		return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//	}

	@RequestMapping(value = {"/", "/main"})
	public String main() { return "main"; }

	@RequestMapping(value = "/manage-users", method = RequestMethod.GET)
	public String manageEmployees() { return "admin/employees";	}

	@RequestMapping(value = "/manage-providers", method = RequestMethod.GET)
	public String manageProviders() { return "admin/providers";	}

	@RequestMapping(value = "/manage-projects", method = RequestMethod.GET)
	public String manageProjects() { return "admin/projects";	}

	@RequestMapping(value = "/manage-reagents", method = RequestMethod.GET)
	public String manageReagents() { return "admin/reagents";	}

	@RequestMapping(value = "/add-extraction", method = RequestMethod.GET)
	public String addExtraction() { return "add-extraction";	}

	@RequestMapping(value = "/add-pcr", method = RequestMethod.GET)
	public String addPcr() { return "add-pcr";	}

}
