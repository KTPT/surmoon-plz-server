package com.ktpt.surmoon.service.survey.domain.model.common;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ktpt.surmoon.service.survey.domain.model.questionItem.SelectQuestionItem;
import com.ktpt.surmoon.service.survey.domain.model.questionItem.SelectQuestionItemRepository;
import com.ktpt.surmoon.service.survey.domain.model.questionItem.TextQuestionItem;
import com.ktpt.surmoon.service.survey.domain.model.questionItem.TextQuestionItemRepository;

@DataJpaTest
public class SharedIdTest {
    @Autowired
    TextQuestionItemRepository textQuestionItemRepository;

    @Autowired
    SelectQuestionItemRepository selectQuestionItemRepository;

    @Test
    void createSharedIdEntity() {
        TextQuestionItem firstEntity = textQuestionItemRepository.save(new TextQuestionItem());

        SelectQuestionItem secondEntity = selectQuestionItemRepository.save(new SelectQuestionItem());

        assertThat(secondEntity.getId()).isEqualTo(firstEntity.getId() + 1L);
    }
}
