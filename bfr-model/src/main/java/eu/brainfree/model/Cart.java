package eu.brainfree.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Max on 11.12.2021
 * @project brainfree
 * @package model
 **/

@Builder
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts", uniqueConstraints = {@UniqueConstraint(name = "Unique_User_And_Cart", columnNames = {"user_id", "name"})})
public class Cart {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "name", updatable = false, nullable = false)
    private String name;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_ID"))
    private User user;

}
