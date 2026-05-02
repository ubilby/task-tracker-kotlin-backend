package io.github.ubilby.tasktracker.domain.task

data class TaskCommandResult(
    val task: Task,
    val events: List<TaskEvent>,
)
