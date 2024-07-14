import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    public static void main(String[] args) {
        String secretKey = "fsociety";
        byte[] secretKeyBytes = Base64.decodeBase64(secretKey);  // Utilisation de commons-codec pour le décodage Base64

        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);
        Date expiration = new Date(currentTimeMillis + 3600 * 1000); // 1 hour

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", 1);

        System.out.println("Creating JWT with secret key: " + secretKey);
        System.out.println("Claims: " + claims);
        System.out.println("Issued at: " + now);
        System.out.println("Expiration: " + expiration);

        try {
            String jwt = Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(expiration)
                    .signWith(SignatureAlgorithm.HS256, secretKeyBytes)  // Utilisation du tableau de bytes du secret key
                    .compact();
            System.out.println("JWT created: " + jwt);
        } catch (Exception e) {
            System.err.println("Error creating JWT: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
