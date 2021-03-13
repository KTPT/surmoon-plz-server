package com.ktpt.surmoon.service.survey.adapter.presentation;

import com.ktpt.surmoon.service.survey.application.ThemeService;
import com.ktpt.surmoon.service.survey.application.dto.ThemeRequest;
import com.ktpt.surmoon.service.survey.application.dto.ThemeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RequestMapping(ThemeController.THEME_URI)
@RestController
public class ThemeController {
    public static final String THEME_URI = "/api/themes";

    private final ThemeService themeService;

    @PostMapping
    public ResponseEntity<ThemeResponse> save(@RequestBody @Valid ThemeRequest request) {
        ThemeResponse response = themeService.save(request);

        return ResponseEntity.created(URI.create(THEME_URI + "/" + response.getId()))
                .body(response);
    }
}
