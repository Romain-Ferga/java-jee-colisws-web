package eu.fr.insee.formation.transactionnel.ecolis.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
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
import eu.fr.indyli.formation.business.ecolis.service.impl.UtilisateurServiceImpl;
import eu.fr.indyli.formation.business.entity.Utilisateur;
import eu.fr.indyli.formation.transactionnel.ecolis.boot.SpringConfigWebApplication;
import eu.fr.indyli.formation.transactionnel.ecolis.utils.EcolisConstantesWeb.EcolisConstantesURI;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringConfigWebApplication.class},
    webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
public class UserRestControllerMockTest {

  @MockBean
  private UtilisateurServiceImpl userService;


  @Autowired
  private MockMvc mvc;

  @Test
  public void itShouldReturnAllTheUsers() throws Exception {
    // When
    when(this.userService.findAll()).thenReturn(null);

    // Then
    this.mvc
        .perform(get(EcolisConstantesURI.PATH_USER)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void itShouldReturnAllGivenUsers() throws Exception {
    // Given
    Utilisateur user = new Utilisateur();
    user.setIdUtilisateur(2);
    user.setCivilite("1");
    user.setName("Le Guene");
    user.setAnneeDeNaissance(1989);
    user.setTelephone("0761705745");
    List<Utilisateur> users = Arrays.asList(user);
    users.add(user);

    // When
    when(this.userService.findAll()).thenReturn(users);

    // Then
    this.mvc
        .perform(get(EcolisConstantesURI.PATH_USER)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json("[{\n" +
            "        \"idUtilisateur\": 2,\n" +
            "        \"civilite\": \"1\",\n" +
            "        \"name\": \"Le Guene\",\n" +
            "        \"anneeDeNaissance\": 1989,\n" +
            "        \"telephone\": \"0761705745\"\n" +
            "    }]"));
  }

  // @Test
  // public void itShouldReturnUsersByUpn() throws Exception {
  // // Given
  // String email = "test";
  //
  // // When
  // when(this.userEngineService.search(email)).thenReturn(null);
  //
  // // Then
  // this.mvc
  // .perform(
  // get(ValirisRestPath.USER + "?email=" + email)
  // // .header("AUTHORIZATION", "Bearer " + accessToken)
  // .contentType(MediaType.APPLICATION_JSON))
  // .andExpect(status().isOk());
  // }

}
