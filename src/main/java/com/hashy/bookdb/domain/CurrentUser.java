package com.hashy.bookdb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class CurrentUser implements Serializable {
    private final String username;
    private final Long userId;
}
