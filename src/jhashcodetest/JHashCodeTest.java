/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhashcodetest;

import it.dsestili.jhashcode.core.Core;
import java.io.File;
import java.security.MessageDigest;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author davide
 */
public class JHashCodeTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try
        {
            if(args.length != 2)
            {
                    System.out.println("Usage: param 1: file path, param 2: algorithm");
                    return;
            }

            String fileName = args[0];
            String algorithm = args[1];

            if(fileName == null || fileName.isEmpty())
            {
                    System.out.println("Blank file name!");
                    return;
            }

            System.out.println("File name: " + fileName);
            File file = new File(fileName);

            if(!file.exists())
            {
                    System.out.println("File does not exist!");
                    return;
            }

            if(!file.isFile())
            {
                    System.out.println("Is not a file!");
                    return;
            }

            long start = System.currentTimeMillis();
            DigestUtils digestUtils = new DigestUtils(MessageDigest.getInstance(algorithm));
            String hash1 = digestUtils.digestAsHex(file);
            long elapsed = System.currentTimeMillis() - start;
            
            System.out.println("Hash (DigestUtils): " + hash1);
            System.out.println("Hash (DigestUtils): " + elapsed + " ms");

            start = System.currentTimeMillis();
            Core core = new Core(file, algorithm);
            String hash2 = core.generateHash();
            elapsed = System.currentTimeMillis() - start;
            
            System.out.println("Hash (Core): " + hash2);
            System.out.println("Hash (Core): " + elapsed + " ms");
            
            if(hash1.equalsIgnoreCase(hash2))
            {
                System.out.println("Same result!");
            }
            else
            {
                System.out.println("Different result!");
            }
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }
    }
}
