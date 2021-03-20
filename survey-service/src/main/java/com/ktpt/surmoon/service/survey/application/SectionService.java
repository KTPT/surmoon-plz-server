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

    @Transactional
    public SectionResponse update(Long id, SectionRequest request) {
        Section section = findById(id);

        if ((section.getPreviousSectionId() == null && request.getPreviousSectionId() == null)
            || section.getPreviousSectionId().equals(request.getPreviousSectionId())) {

            section.updateContent(request.getSurveyId(), request.getTitle(), request.getDescription());
        } else {
            // 옮기려는 위치의 바로 뒷 section
            Section section1 = findByPreviousSectionId(request.getPreviousSectionId())
                .orElseThrow(() -> new IllegalArgumentException(
                    "존재하지 않는 section, previousSectionId : " + request.getPreviousSectionId()));

            // 맨 마지막 section을 이동하는 경우가 아닐 때
            Optional<Section> section2 = findByPreviousSectionId(id);

            if (section2.isPresent()) {
                // Section section2 = findByPreviousSectionId(id).get();
                section2.get().updatePreviousSectionId(request.getSurveyId(), section.getPreviousSectionId());
                sectionRepository.save(section2.get());
            }

            section1.updatePreviousSectionId(request.getSurveyId(), id);
            sectionRepository.save(section1);

            section.updatePreviousSectionId(request.getSurveyId(), request.getPreviousSectionId());
        }

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
