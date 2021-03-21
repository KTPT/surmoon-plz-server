package com.ktpt.surmoon.service.survey.application;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public SectionResponse updateContent(Long id, SectionRequest request) {
        Section section = findById(id);
        section.updateContent(request.getSurveyId(), request.getTitle(), request.getDescription());

        return SectionResponse.from(sectionRepository.save(section));
    }

    @Transactional
    public SectionResponse updateSequence(Long id, SectionRequest request) {
        Section section = findById(id);

        Section sectionWhosePreviousSectionIdIsSameWithPreviousSectionIdOfRequest = findByPreviousSectionId(
            request.getPreviousSectionId())
            .orElseThrow(() -> new IllegalArgumentException(
                "존재하지 않는 section, previousSectionId : " + request.getPreviousSectionId()));

        Optional<Section> sectionWhosePreviousSectionIdIsSameWithId = findByPreviousSectionId(id);

        sectionWhosePreviousSectionIdIsSameWithId.ifPresent(value -> sectionRepository.save(value
            .updatePreviousSectionId(request.getSurveyId(), section.getPreviousSectionId())));

        sectionRepository.save(
            sectionWhosePreviousSectionIdIsSameWithPreviousSectionIdOfRequest.updatePreviousSectionId(
                request.getSurveyId(), id));

        section.updatePreviousSectionId(request.getSurveyId(), request.getPreviousSectionId());

        return SectionResponse.from(sectionRepository.save(section));
    }

    private Section findById(Long id) {
        return sectionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 section, id : " + id));
    }

    private Optional<Section> findByPreviousSectionId(Long previousSectionId) {
        return sectionRepository.findByPreviousSectionId(previousSectionId);
    }
}
