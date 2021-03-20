package com.ktpt.surmoon.service.survey.application;

import org.springframework.stereotype.Service;

import com.ktpt.surmoon.service.survey.application.dto.SectionRequest;
import com.ktpt.surmoon.service.survey.application.dto.SectionResponse;
import com.ktpt.surmoon.service.survey.domain.model.section.Section;
import com.ktpt.surmoon.service.survey.domain.model.section.SectionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SectionService {
    private final SectionRepository sectionRepository;

    public SectionResponse save(SectionRequest request) {
        Section saved = sectionRepository.save(request.toEntity());
        return SectionResponse.from(saved);
    }
}
