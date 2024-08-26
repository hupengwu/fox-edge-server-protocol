package cn.foxtech.common.utils.redis.list;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 伪redis列表：提供一个redis风格的内存队列
 */
public class RedisLikeSycService {
    private final List list = new CopyOnWriteArrayList();

    private final long maxSize = 128L;

    public void push(Object value) {
        synchronized (this.list) {
            if (value == null || this.list.size() > this.maxSize) {
                this.list.notify();
                return;
            }

            this.list.add(value);
            this.list.notify();
        }
    }

    public Object pop(long timeout, TimeUnit unit) {
        try {
            synchronized (this.list) {
                if (this.list.isEmpty()) {
                    this.list.wait(unit.toMillis(timeout));
                }

                return this.list.remove(0);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isBlock() {
        return this.list.size() > this.maxSize;
    }

    public boolean isBusy(int percentage) {
        percentage = percentage > 100 ? 100 : percentage;
        percentage = percentage < 10 ? 10 : percentage;

        return this.list.size() > this.maxSize * percentage / 100;
    }
}
