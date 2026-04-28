package io.github.ubilby.tasktracker.domain.task

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.assertions.throwables.shouldThrow
import java.util.UUID

class TaskIdTest: FunSpec ({
    test("taskId.generate() calling two times returns two different values") {
        val firstTaskId = TaskId.generate()
        val secondTaskId = TaskId.generate()
        firstTaskId shouldNotBe secondTaskId
    }

    test("fromString preserves uuid value") {
        val original = UUID.randomUUID()
        val parsed = TaskId.fromString(original.toString()).value
        parsed shouldBe original
    }

    test("fromString throws on invalid string") {
        shouldThrow<IllegalArgumentException> {
            TaskId.fromString("not uuid string")
        }
    }

    test("two ids parsed from the same string are equal") {
        val raw = UUID.randomUUID().toString()
        val a = TaskId.fromString(raw)
        val b = TaskId.fromString(raw)
        a shouldBe b
    }
})
