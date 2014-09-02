package controllers;

import java.util.*;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import play.data.Form;
import play.data.DynamicForm;
import play.db.ebean.Model.Finder;

import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.avaje.ebean.Ebean;

import models.*;
import auth0.*;

public class ProductController extends Controller {

	private static Exception error;

    private static Map<String, Object> profile;

    public static Result create() {

        if (!authorize()) {

            ObjectNode result = Json.newObject();
            result.put("code", "unauthorized");
            result.put("msg", "" + error);
            return ok(result);
        }

        Product prod = bindProduct();

        prod.setUserId(parseLong(session("user.id")));

        prod.setCreatedDate(new Date());
        prod.setLastModifiedDate(prod.getCreatedDate());

        Ebean.save(prod);

        ObjectNode result = Json.newObject();
        result.put("code", "success");
        result.put("msg", "Successfully Created " + prod);
        return ok(result);
    }

    public static Result delete(Long id) {

        if (!authorize()) {

            ObjectNode result = Json.newObject();
            result.put("code", "unauthorized");
            result.put("msg", "" + error);
            return ok(result);
        }

        Product prod = retrieveProduct(id);

        Ebean.delete(prod);

        ObjectNode result = Json.newObject();
        result.put("code", "success");
        result.put("msg", "");
        return ok(result);
    }

    public static boolean authorize() {

		try {
		
            JWTFilter jf = new JWTFilter();
            jf.doFilter();
            profile = jf.getProfile();

			String user_id = (String) profile.get("user_id");
            String email = (String) profile.get("email");
            String name = (String) profile.get("name");

            String fName = "";
            String lName = "";

            try {
                String[] tt = name.split(" ", 2);
                fName = tt[0];
                lName = tt[1];
            } catch (Exception ex) {

            }

            User user = null;

            try {

                user = retrieveUser(user_id);
            } catch (IndexOutOfBoundsException ex) {

                user = new User(fName, lName, email, user_id);
                Ebean.save(user);
            }

            user = retrieveUser(user_id);

            session("user.name", user.getFirstName() + " " + user.getLastName());
            session("user.id", user.getId() + "");

            return true;
        } catch (Exception ex) {
			error = ex;
            return false;
        } 
    }

    public static User retrieveUser(String user_id) {

        return Ebean.find(User.class)
                .where()
                .eq("userId", user_id)
                .findList()
                .get(0);
    }

    public static Product retrieveProduct(long id) {

        Product prod = Ebean.find(Product.class)
                .where()
                .eq("id", id)
                .eq("userId", parseLong(session("user.id")))
                .findList()
                .get(0);

        return prod;
    }

    private static Product bindProduct() {
        Product prod = new Product();
        return bindProduct(prod);
    }

    private static Product bindProduct(Product prod) {

        DynamicForm requestData = Form.form().bindFromRequest();
        String name = requestData.get("name");
        String description = requestData.get("description");

        prod.setName(name);
        prod.setDescription(description);
        return prod;
    }

    public static Result update(Long id) {

        if (!authorize()) {

            ObjectNode result = Json.newObject();
            result.put("code", "unauthorized");
            result.put("msg", "" + error);
            return ok(result);
        }

        Product prod = retrieveProduct(id);

        bindProduct(prod);

        prod.setLastModifiedDate(new Date());

        Ebean.update(prod);

        ObjectNode result = Json.newObject();
        result.put("code", "success");
        result.put("msg", "");
        return ok(result);
    }

    public static Result list() {

        if (!authorize()) {

            ObjectNode result = Json.newObject();
            result.put("code", "unauthorized");
            result.put("msg", "" + error);
            return ok(result);
        }

        long page = requestLong("page");
        long size = requestLong("size");

        size = Math.min(size, 50l);
        size = size <= 0l ? 20l : size;

        long offset = (page - 1l) * size;

        List<Product> prods = Ebean.find(Product.class)
                .where()
                .eq("userId", parseLong(session("user.id")))
                .setFirstRow((int) offset)
                .setMaxRows((int) size)
                .findList();

        String u_name = session("user.name");

        return ok(Json.toJson(prods));
    }

    static long requestLong(String key) {

        try {

            DynamicForm requestData = Form.form().bindFromRequest();

            long l = Long.parseLong(requestData.get(key));

            //	System.out.println("###[[parse : " + l + " => " + session("user.id"));
            return l;

        } catch (NullPointerException | NumberFormatException ex) {

            return 0;
        }

    }

    static long parseLong(String key) {

        try {

            DynamicForm requestData = Form.form().bindFromRequest();

            long l = Long.parseLong(key);

            //	System.out.println("###[[parse : " + l + " => " + session("user.id"));
            return l;

        } catch (NullPointerException | NumberFormatException ex) {

            return 0;
        }

    }

    public static Result show(Long id) {

        if (!authorize()) {

            ObjectNode result = Json.newObject();
            result.put("code", "unauthorized");
            result.put("msg", "" + error);
            return ok(result);
        }

        Product prod = retrieveProduct(id);

        String u_name = session("user.name");

        return ok(Json.toJson(prod));
    }

}
