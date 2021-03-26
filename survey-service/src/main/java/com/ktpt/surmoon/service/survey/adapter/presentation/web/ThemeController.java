package com.ktpt.surmoon.service.survey.adapter.presentation.web;

import com.ktpt.surmoon.service.survey.application.ThemeService;
import com.ktpt.surmoon.service.survey.application.dto.ThemeDTO;
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
    public ResponseEntity<ThemeDTO.ThemeResponse> update(@PathVariable Long id, @RequestBody @Valid ThemeDTO.ThemeRequest request) {
        ThemeDTO.ThemeResponse response = themeService.update(id, request);

        return ResponseEntity.ok(response);
    }
}
