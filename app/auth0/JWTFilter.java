package auth0;

import com.auth0.jwt.JWTVerifier;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.regex.Pattern;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import play.mvc.*;
import play.data.*;
import play.Play;


import static play.mvc.Controller.*;
import static java.util.Arrays.asList;

public class JWTFilter {
    private JWTVerifier jwtVerifier;
	private Map<String, Object> profile;

	public JWTFilter() {
	
		init();
	}
	

    public void init() {
        jwtVerifier = new JWTVerifier(
			Play.application().configuration().getConfig("auth0").getString("client_secret"),
                Play.application().configuration().getConfig("auth0").getString("client_id"));
    }


    public void doFilter() throws IOException, SecurityException {

        String token = getToken();

        try {
            profile = jwtVerifier.verify(token);
        } catch (Exception e) {
            throw new SecurityException("Unauthorized: Token validation failed", e);
        }
    }

    private String getToken() throws SecurityException {
        String token = null;
        final String authorizationHeader = request().getHeader("authorization");
        if (authorizationHeader != null) {
            String[] parts = authorizationHeader.split(" ");
            if (parts.length == 2) {
                String scheme = parts[0];
                String credentials = parts[1];

                Pattern pattern = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);
                if (pattern.matcher(scheme).matches()) {
                    token = credentials;
                }
            } else {
                throw new SecurityException("Unauthorized: Format is Authorization: Bearer [token]");
            }
        } else {
            throw new SecurityException("Unauthorized: No Authorization header was found");
        }
        return token;
    }
	
	public Map<String, Object> getProfile() {
		return profile;
	}
	
}
