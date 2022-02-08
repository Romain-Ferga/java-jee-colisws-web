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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IAlerteService;
import eu.fr.indyli.formation.business.entity.Alerte;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;
import eu.fr.indyli.formation.transactionnel.ecolis.form.PojoModelAlerteForm;
import eu.fr.indyli.formation.transactionnel.ecolis.utils.EcolisConstantesWeb.EcolisConstantesURI;

@RestController
@RequestMapping("/alertes")
public class AlerteRestController {

	@Resource(name = EcolisConstantesService.ALERTE_SERVICE_KEY)
	IAlerteService alertService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<PojoModelAlerteForm> findAllAlerts() {

		List<Alerte> alertsList = alertService.findAll();

		List<PojoModelAlerteForm> msgPojoList = this.convertToPojo(alertsList);

		return msgPojoList;

	}

	@GetMapping(value = EcolisConstantesURI.PATH_ALERTE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAlert(@PathVariable Integer alertId) {

		Alerte alert;

		PojoModelAlerteForm pojo = null;

		try {

			alert = this.alertService.findById(alertId);

			pojo = this.modelMapper.map(alert, PojoModelAlerteForm.class);

		} catch (EcolisBusinessException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

		}

		return ResponseEntity.ok(pojo);

	}

	@GetMapping(value = EcolisConstantesURI.PATH_ALERTE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteAlert(@PathVariable Integer alertId) throws EcolisBusinessException {

		this.alertService.deleteAlerteById(alertId);

	}

	@PostMapping(value = EcolisConstantesURI.PATH_ALERTE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createAlert(@RequestBody Alerte alert) throws EcolisBusinessException {

		Integer newAlertId = this.alertService.createUpdateEntity(alert);

		Alerte newAlert = this.alertService.findById(newAlertId);

		PojoModelAlerteForm pojo = this.modelMapper.map(newAlert, PojoModelAlerteForm.class);

		return ResponseEntity.ok(pojo);

	}

	@PostMapping(value = EcolisConstantesURI.PATH_ALERTE_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateVilleDepartVilleArrivee(@RequestBody Integer alertId,
			@RequestBody String villeDepart, @RequestBody String villeArrivee) throws EcolisBusinessException {

		Alerte alert = this.alertService.findById(alertId);

		alert.setVilleDepart(villeDepart);

		alert.setVilleArrivee(villeArrivee);

		this.alertService.createUpdateEntity(alert);

		PojoModelAlerteForm pojo = this.modelMapper.map(alert, PojoModelAlerteForm.class);

		return ResponseEntity.ok(pojo);

	}

//	@PostMapping(value = EcolisConstantesURI.PATH_ALERTE_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> updateVilleDepart(@RequestBody Integer alertId, @RequestBody String villeDepart)
//			throws EcolisBusinessException {
//
//		Alerte alert = this.alertService.findById(alertId);
//
//		alert.setVilleDepart(villeDepart);
//
//		this.alertService.createUpdateEntity(alert);
//
//		PojoModelAlerteForm pojo = this.modelMapper.map(alert, PojoModelAlerteForm.class);
//
//		return ResponseEntity.ok(pojo);
//
//	}
//
//	@PostMapping(value = EcolisConstantesURI.PATH_ALERTE_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> updateVilleArrivee(@RequestBody Integer alertId, @RequestBody String villeArrivee)
//			throws EcolisBusinessException {
//
//		Alerte alert = this.alertService.findById(alertId);
//
//		alert.setVilleArrivee(villeArrivee);
//
//		this.alertService.createUpdateEntity(alert);
//
//		PojoModelAlerteForm pojo = this.modelMapper.map(alert, PojoModelAlerteForm.class);
//
//		return ResponseEntity.ok(pojo);
//
//	}

	private List<PojoModelAlerteForm> convertToPojo(List<Alerte> entityMsgList) {

		List<PojoModelAlerteForm> msgPojoList = new ArrayList<PojoModelAlerteForm>();

		if (!CollectionUtils.isEmpty(entityMsgList)) {

			for (Alerte alerte : entityMsgList) {

				PojoModelAlerteForm pojo = this.modelMapper.map(alerte, PojoModelAlerteForm.class);

				msgPojoList.add(pojo);

			}

		}

		return msgPojoList;

	}

}
