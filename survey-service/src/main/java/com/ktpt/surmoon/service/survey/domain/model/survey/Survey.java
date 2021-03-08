package com.ktpt.surmoon.service.survey.domain.model.survey;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long creatorId;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    // TODO: 2021/03/09 공동 편집자?
    public void updateWhoCreator(String title, Long creatorId) {
        verifyUpdate(title, creatorId);
        this.title = title;
    }

    private void verifyUpdate(String title, Long creatorId) {
        if (!this.creatorId.equals(creatorId)) {
            throw new IllegalArgumentException("수정 권한이 없는 사용자, id : " + creatorId);
        }
        if (this.title.equals(title)) {
            throw new IllegalArgumentException("같은 내용으로 수정할 수 없습니다.");
        }
    }
}
