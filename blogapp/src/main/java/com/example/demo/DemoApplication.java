package com.example.demo;

import java.io.IOException;
import java.net.ServerSocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(DemoApplication.class);

		int serverPort = 8080;

		while (isLocalPortInUse(serverPort) == true) {
			serverPort++;
		}

		System.getProperties().put("server.port", Integer.toString(serverPort));
		app.run(args);
	}

	private static boolean isLocalPortInUse(int port) {
		try {
			new ServerSocket(port).close();
			return false;
		} catch (IOException e) {
			return true;
		}
	}

}
