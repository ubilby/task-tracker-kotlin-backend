package io.github.ubilby.tasktracker.domain.task

import java.time.Instant
import java.time.LocalDate

@ConsistentCopyVisibility
data class Task private constructor(
    val id: TaskId,
    val text: String,
    val status: TaskStatus,
    val dueDate: LocalDate?
) {
    init {
        require(text.isNotBlank()) { "Task text cannot be empty" }
        require(text.length <= 1000) { "Task text cannot exceed 1000 characters" }
        dueDate?.let {
            require(!it.isBefore(LocalDate.now())) { "Due date cannot be in the past" }
        }
    }

    companion object {
        fun create(text: String, dueDate: LocalDate? = null): TaskCommandResult {
            val id = TaskId.generate()
            val task = Task(
                id = id,
                text = text,
                status = TaskStatus.NEW,
                dueDate = dueDate,
            )
            val event = TaskCreated(taskId = id, occurredAt = Instant.now())
            return TaskCommandResult(task = task, events = listOf(event))
        }
    }
}
