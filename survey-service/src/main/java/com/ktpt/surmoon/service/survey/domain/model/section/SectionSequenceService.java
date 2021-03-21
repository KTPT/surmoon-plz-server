package com.ktpt.surmoon.service.survey.domain.model.section;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ktpt.surmoon.service.survey.application.dto.SectionRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SectionSequenceService {
    private final SectionRepository sectionRepository;

    public void updateSequence(Section target, Long previousSectionId, Long surveyId) {
        Section atDestination = findByPreviousSectionId(previousSectionId);
        Optional<Section> afterTarget = sectionRepository.findOptionalByPreviousSectionId(target.getId());

        afterTarget.ifPresent(it ->
            sectionRepository.save(it.updatePreviousSectionId(target.getPreviousSectionId(), surveyId)));
        sectionRepository.save(atDestination.updatePreviousSectionId(target.getId(), surveyId));
        target.updatePreviousSectionId(previousSectionId, surveyId);
    }

    public Section insertSequence(SectionRequest request) {
        Section section = sectionRepository.save(request.toEntity(-1L));

        sectionRepository.findOptionalByPreviousSectionId(request.getPreviousSectionId())
            .ifPresent(it -> it.updatePreviousSectionId(section.getId(), request.getSurveyId()));

        return section.updatePreviousSectionId(request.getPreviousSectionId(), request.getSurveyId());
    }

    private Section findByPreviousSectionId(Long previousSectionId) {
        return sectionRepository.findOptionalByPreviousSectionId(previousSectionId)
            .orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 section, previousSectionId : " + previousSectionId));
    }
}
