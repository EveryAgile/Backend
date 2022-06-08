package org.everyagile.everyagile.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProjType {
    DEFAUT("default","기본"),
    DEVELOP("develop","개발");

    private final String key;
    private final String title;
}
