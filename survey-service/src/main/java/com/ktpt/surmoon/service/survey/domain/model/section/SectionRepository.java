package com.ktpt.surmoon.service.survey.domain.model.section;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {
    Optional<Section> findOptionalByPreviousSectionId(Long previousSectionId);
}
