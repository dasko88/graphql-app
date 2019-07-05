package it.ts.dotcom.demo.graphqlapp.model.entity;

import it.ts.dotcom.demo.graphqlapp.model.repository.UserRepository;
import it.ts.dotcom.demo.graphqlspringbootstarter.service.ConsumesRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
@ConsumesRepository(UserRepository.class)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String surname;
	private Integer height;
	private Date createdDate;

}