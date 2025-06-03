package com.afriasdev.donacionsangrerd.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ReposicioneId implements Serializable {
    private static final long serialVersionUID = 7892458722173887005L;
    @NotNull
    @Column(name = "solicitud_id", nullable = false)
    private Integer solicitudId;

    @NotNull
    @Column(name = "donacion_id", nullable = false)
    private Integer donacionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReposicioneId entity = (ReposicioneId) o;
        return Objects.equals(this.solicitudId, entity.solicitudId) &&
                Objects.equals(this.donacionId, entity.donacionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(solicitudId, donacionId);
    }

}
