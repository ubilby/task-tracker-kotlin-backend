package io.github.ubilby.tasktracker.domain.task

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.Instant

class TaskEventTest: FunSpec(
    {
        test("TaskCreated exposes taskId and occurredAt as TaskEvent") {
            val id = TaskId.generate()
            val now = Instant.now()
            val event: TaskEvent = TaskCreated(taskId = id, occurredAt = now)
            event.taskId shouldBe id
            event.occurredAt shouldBe now
        }

        test("two TaskCreated with same fields are equal") {
            val id = TaskId.generate()
            val now = Instant.now()
            val a = TaskCreated(taskId = id, occurredAt = now)
            val b = TaskCreated(taskId = id, occurredAt = now)
            a shouldBe b
        }
    },
)