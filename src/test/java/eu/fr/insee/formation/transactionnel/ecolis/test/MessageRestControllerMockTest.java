package eu.fr.insee.formation.transactionnel.ecolis.test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import eu.fr.indyli.formation.business.ecolis.service.impl.MessageServiceImpl;
import eu.fr.indyli.formation.business.entity.Message;
import eu.fr.indyli.formation.transactionnel.ecolis.boot.SpringConfigWebApplication;
import eu.fr.indyli.formation.transactionnel.ecolis.utils.EcolisConstantesWeb.EcolisConstantesURI;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringConfigWebApplication.class},
    webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
public class MessageRestControllerMockTest {

  @MockBean
  private MessageServiceImpl messageService;


  @Autowired
  private MockMvc mvc;


  @Test
  public void itShouldReturnOneMessageById() throws Exception {
    // Given
    Message message1 = new Message(1, "Pourrez vous déposer à Suresnes", new Date());
    // When
    when(this.messageService.findById(1)).thenReturn(message1);

    // Then
    this.mvc
        .perform(get(EcolisConstantesURI.PATH_MESSAGE + "/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.corps", is("Pourrez vous déposer à Suresnes")))
        .andDo(print());
  }

  @Test
  public void itShouldReturnAllTheMessages() throws Exception {
    // Given
    // TODO : Initialiser la collection
    List<Message> messages = null;

    // When
    when(this.messageService.findAll()).thenReturn(messages);

    // Then
    this.mvc
        .perform(get(EcolisConstantesURI.PATH_MESSAGE)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void itShouldReturnMessageByEmail() throws Exception {
    // Given
    String email = "armee.gouv@gouv.fr";
    Message message1 = new Message(1, "Pourrez vous déposer à Suresnes", new Date());
    Message message2 = new Message(2, "Je vous attendrai entre 14h et 15h", new Date());
    List<Message> messages = Arrays.asList(message1, message2);

    // When
    when(this.messageService.getMessageByEmailUser(email)).thenReturn(messages);

    // Then
    this.mvc
        .perform(get("/messages/users/" + email + "/")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.*", Matchers.hasSize(2)))
        .andExpect(jsonPath("$.[1].dateCreation", is("10-12-2019")))
        .andDo(print());
  }
}
