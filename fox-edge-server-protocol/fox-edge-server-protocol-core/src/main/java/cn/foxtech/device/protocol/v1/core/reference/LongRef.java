package cn.foxtech.device.protocol.v1.core.reference;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class LongRef {
    private long value = 0;
}
