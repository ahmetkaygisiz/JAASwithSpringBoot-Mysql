package com.akua.loginmodule;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;

import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthorityGranter implements AuthorityGranter {

	public Set<String> grant(Principal principal) {
		if (principal.getName().equalsIgnoreCase("dbadmin1")) {
			return Collections.singleton("ROLE_ADMIN");
		} else if (principal.getName().equalsIgnoreCase("dbuser1")) {
			return Collections.singleton("ROLE_USER");
		} else
			return Collections.singleton("ROLE_USER");
	}
}
