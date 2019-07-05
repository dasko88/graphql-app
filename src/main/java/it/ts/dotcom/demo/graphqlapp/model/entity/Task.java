package it.ts.dotcom.demo.graphqlapp.model.entity;

import it.ts.dotcom.demo.graphqlapp.model.repository.TaskRepository;
import it.ts.dotcom.demo.graphqlspringbootstarter.service.ConsumesRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
@ConsumesRepository(TaskRepository.class)
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String code;
	private Integer priority;

}
