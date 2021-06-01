package fr.eni.dispocar.tools;

import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Encryption {
	
	//private static final SecureRandom RAND = new SecureRandom();

	static Optional<String> generateSalt (final int length) {

	  if (length < 1) {
	    System.err.println("error in generateSalt: length must be > 0");
	    return Optional.empty();
	  }
	  /*
	  byte[] salt = new byte[length];
	  RAND.nextBytes(salt);
	  System.out.println(Arrays.toString(salt));
	  */
	  //fixed salt
	  byte[] salt = {79, -77, -6, 81, -124, 24, -80, 95, 98, -127, -122, 71, -123, -36, 40, -106, -92, -57, 11, -117, 78, 64, 26, -53, 69, -32, 22, 28, 38, -106, 118, 82, -70, 21, -80, 9, -112, 109, 66, 53, -101, 72, 32, 66, -84, -12, -77, -106, -40, 97, 6, -118, -73, -4, -65, 56, -95, -10, -113, -11, 65, -113, -24, -47, 38, 21, -96, 71, -28, -35, -40, 64, -26, -16, 48, 118, -125, -40, -104, 91, -24, -123, 81, 18, -72, -119, 72, 93, -24, -122, -66, -75, -120, -20, -60, 78, -97, 100, -111, -52, 64, 45, -73, -47, 110, -17, -23, -25, 101, 61, 69, 93, 43, 101, 62, 45, -91, 14, -22, 115, -70, -47, -31, -113, 59, 122, 1, -116, -112, -25, -95, -36, -68, 85, -52, 11, 100, -23, -100, 14, -92, -101, -22, 62, -17, -53, -86, -90, -27, 13, -46, -116, 125, 90, 67, 36, 7, -2, -22, 126, 71, 77, 17, 127, 43, 94, -13, -86, -86, 50, 115, -87, -2, -11, 16, -25, 109, -112, -80, 11, 53, -5, 50, 104, -6, 73, -89, -60, -41, 96, 54, -114, 26, 59, 49, 99, 106, -53, 89, 26, 80, -115, -106, 56, -99, 89, -110, 125, -75, -2, -88, -31, -89, -80, 35, 107, -64, 112, -125, 45, -11, -68, 37, -88, 76, 44, -119, -109, 42, 70, -106, -26, -66, 116, -41, -8, -55, -105, 51, -65, -64, 92, -82, -22, -45, 14, -6, 13, -37, 12, 44, 91, -36, 112, 108, -111, -109, -56, 79, -40, 83, -122, -18, -26, -90, -47, -32, -73, -123, 11, 65, -34, 32, -31, 7, 106, 50, 120, -107, 6, -63, -77, -78, -24, 13, 109, -108, -51, -126, 64, -92, 60, -66, 61, -109, 72, 61, -54, -20, -87, -44, -108, -17, 55, 45, 79, -77, -91, -116, -60, -120, 95, 87, 27, 21, -95, 114, -105, -10, 21, -43, -61, -15, 84, 22, -56, -9, -29, -40, 108, -128, -78, 81, 119, -31, -58, -118, -3, -66, 98, -72, -17, -3, -37, -78, 6, 64, -77, 60, -22, -84, -103, 106, -114, 23, -86, -7, -95, -104, 36, 75, 28, 40, 103, -51, -90, -100, -127, 18, -8, -28, 113, -27, -14, -22, 6, 71, 39, -105, -51, 33, -47, -78, -39, -58, 52, 116, -100, 87, -76, 56, -21, 124, -79, 8, -56, 52, -102, -54, -24, 30, 114, 93, -124, 23, -66, -116, -32, -32, -110, -109, 126, 4, 102, -60, 47, 79, 7, -39, 55, 69, 93, -81, 0, -111, -77, 57, -67, -21, -111, 45, 83, -86, 35, -47, 47, -23, -52, -53, -18, 51, 79, -37, 93, -64, -89, -114, -17, 63, -118, 42, -48, 11, -84, 60, 40, -12, 56, -37, 99, -75, 108, 0, 20, 5, 105, -37, 16, 59, -89, 122, -15, -107, -17, -5, -57, 24, 52, 75, 49, -1, 118, -32, -58, 7, -9, 71, 120, -91, -121, 92, -100, 101, 126, -125, -101, 4, 23, 8, 24, -100, 20, 41, -32, 121, -100, -58, -1, -112, -29, -39, 67};
	  return Optional.of(Base64.getEncoder().encodeToString(salt));
	}
	  
	
	private static final int ITERATIONS = 65536;
	private static final int KEY_LENGTH = 512;
	private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

	static String hashPassword (String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

	  char[] chars = password.toCharArray();
	  byte[] bytes = salt.getBytes();
	  String ecryptedPassword = null;

	  PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

	  Arrays.fill(chars, Character.MIN_VALUE);

	  try {
	    SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
	    byte[] securePassword = fac.generateSecret(spec).getEncoded();
	    ecryptedPassword = Base64.getEncoder().encodeToString(securePassword);

	  } finally {
	    spec.clearPassword();
	  }
	  return ecryptedPassword;
	}

}
