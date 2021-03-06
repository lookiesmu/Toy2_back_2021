package team2.study_project.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BasicClass {

    @CreatedDate
    @Column
    private LocalDate createdDate;

    @CreatedBy
    @Column
    private String createdBy;

    @LastModifiedDate
    @Column
    private LocalDate lastModifiedDate;

    @LastModifiedBy
    @Column
    private String lastModifiedBy;

}
