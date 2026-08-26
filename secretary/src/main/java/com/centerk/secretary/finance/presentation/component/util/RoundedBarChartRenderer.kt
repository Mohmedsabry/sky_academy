package com.centerk.secretary.finance.presentation.component.util

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.renderer.BarChartRenderer
import com.github.mikephil.charting.utils.ViewPortHandler

class RoundedBarChartRenderer(
    chart: BarChart,
    animator: ChartAnimator,
    viewPortHandler: ViewPortHandler,
    private val radius: Float
) : BarChartRenderer(
    chart,
    animator,
    viewPortHandler
) {

    private val rect = RectF()

    override fun drawDataSet(
        c: Canvas,
        dataSet: IBarDataSet,
        index: Int
    ) {
        val trans = mChart.getTransformer(
            dataSet.axisDependency
        )

        mBarBorderPaint.color = dataSet.barBorderColor
        mBarBorderPaint.style = Paint.Style.STROKE
        mBarBorderPaint.strokeWidth = dataSet.barBorderWidth

        val drawBorder = dataSet.barBorderWidth > 0f

        val phaseX = mAnimator.phaseX
        val phaseY = mAnimator.phaseY

        val barData = mChart.barData

        val barWidth = barData.barWidth

        val buffer = mBarBuffers[index]

        buffer.setPhases(phaseX, phaseY)
        buffer.setDataSet(index)
        buffer.setInverted(
            mChart.isInverted(dataSet.axisDependency)
        )
        buffer.setBarWidth(barWidth)

        buffer.feed(dataSet)

        trans.pointValuesToPixel(buffer.buffer)

        val size = buffer.size()

        for (j in 0 until size step 4) {

            if (!mViewPortHandler.isInBoundsLeft(
                    buffer.buffer[j + 2]
                )
            ) {
                continue
            }

            if (!mViewPortHandler.isInBoundsRight(
                    buffer.buffer[j]
                )
            ) {
                break
            }

            rect.set(
                buffer.buffer[j],
                buffer.buffer[j + 1],
                buffer.buffer[j + 2],
                buffer.buffer[j + 3]
            )

            // Rounded فقط من فوق
            val path = Path().apply {

                val r = minOf(
                    radius,
                    rect.width() / 2f,
                    rect.height() / 2f
                )

                moveTo(rect.left, rect.bottom)

                lineTo(rect.left, rect.top + r)

                quadTo(
                    rect.left,
                    rect.top,
                    rect.left + r,
                    rect.top
                )

                lineTo(rect.right - r, rect.top)

                quadTo(
                    rect.right,
                    rect.top,
                    rect.right,
                    rect.top + r
                )

                lineTo(rect.right, rect.bottom)

                close()
            }

            mRenderPaint.color =
                dataSet.getColor(j / 4)

            c.drawPath(
                path,
                mRenderPaint
            )

            if (drawBorder) {
                c.drawPath(
                    path,
                    mBarBorderPaint
                )
            }
        }
    }
}