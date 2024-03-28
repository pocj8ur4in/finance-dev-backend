package finance.dev.domain.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class Date {
    @Column(name = "created_date")
    @CreatedDate
    private java.time.LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private java.time.LocalDateTime modifiedDate;
}
