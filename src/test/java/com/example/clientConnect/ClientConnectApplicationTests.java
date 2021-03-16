package com.example.clientConnect;

import com.example.clientConnect.client.ClientController;
import com.example.clientConnect.client.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
class ClientConnectApplicationTests {

	@Autowired
	private MockMvc mvc;

	ObjectMapper mapper = new ObjectMapper();

	@MockBean
	ClientService clientService;

	@Test
	void getClients() throws Exception {

		RequestBuilder requestBuilder = get("/api/client/all");

		MvcResult result = mvc.perform(requestBuilder).andReturn();

		assertEquals("[]",result.getResponse().getContentAsString());
//		mvc.perform(get("/api/client/all"))
//			.contentType(MediaType.APPLICATION_JSON)
//			.andExpect(status().isOk());

	}


}
