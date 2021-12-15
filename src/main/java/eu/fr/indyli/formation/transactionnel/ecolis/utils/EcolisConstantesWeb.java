package eu.fr.indyli.formation.transactionnel.ecolis.utils;

/**
 * Classe de constantes
 * 
 * @author CHZOME
 *
 */
public class EcolisConstantesWeb {

  public final class EcolisConstantesModelAttribute {
    public final static String LOGIN_MODEL_ATTR_KEY = "loginModelAttribute";
    public final static String ALERTE_MODEL_ATTR_KEY = "alerteModelAttribute";
    public final static String DISP_ALERTE_MODEL_ATTR_KEY = "listeAlerteModelAttribute";
  }

  public final class EcolisConstantesURI {
    public final static String PATH_USER = "/users";
    public final static String PATH_USER_ID = "/{userId}";
    public final static String PATH_MESSAGE = "/messages";
    public final static String PATH_USER_MESSAGE = "/users/{email:.+}/";
  }
}
