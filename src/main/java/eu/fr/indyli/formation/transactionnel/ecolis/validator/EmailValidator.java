package eu.fr.indyli.formation.transactionnel.ecolis.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IUtilisateurService;
import eu.fr.indyli.formation.business.entity.Utilisateur;
import eu.fr.indyli.formation.business.utils.EcolisConstantes;

public class EmailValidator implements ConstraintValidator<EmailChecker, String>{

	private Pattern pattern;
	private Matcher matcher;
	
	@Resource(name = EcolisConstantes.EcolisConstantesService.USER_SERVICE_KEY)
	private IUtilisateurService userService = null;
	 public EmailValidator(){
	 }

	private static final String EMAIL_PATTERN =
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public void initialize(EmailChecker arg0) {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	public boolean isValid(String emailToValid, ConstraintValidatorContext arg1) {
		Utilisateur userProp;
		try {
			userProp = this.userService.findByEmail(emailToValid);
			matcher = pattern.matcher(emailToValid);
			return (matcher.matches() && userProp != null);
		} catch (EcolisBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
