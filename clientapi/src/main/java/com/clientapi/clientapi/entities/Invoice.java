package com.clientapi.clientapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "invoice")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class Invoice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private Double total;

    @Getter @Setter private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @Getter @Setter private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}