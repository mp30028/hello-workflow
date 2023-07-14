package com.zonesoft.hello.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@GetMapping(value={"","/","/greeting","/hello"})
	@ResponseBody
	public String greeting( @AuthenticationPrincipal OidcUser oidcUser) {
		Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();
		if (authenticationToken.isAuthenticated()) {
			String username = oidcUser.getPreferredUsername();
			String fullname = oidcUser.getFullName();
			String info = authenticationToken.toString().replace("], ", "]\n");
			StringBuilder htmlResponse = new StringBuilder();
			
				htmlResponse.append("<h3>");
					htmlResponse.append("Greetings ");htmlResponse.append(fullname);
					htmlResponse.append("<h4>");
						htmlResponse.append("You have successfully logged in as ");htmlResponse.append(username);
					htmlResponse.append("</h4>");
				htmlResponse.append("</h3>");
	
				htmlResponse.append("<h4>");
					htmlResponse.append("Other available info");
				htmlResponse.append("</h4>");
				
				htmlResponse.append("<pre>");
					htmlResponse.append(info);
				htmlResponse.append("</pre>");

			return htmlResponse.toString();
		} else {
			return "YOU ARE NOT AUTHENTICATED";
		}
	}

}