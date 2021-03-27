package com.ktpt.surmoon.service.survey.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktpt.surmoon.service.survey.application.dto.SectionRequest;
import com.ktpt.surmoon.service.survey.application.dto.SectionResponse;
import com.ktpt.surmoon.service.survey.application.dto.SectionUpdateContentRequest;
import com.ktpt.surmoon.service.survey.application.dto.SectionUpdateSequenceRequest;
import com.ktpt.surmoon.service.survey.domain.model.section.Section;
import com.ktpt.surmoon.service.survey.domain.model.section.SectionRepository;
import com.ktpt.surmoon.service.survey.domain.model.section.SectionSequenceService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SectionService {
    private final SectionRepository sectionRepository;
    private final SectionSequenceService sectionSequenceService;

    @Transactional
    public SectionResponse save(SectionRequest request) {
        Section created = sectionRepository.save(request.toEntity());
        sectionSequenceService.insertSequence(created, request.getPreviousSectionId(), request.getSurveyId());

        return SectionResponse.from(sectionRepository.save(created));
    }
    public SectionResponse updateContent(Long id, SectionUpdateContentRequest request) {
        Section section = findById(id);
        section.updateContent(request.getSurveyId(), request.getTitle(), request.getDescription());

        return SectionResponse.from(sectionRepository.save(section));
    }

    @Transactional
    public SectionResponse updateSequence(Long id, SectionUpdateSequenceRequest request) {
        Section target = findById(id);
        sectionSequenceService.updateSequence(target, request.getPreviousSectionId(), request.getSurveyId());

        return SectionResponse.from(sectionRepository.save(target));
    }

    private Section findById(Long id) {
        return sectionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 section, id : " + id));
    }

    @Transactional
    public void delete(Long id) {
        sectionRepository.deleteById(id);
        // TODO: delete all Items whose sectionId = id
    }
}
