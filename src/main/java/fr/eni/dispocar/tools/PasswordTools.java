/**
 * 
 */
package fr.eni.dispocar.tools;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author sebastien
 *
 */
public class PasswordTools {
	
	/**
	 * Chiffre un mot de passe
	 * @param originalPassword
	 * @return le mot de passe chiffré
	 * @throws UnsupportedEncodingException
	 * @throws GeneralSecurityException
	 */
	public static String encrypt(String originalPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String salt = Encryption.generateSalt(512).get();
		String key = Encryption.hashPassword(originalPassword, salt);
		return key;
	}

}
