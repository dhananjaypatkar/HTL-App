package controllers;

import java.io.File;

import views.html.*;
import model.Timeline;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
  
    public static Result main() throws Exception{
		String data = FileUtils.readFileToString(new File("example_json.json"));
		System.out.println("Data from json file "+Json.parse(data));
		
		JsonNode jsonNode = Json.parse(data);
		ObjectMapper mapper = new ObjectMapper();
		Timeline timeline = mapper.convertValue(jsonNode, Timeline.class);
		System.out.println("timeLine "+timeline.getTitle());
		return ok(main.render("Your HealthLine",Json.stringify(jsonNode)));
    }
  
}
