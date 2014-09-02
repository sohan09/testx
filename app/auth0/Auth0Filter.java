package auth0;

import java.io.IOException;

import play.mvc.*;
import play.data.*;
import play.Play;
import static play.mvc.Controller.*;
import play.libs.F;

public class Auth0Filter {

    public void init() {
    }
	
    public boolean authorize() {

        Tokens tokens = loadTokens();

        if (tokens.getAccessToken() == null || tokens.getIdToken() == null) {
			
			return false;
        }
	
        return true;
    }

    protected Tokens loadTokens() {

        return new Tokens((String) session("idToken"),
                (String) session("accessToken"));
    }
	
    public void destroy() {
    }
}