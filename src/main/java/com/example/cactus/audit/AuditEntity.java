package com.example.cactus.audit;

import com.example.cactus.entity.Status;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {

    @CreatedDate
    @Column(columnDefinition = "datetime default current_timestamp not null")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
//    @Column(columnDefinition = "varchar(9) default 'PENDING' not null")
    private Status status = Status.PENDING;

    private Long stemId;

    private String message;

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStemId(Long id){
        this.stemId = id;
    }
}
