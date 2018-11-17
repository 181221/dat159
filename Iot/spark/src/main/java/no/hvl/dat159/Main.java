package no.hvl.dat159;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import static spark.Spark.*;

import java.time.LocalDateTime;

public class Main {
	
	static Temperature temp = null;
	
    public static void main(String[] args) {
    	
    	temp = new Temperature();
    	
    	port(8080);
    	
    	after((req, res) -> {
    		  res.type("application/json");
    		});
    	String device = "pederyo_dweet";
        get("get/latest/dweet/for/"+device, (req, res) -> {
        	Gson gson = new Gson();
            JsonObject dweet = new JsonObject();
            dweet.addProperty("this", "succeeded");
            dweet.addProperty("by", "getting");
            dweet.addProperty("the", "dweet");
            JsonObject with = new JsonObject();

            with.addProperty("thing", device);
            with.addProperty("created", LocalDateTime.now().toString());
            with.add("content", gson.toJsonTree(temp));
            dweet.add("with", with);
        	return gson.toJson(dweet);
		});
        
        put("/tempsensor/current", (req,res) -> {
        
        	Gson gson = new Gson();
        	
        	temp = gson.fromJson(req.body(), Temperature.class);
        
            return tempToJson();
        	
        });
    }
    
    static String tempToJson () {
    	
    	Gson gson = new Gson();
    	    
    	String jsonInString = gson.toJson(temp);
    	
    	return jsonInString;
    }
}
