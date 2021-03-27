package com.ktpt.surmoon.service.survey.domain.model.section;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SectionSequenceService {
    private final SectionRepository sectionRepository;

    public void insertSequence(Section created, Long previousSectionId, Long surveyId) {
        sectionRepository.findOptionalByPreviousSectionId(previousSectionId)
            .ifPresent(it -> it.updatePreviousSectionId(created.getId(), surveyId));

        created.updatePreviousSectionId(previousSectionId, surveyId);
    }

    public void updateSequence(Section target, Long previousSectionId, Long surveyId) {
        Section atDestination = findByPreviousSectionId(previousSectionId);
        Optional<Section> afterTarget = sectionRepository.findOptionalByPreviousSectionId(target.getId());

        afterTarget.ifPresent(it ->
            sectionRepository.save(it.updatePreviousSectionId(target.getPreviousSectionId(), surveyId)));
        sectionRepository.save(atDestination.updatePreviousSectionId(target.getId(), surveyId));
        target.updatePreviousSectionId(previousSectionId, surveyId);
    }

    private Section findByPreviousSectionId(Long previousSectionId) {
        return sectionRepository.findOptionalByPreviousSectionId(previousSectionId)
            .orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 section, previousSectionId : " + previousSectionId));
    }
}
