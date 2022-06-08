@file:Suppress("MemberVisibilityCanBePrivate", "SpellCheckingInspection")

package com.deflatedpickle.undulation.builder

import com.deflatedpickle.marvin.builder.Builder
import org.apache.logging.log4j.LogManager
import java.awt.Window
import javax.swing.ProgressMonitor

open class ProgressMonitorBuilder(
    open val window: Window,
    var progress: Int = 0,
) : Builder<ProgressMonitor> {
    val logger = LogManager.getLogger()

    sealed interface ProgressStep {
        var note: String
    }

    data class BasicProgressStep(
        override var note: String = "",
        var task: (Any?) -> Any = { },
    ) : ProgressStep

    data class ReferenceProgressStep(
        override var note: String = "",
        var task: (ProgressMonitorBuilder, Any?) -> Any = { _, _ -> },
    ) : ProgressStep

    private val steps = mutableListOf<ProgressStep>()

    private var title = "Progress"
    fun title(thing: String): ProgressMonitorBuilder {
        title = thing
        return this
    }

    fun queue(thing: BasicProgressStep.() -> Unit) = this.also { steps.add(BasicProgressStep().apply(thing)) }
    fun queuwu(thing: ReferenceProgressStep.() -> Unit) = this.also { steps.add(ReferenceProgressStep().apply(thing)) }

    override fun build(): Builder<ProgressMonitor> {
        val monitor = ProgressMonitor(
            window,
            title,
            "",
            progress,
            steps.size,
        ).apply {
            millisToPopup = 0
            millisToDecideToPopup = 0
        }

        var last: Any? = null

        // This needs to run in a thread or the progress bar will never update
        Thread {
            var index = 0
            while (index <= steps.size - 1) {
                val i = steps[index]
                monitor.note = "${i.note}..."
                logger.debug("Moved to the ${i.note} step")

                last = when (i) {
                    is BasicProgressStep -> i.task(last)
                    is ReferenceProgressStep -> i.task(this, last)
                }

                monitor.setProgress(++progress)
                index++
                Thread.sleep(50)
            }
        }.start()

        return this
    }
}