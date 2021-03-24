package com.ktpt.surmoon.service.survey.application.item.question;

import org.springframework.stereotype.Service;

import com.ktpt.surmoon.service.survey.domain.model.item.question.text.TextQuestionItemRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TextQuestionItemService {
    private final TextQuestionItemRepository repository;
}
