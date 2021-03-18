package com.ktpt.surmoon.service.survey.adapter.presentation;

import com.ktpt.surmoon.service.survey.application.ThemeService;
import com.ktpt.surmoon.service.survey.application.dto.ThemeRequest;
import com.ktpt.surmoon.service.survey.application.dto.ThemeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping(ThemeController.THEME_URI)
@RestController
public class ThemeController {
    public static final String THEME_URI = "/api/themes";

    private final ThemeService themeService;

    @PutMapping("/{id}")
    public ResponseEntity<ThemeResponse> update(@PathVariable Long id, @RequestBody @Valid ThemeRequest request) {
        ThemeResponse response = themeService.update(id, request);

        return ResponseEntity.ok(response);
    }
}
