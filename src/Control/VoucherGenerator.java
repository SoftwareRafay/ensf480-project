//Authors: Group 5-L01

package Control;

import java.util.Random;

public class VoucherGenerator {
    public static String generateVoucherCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder voucher = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) { 
            int index = random.nextInt(characters.length());
            voucher.append(characters.charAt(index));
        }
        return voucher.toString();
    }
}

