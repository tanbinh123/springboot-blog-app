package com.example.demo.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

@CrossOrigin
@RestController()
@RequestMapping("/api")
public class BackendController {

	private static Logger LOG = LoggerFactory.getLogger(BackendController.class);
	public static String WELCOME_MESSAGE = "Welcome to Spring Boot!";
	private static String PATH = "./guestbook.json";

	@GetMapping("/welcome")
	public String welcome() {
		LOG.info("GET request received to /welcome");
		return WELCOME_MESSAGE;
	}

	@GetMapping("/guestbook")
	public String returnGuestbook() throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		JsonArray object = null;
		String json = null;
		File f = new File(PATH);
		if (f.exists()) {
			object = gson.fromJson(new FileReader(PATH), JsonArray.class);
			json = gson.toJson(object);
			return json;
		} else {
			json = PopulateJson.returnJson();
			FileWriter file = new FileWriter(PATH);
			file.write(json);
			file.close();
			return json;
		}
	}

	@PostMapping("/newmessage")
	public static String postMessage(@RequestBody String payload)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		JsonArray object = null;
		File f = new File(PATH);
		if (f.exists()) {
			object = gson.fromJson(new FileReader(PATH), JsonArray.class);
		} else {
			object = new JsonArray();
		}
		String response = payload;
		JsonObject newObject = gson.fromJson(response, JsonObject.class);
		object.add(newObject);
		String json = gson.toJson(object);
		FileWriter file = new FileWriter(PATH);
		file.write(json);
		file.close();
		return json;

	}

	@GetMapping("/delete/{ID}")
	public String deleteRow(@PathVariable String ID) throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		JsonArray object = gson.fromJson(new FileReader(PATH), JsonArray.class);
		for (int i = 0; i < object.size(); i++) {
			JsonObject obj = object.get(i).getAsJsonObject();
			String result = obj.get("id").getAsString();
			if (result.equals(ID)) {
				object.remove(i);
			}
		}
		String json = gson.toJson(object);
		FileWriter file = new FileWriter(PATH);
		file.write(json);
		file.close();
		return json;

	}

	@GetMapping("/{[path:[^\\.]*}")
	public ModelAndView redirectApi() {
		LOG.info("Unknown path, BackendController redirecting..");
		return new ModelAndView("redirect:/");
	}
}