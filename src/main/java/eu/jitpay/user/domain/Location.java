package eu.jitpay.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
@Table(name="location")
public class Location {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created_on",updatable = false)
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name = "latitude")
    public Double latitude;

    @Column(name = "longitude")
    public Double longitude;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

}
