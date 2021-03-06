package com.innerclan.v1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false,unique = true)
    @NotNull(message = " promo name can not be empty")
    String name;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date createdOn;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "expiry date can not be empty")
    Date expiryDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "access type can not be empty")
    Access access;

    @NotNull(message = "promo value cannot be null")
    double value;

    @ManyToMany(mappedBy = "promos")
    @JsonIgnore
    Set<Client> clients;

    @Transient
    long usedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Promo)) return false;
        Promo promo = (Promo) o;
        return Objects.equals(getName(), promo.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}




