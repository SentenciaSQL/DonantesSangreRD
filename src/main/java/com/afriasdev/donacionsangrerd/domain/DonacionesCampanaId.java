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
public class DonacionesCampanaId implements Serializable {
    private static final long serialVersionUID = 1292406574763568971L;
    @NotNull
    @Column(name = "donacion_id", nullable = false)
    private Integer donacionId;

    @NotNull
    @Column(name = "campana_id", nullable = false)
    private Integer campanaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DonacionesCampanaId entity = (DonacionesCampanaId) o;
        return Objects.equals(this.campanaId, entity.campanaId) &&
                Objects.equals(this.donacionId, entity.donacionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campanaId, donacionId);
    }

}
