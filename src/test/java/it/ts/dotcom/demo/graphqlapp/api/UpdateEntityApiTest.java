package it.ts.dotcom.demo.graphqlapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.ts.dotcom.demo.graphqlapp.GraphqlAppApplication;
import it.ts.dotcom.demo.graphqlapp.model.entity.User;
import it.ts.dotcom.demo.graphqlapp.model.repository.UserRepository;
import org.junit.Assert;
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
public class UpdateEntityApiTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private UserRepository userRepository;
	@Value("classpath:queries/updateUser.txt")
	private Resource updateUserQuery;

	@Test
	public void newUser() throws Exception {
		GraphQLRequest request = new GraphQLRequest.Builder(readFileToString(updateUserQuery.getFile(), "UTF-8"))
				.addVariable("id", 1)
				.addVariable("name", "Franco")
				.addVariable("surname", "Neri")
				.addVariable("height", 181)
				.build();

		mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.data.updateUser.name", is("Franco")));

		User user = userRepository.findById(1).get();
		Assert.assertEquals("Franco", user.getName());
	}

}