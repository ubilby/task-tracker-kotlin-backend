package io.github.ubilby.tasktracker.domain.task

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class TaskTest : FunSpec({
    test("create returns task with NEW status") {
        val text = "Test text"
        val taskCommandResult = Task.create(text)
        taskCommandResult.task.status shouldBe TaskStatus.NEW
    }

    test("create returns task with getting text") {
        val text = "Create a task"
        val taskCommandResult = Task.create(text)
        taskCommandResult.task.text shouldBe text
    }

    test("create returns task with getting date") {
        val text = "Create a task"
        val date = LocalDate.now().plusDays(1)
        val taskCommandResult = Task.create(text, date)
        taskCommandResult.task.dueDate shouldBe date
    }

    test("task date should has null date") {
        val text = "Create a task"
        val taskCommandResult = Task.create(text)
        taskCommandResult.task.dueDate shouldBe null
    }

    test("create produces a single TaskCreated event with task id") {
        val text = "Test text"
        val taskCommandResult = Task.create(text)
        taskCommandResult.events.size shouldBe 1
        taskCommandResult.events[0].shouldBeInstanceOf<TaskCreated>()
        taskCommandResult.events[0].taskId shouldBe taskCommandResult.task.id
    }

    test("empty text throws exception") {
        val text = ""
        shouldThrow<IllegalArgumentException> {
            Task.create(text)
        }
    }

    test("greater than 1000 symbols text throws exception") {
        val text = "Test text".repeat(112)
        shouldThrow<IllegalArgumentException> {
            Task.create(text)
        }
    }

    test("date in past throws exception") {
        val text = "Test text"
        val yesterday = LocalDate.now().minus(1, ChronoUnit.DAYS)
        shouldThrow<IllegalArgumentException> {
            Task.create(text, yesterday)
        }
    }
})
