package com.ktpt.surmoon.service.survey.application;

import com.ktpt.surmoon.service.survey.domain.model.item.question.text.TextQuestionItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TextQuestionItemService {
    private final TextQuestionItemRepository repository;
}
