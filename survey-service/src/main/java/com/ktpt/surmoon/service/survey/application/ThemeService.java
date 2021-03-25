package com.ktpt.surmoon.service.survey.application;

import com.ktpt.surmoon.service.survey.application.dto.ThemeDTO;
import com.ktpt.surmoon.service.survey.domain.model.theme.Theme;
import com.ktpt.surmoon.service.survey.domain.model.theme.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    public ThemeDTO.ThemeResponse save(ThemeDTO.ThemeRequest request) {
        Theme saved = themeRepository.save(request.toEntity());

        return ThemeDTO.ThemeResponse.from(saved);
    }

    public ThemeDTO.ThemeResponse update(Long id, ThemeDTO.ThemeRequest request) {
        Theme theme = findById(id);
        theme.update(request.getSurveyId(), request.getThumbnail(), request.getMainColor(), request.getBackgroundColor(), request.getFontStyle());

        return ThemeDTO.ThemeResponse.from(themeRepository.save(theme));
    }

    private Theme findById(Long id) {
        return themeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 theme, id : " + id));
    }
}
