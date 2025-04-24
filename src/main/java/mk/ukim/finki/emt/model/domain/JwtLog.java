package mk.ukim.finki.emt.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.security.JwtConstants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//nov endpoint kaj sho ke se vrakja username token issued at i expires at
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jwt_logs")
public class JwtLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private String issuedAt;
    private String expiresAt;

    public JwtLog(String token, String issuedAt) {
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiresAt = LocalDateTime.parse(issuedAt).plusNanos(JwtConstants.EXPIRATION_TIME).format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
