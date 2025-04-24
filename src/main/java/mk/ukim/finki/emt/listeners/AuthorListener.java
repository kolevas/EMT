package mk.ukim.finki.emt.listeners;

import jakarta.persistence.*;
import mk.ukim.finki.emt.events.AuthorChangedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthorListener {
    @PersistenceContext
    private EntityManager entityManager;

    @EventListener
    public void handleAuthorChange(AuthorChangedEvent event) {
        entityManager.createNativeQuery("REFRESH MATERIALIZED VIEW authors_per_country")
                .executeUpdate();
    }
}