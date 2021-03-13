package com.ktpt.surmoon.service.survey.domain.model.theme;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long surveyId;
    private String thumbnail;
    private String mainColor;
    private String backgroundColor;
    private String fontStyle;

    public void update(Long surveyId, String thumbnail, String mainColor, String backgroundColor, String fontStyle) {
        verifyUpdate(surveyId);
        this.surveyId = surveyId;
        this.thumbnail = thumbnail;
        this.mainColor = mainColor;
        this.backgroundColor = backgroundColor;
        this.fontStyle = fontStyle;
    }

    private void verifyUpdate(Long surveyId) {
        if (!this.surveyId.equals(surveyId)) {
            throw new IllegalArgumentException("잘못된 surveyId, surveyId : " + surveyId);
        }
    }
}
