package com.hashy.bookdb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class CurrentUser implements Serializable {
    private final String username;
    private final Long userId;
}
