package four33.simpleboard.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	
	private static AES instance = null;
	private String ALGORITHM = "AES";
	private String key = "four33dongqlee!!";
	
	//16 자리 
	private AES(){
		
	}
	
	public static AES getInstance(){
		if(instance == null){
			instance = new AES();
		}
		return instance;
	}
	
	public String encrypt(String str) {
		
		String encrypted = "";
		
		try {
			SecretKeySpec sKeySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
			
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
			
			encrypted = byteArrayToHex(cipher.doFinal(str.getBytes()));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return encrypted;
		
	}
	
	public String decrypt(String str){
		String decrypted = null;
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            decrypted = new String(cipher.doFinal(hexToByteArray(str)));
            return decrypted;
        } 
        catch (Exception e) {
        	System.out.println("decrypt exception");
        }
        return decrypted;
	}
	
	
	private byte[] hexToByteArray(String s) {
        byte[] retValue = null;
        if (s != null && s.length() != 0) {
            retValue = new byte[s.length() / 2];
            for (int i = 0; i < retValue.length; i++) {
                retValue[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
            }
        }
        return retValue;
    }
     
    private String byteArrayToHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        for (int i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }
	
	
	
	
}
