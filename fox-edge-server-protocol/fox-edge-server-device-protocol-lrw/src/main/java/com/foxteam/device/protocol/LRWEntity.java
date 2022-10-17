package com.foxteam.device.protocol;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class LRWEntity {
    private byte cmd = 0;

    private byte[] dat = new byte[0];
}
