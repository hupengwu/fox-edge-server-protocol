package com.foxteam.device.protocol.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class ShortsRef {
    private short[] value = new short[] {};
}