package io.github.ubilby.tasktracker.domain.task

import java.util.UUID

@JvmInline
value class TaskId private constructor(val value: UUID) {
    companion object {
        fun generate(): TaskId = TaskId(UUID.randomUUID())
        fun fromString(value: String): TaskId = TaskId(UUID.fromString(value))
    }
}
