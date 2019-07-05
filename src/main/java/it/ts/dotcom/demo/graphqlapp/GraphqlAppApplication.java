package it.ts.dotcom.demo.graphqlapp;

import it.ts.dotcom.demo.graphqlapp.model.entity.Task;
import it.ts.dotcom.demo.graphqlapp.model.entity.User;
import it.ts.dotcom.demo.graphqlapp.model.repository.TaskRepository;
import it.ts.dotcom.demo.graphqlapp.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class GraphqlAppApplication {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TaskRepository taskRepository;


	public static void main(String[] args) {
		SpringApplication.run(GraphqlAppApplication.class, args);
	}

	@PostConstruct
	public void init() {
		userRepository.saveAll(Arrays.asList(
				new User(null, "Mario", "Verdi", 193, new Date()),
				new User(null, "Frank", "Rossi", 168, new Date()),
				new User(null, "Roberto", "Mancini", 174, new Date())
		));
		taskRepository.saveAll(Arrays.asList(
				new Task(null, "Pulire casa", "C001", 1, new Date()),
				new Task(null, "Fare spesa", "C002", 2, new Date()),
				new Task(null, "Ascoltare musica", "C003", 1, new Date()),
				new Task(null, "Guardare film", "C004", 3, new Date())
		));
	}

}
