package com.ubiqsecurity;

import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigInteger;


import java.util.Arrays;
import ubiqsecurity.fpe.FF1;
import ubiqsecurity.fpe.FF3_1;
import com.ubiqsecurity.UbiqFactory;

import java.util.concurrent.ExecutionException;


import java.util.*;




public class UbiqFPEEncryptTest
{

    @Test
    public void encryptFPE_1() {
        try {
            UbiqCredentials ubiqCredentials = UbiqFactory.readCredentialsFromFile("credentials", "default");

            final byte[] tweekFF1 = {
                (byte)0x39, (byte)0x38, (byte)0x37, (byte)0x36,
                (byte)0x35, (byte)0x34, (byte)0x33, (byte)0x32,
                (byte)0x31, (byte)0x30,
            };
            
            try (UbiqFPEEncryptDecrypt ubiqEncryptDecrypt = new UbiqFPEEncryptDecrypt(ubiqCredentials, 100)) {
                String original = "123-45-6789";
                String cipher = ubiqEncryptDecrypt.encryptFPE(ubiqCredentials, "FFS Name", original, tweekFF1); 
                String decrypted = ubiqEncryptDecrypt.decryptFPE(ubiqCredentials, "FFS Name", cipher, tweekFF1);
            
                assertEquals(original, decrypted);  
            }
    
        } catch (Exception ex) {
            System.out.println(String.format("Exception: %s", ex.getMessage()));
            ex.printStackTrace();
            System.exit(1);
        }    
    }


    @Test
    public void encryptFPE_2() {
        try {
            UbiqCredentials ubiqCredentials = UbiqFactory.readCredentialsFromFile("credentials", "default");

            final byte[] tweekFF1 = {
                (byte)0x39, (byte)0x38, (byte)0x37, (byte)0x36,
                (byte)0x35, (byte)0x34, (byte)0x33, (byte)0x32,
                (byte)0x31, (byte)0x30,
            };
            
            try (UbiqFPEEncryptDecrypt ubiqEncryptDecrypt = new UbiqFPEEncryptDecrypt(ubiqCredentials, 100)) {
                //System.out.println("\nSSN First run");
                String original = "01$23-456-78-90";
                String cipher = ubiqEncryptDecrypt.encryptFPE(ubiqCredentials, "FFS Name", original, tweekFF1); 
                String decrypted = ubiqEncryptDecrypt.decryptFPE(ubiqCredentials, "FFS Name", cipher, tweekFF1);
            
                assertEquals(original, decrypted);  
            }
    
        } catch (Exception ex) {
            System.out.println(String.format("Exception: %s", ex.getMessage()));
            ex.printStackTrace();
            System.exit(1);
        }    
    }





// Disable the masking tests until we decide to utilize the FPEMask module

//     @Test
//     public void testMask1() {
//         
//         String original = "123-45-6789";
//         String regex = "(\\d{3})-(\\d{2})-(\\d{4})";
//         
//         FPEMask mask = new FPEMask(original, regex);
//         System.out.println("original: " + original + "  using regex: " + regex);
//         
//         String cipher = "987654321";  // assume that this is the result of the fpe encrypt for the encryptable part
//         String encryptable = mask.getEncryptablePart();
//         System.out.println("FPEMask determined encryptable part: " + encryptable);
//         System.out.println("Lets assume this 'encrypts' to cipher: " + cipher);
//                 
//         String withInsertion = mask.insertEncryptedPart(cipher);
//         System.out.println("FPEMask applies insertion of cipher: " + withInsertion);
//         
//         String redacted = mask.getRedacted();
//         System.out.println("FPEMask returns redacted: " + redacted);
//         
//         assertEquals(true, true);  // TODO - Determine appropriate test
//     }
// 
// 
// 
//     @Test
//     public void testMask2() {
// 
//         String original = "123-45-6789";
//         String regex = "(\\d{3})-(\\d{2})-\\d{4}";
//         
//         FPEMask mask = new FPEMask(original, regex);
//         System.out.println("original: " + original + "  using regex: " + regex);
//       
//         String encryptable = mask.getEncryptablePart();
//         String cipher = "00000";  // assume that this is the result of the fpe encrypt for the encryptable part
//         System.out.println("FPEMask determined encryptable part: " + encryptable);
//         System.out.println("Lets assume this 'encrypts' to cipher: " + cipher);
//                 
//         String withInsertion = mask.insertEncryptedPart(cipher);
//         System.out.println("FPEMask applies insertion of cipher: " + withInsertion);
//         
//         String redacted = mask.getRedacted();
//         System.out.println("FPEMask returns redacted: " + redacted);
//         
//             
//         assertEquals(true, true);  // TODO - Determine appropriate test
//     }








}
