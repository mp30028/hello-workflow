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
			String info = authenticationToken.toString().replace("], ", "]\n\n");
			StringBuilder htmlResponse = new StringBuilder();
			
				htmlResponse.append("<h3>");
					htmlResponse.append("Greetings ");htmlResponse.append(fullname);
					htmlResponse.append("<h4>");
						htmlResponse.append("You have successfully logged in as ");htmlResponse.append(username);
					htmlResponse.append("</h4>");
				htmlResponse.append("</h3>");
				htmlResponse.append("<br/><br/>");
				htmlResponse.append("<b>");
					htmlResponse.append("Other info available");
				htmlResponse.append("</b>");
				
				htmlResponse.append("<div style=\"width:800px; overflow:auto\">");
					htmlResponse.append("<pre style=\"white-space: pre-wrap\">");
						htmlResponse.append(info);
					htmlResponse.append("</pre>");
				htmlResponse.append("<div/>");

			return htmlResponse.toString();
		} else {
			return "YOU ARE NOT AUTHENTICATED";
		}
	}

}