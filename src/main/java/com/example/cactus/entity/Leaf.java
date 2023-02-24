package com.example.cactus.entity;

import com.example.cactus.audit.AuditEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@NoArgsConstructor
@Getter @DynamicInsert @DynamicUpdate
public class Leaf extends AuditEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String code;

    public Leaf(String code) {
        this.code = code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
