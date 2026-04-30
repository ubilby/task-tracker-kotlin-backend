package io.github.ubilby.tasktracker.domain.task

import java.time.Instant

sealed class TaskEvent {
    abstract val taskId: TaskId
    abstract val occurredAt: Instant
}

data class TaskCreated(
    override val taskId: TaskId,
    override val occurredAt: Instant
) : TaskEvent()

data class TaskCompleted(
    override val taskId: TaskId,
    override val occurredAt: Instant
) : TaskEvent()

data class TaskCancelled(
    override val taskId: TaskId,
    override val occurredAt: Instant
) : TaskEvent()

data class TaskReopened(
    override val taskId: TaskId,
    override val occurredAt: Instant
) : TaskEvent()

data class TaskDeleted(
    override val taskId: TaskId,
    override val occurredAt: Instant
) : TaskEvent()

data class TaskStarted(
    override val taskId: TaskId,
    override val occurredAt: Instant
) : TaskEvent()
