package eu.fr.indyli.formation.transactionnel.ecolis.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IUtilisateurService;
import eu.fr.indyli.formation.business.entity.Utilisateur;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;
import eu.fr.indyli.formation.transactionnel.ecolis.form.PojoUserForm;
import eu.fr.indyli.formation.transactionnel.ecolis.utils.EcolisConstantesWeb.EcolisConstantesURI;

@RestController
@RequestMapping("/users")
public class UtilisateurRestController {

  @Resource(name = EcolisConstantesService.USER_SERVICE_KEY)
  IUtilisateurService userService;
  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  public List<PojoUserForm> findAllUsers() {
    List<Utilisateur> usersList = userService.findAll();
    List<PojoUserForm> msgPojoList = this.convertToPojo(usersList);
    return msgPojoList;
  }



  @GetMapping(value = EcolisConstantesURI.PATH_USER_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> findUser(@PathVariable Integer userId) {
    Utilisateur user;
    PojoUserForm pojo = null;
    try {
      user = this.userService.findById(userId);
      pojo = this.modelMapper.map(user, PojoUserForm.class);
    } catch (EcolisBusinessException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    return ResponseEntity.ok(pojo);
  }

  private List<PojoUserForm> convertToPojo(List<Utilisateur> entityMsgList) {
    List<PojoUserForm> msgPojoList = new ArrayList<PojoUserForm>();
    if (!CollectionUtils.isEmpty(entityMsgList)) {
      for (Utilisateur message : entityMsgList) {
        PojoUserForm pojo = this.modelMapper.map(message, PojoUserForm.class);
        msgPojoList.add(pojo);
      }
    }
    return msgPojoList;
  }
}
