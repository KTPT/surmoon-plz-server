package com.ktpt.surmoon.service.survey.adapter.presentation.web;

import static com.ktpt.surmoon.service.survey.adapter.presentation.web.TextQuestionItemController.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktpt.surmoon.service.survey.application.TextQuestionItemService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(TEXT_QUESTION_ITEM_URI)
@RestController
public class TextQuestionItemController {
    public static final String TEXT_QUESTION_ITEM_URI = "/api/items/question/text";

    private final TextQuestionItemService service;
}
