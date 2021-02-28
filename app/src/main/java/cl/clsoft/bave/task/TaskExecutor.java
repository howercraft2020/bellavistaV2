package cl.clsoft.bave.task;

import androidx.annotation.NonNull;

public interface TaskExecutor {
    <T> void async(@NonNull final AppTask<T> task);
}
