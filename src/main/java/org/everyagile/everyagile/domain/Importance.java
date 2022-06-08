package org.everyagile.everyagile.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Getter
public enum Importance {
    HIGH("high", "상"),
    LOW("low", "하"),
    DEFAULT("default", "중");

    private final String key;
    private final String title;
}
