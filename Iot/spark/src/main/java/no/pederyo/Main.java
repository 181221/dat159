package no.pederyo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import static spark.Spark.*;

import java.time.LocalDateTime;

public class Main {
	
	static Temperature temp = null;
	
    public static void main(String[] args) {
    	
    	temp = new Temperature();

        port(getHerokuAssignedPort());

    	String device = "pederyo_dweet";
        get("get/dweet/for/"+device, (req, res) -> {
        	Gson gson = new Gson();
            JsonObject dweet = new JsonObject();
            dweet.addProperty("this", "succeeded");
            dweet.addProperty("by", "getting");
            dweet.addProperty("the", "dweet");
            JsonObject with = new JsonObject();

            with.addProperty("thing", device);
            with.addProperty("created", LocalDateTime.now().withNano(0).toString());
            with.add("content", gson.toJsonTree(temp));
            dweet.add("with", with);
            res.type("application/json");
        	return gson.toJson(dweet);
		});
        
        put("get/dweet/for/"+device, (req,res) -> {

        	Gson gson = new Gson();
        	
        	temp = gson.fromJson(req.body(), Temperature.class);
            res.type("application/json");
            return gson.toJson(temp);
        	
        });

        get("*", (request, response) -> "404 - not found");
    }
    
    static String tempToJson () {
    	
    	Gson gson = new Gson();
    	    
    	String jsonInString = gson.toJson(temp);
    	
    	return jsonInString;
    }
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
