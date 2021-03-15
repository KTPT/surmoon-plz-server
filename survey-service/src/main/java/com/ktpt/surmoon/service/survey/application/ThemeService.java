package com.ktpt.surmoon.service.survey.application;

import com.ktpt.surmoon.service.survey.application.dto.ThemeRequest;
import com.ktpt.surmoon.service.survey.application.dto.ThemeResponse;
import com.ktpt.surmoon.service.survey.domain.model.theme.Theme;
import com.ktpt.surmoon.service.survey.domain.model.theme.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    public ThemeResponse save(ThemeRequest request) {
        Theme saved = themeRepository.save(request.toEntity());

        return ThemeResponse.from(saved);
    }

    public ThemeResponse update(Long id, ThemeRequest request) {
        Theme theme = findById(id);
        theme.update(request.getSurveyId(), request.getThumbnail(), request.getMainColor(), request.getBackgroundColor(), request.getFontStyle());

        return ThemeResponse.from(themeRepository.save(theme));
    }

    private Theme findById(Long id) {
        return themeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 theme, id : " + id));
    }
}
