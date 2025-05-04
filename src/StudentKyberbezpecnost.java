import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class StudentKyberbezpecnost extends Student {
    public StudentKyberbezpecnost(int id, String jmeno, String prijmeni, String datumNarozeni) {
        super(id, jmeno, prijmeni, datumNarozeni);
    }

    @Override
    public String specialSkill() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest((jmeno + prijmeni).getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return "Hash error";
        }
    }
}
