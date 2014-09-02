package auth0;

import java.security.Principal;

import play.mvc.*;
import play.data.*;
import static play.mvc.Controller.*;

public class Auth0RequestWrapper {
    String idToken;

    public Auth0RequestWrapper(Tokens tokens) {

        this.idToken = tokens.getIdToken();
    }

    public Principal getUserPrincipal() {
	
        return new Auth0Principal(this.idToken);
    }

}