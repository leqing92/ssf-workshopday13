package ibf2023.ssf.day13;

//import java.util.Collections;

import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day13WorkshopApplication /* implements CommandLineRunner */{

	// the priority for setting port : commandline below > commandline set SERVER_PORT=9000 > application.properties > default port 8080
	public static void main(String[] args) {
		String port = "8085";
		ApplicationArguments cliOpts = new DefaultApplicationArguments(args);

		if (cliOpts.containsOption("port")){
			port = cliOpts.getOptionValues("port").get(0); //get the first value
		}
		
		System.setProperty("server.port", port);
		SpringApplication.run(Day13WorkshopApplication.class, args);

		System.out.printf("Application started on port %s\n", port);
	}

//not working
	// @Override
	// public void run(String... args) {		

	// 	SpringApplication app = new SpringApplication(Day13WorkshopApplication.class);
	// 	String port = "8085";

	// 	ApplicationArguments cliOpts = new DefaultApplicationArguments(args);
	// 	if(cliOpts.containsOption("port")){
	// 		port = cliOpts.getOptionValues("port").get(0);
	// 	}
		
	// 	app.setDefaultProperties(Collections.singletonMap("server.port", port));

	// 	System.out.printf("Application started on port %s\n", port);
	// 	app.run(args);

	// }
}
