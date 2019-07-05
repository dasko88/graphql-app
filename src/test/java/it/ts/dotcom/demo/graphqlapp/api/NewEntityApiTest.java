package it.ts.dotcom.demo.graphqlapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.ts.dotcom.demo.graphqlapp.GraphqlAppApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import util.GraphQLRequest;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = GraphqlAppApplication.class)
@AutoConfigureMockMvc
public class NewEntityApiTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Value("classpath:queries/newTask.txt")
	private Resource newTaskQuery;
	@Value("classpath:queries/newUser.txt")
	private Resource newUserQuery;

	@Test
	public void newUser() throws Exception {
		GraphQLRequest request = new GraphQLRequest.Builder(readFileToString(newUserQuery.getFile(), "UTF-8"))
				.addVariable("name", "Mario")
				.addVariable("surname", "Mitico")
				.addVariable("height", 180)
				.build();

		mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.data.newUser.name", is("Mario")));
	}

	@Test
	public void newTask() throws Exception {
		GraphQLRequest request = new GraphQLRequest.Builder(readFileToString(newTaskQuery.getFile(), "UTF-8"))
				.addVariable("name", "zitto")
				.addVariable("code", "T001")
				.addVariable("priority", 1)
				.build();

		mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.data.newTask.name", is("zitto")));
	}

}