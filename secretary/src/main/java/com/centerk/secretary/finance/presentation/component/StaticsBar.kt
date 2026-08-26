package com.centerk.secretary.finance.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.centerk.secretary.R
import com.centerk.secretary.finance.presentation.component.util.RevenueChartItem
import com.centerk.secretary.finance.presentation.component.util.RoundedBarChartRenderer
import com.centerk.secretary.finance.presentation.component.util.formatMonth
import com.centerk.secretary.finance.presentation.component.util.toPx
import com.centerk.secretary.util.convertToReadableText
import com.core.ui.ChipComponent
import com.core.ui.QuarterMonthsDropDown
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import ir.kaaveh.sdpcompose.sdp
import kotlin.math.roundToInt

@Composable
fun RevenueChart(
    title: String,
    chartItems: List<RevenueChartItem>,
    barColor: Color = MaterialTheme.colorScheme.primary,
    xAxisTextColor: Color = MaterialTheme.colorScheme.tertiary,
    cornerRadius: Dp = 8.dp,
    showValues: Boolean = false,
    monthCount: Int,
    onChangeMonthCount: (Int) -> Unit,
    onChangeShowValus: () -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(220.dp)
) {
    val context = LocalContext.current

    val configuration = LocalConfiguration.current

    val locale = configuration.locales[0]

    val isRtl =
        LocalLayoutDirection.current == LayoutDirection.Rtl
    OutlinedCard(
        enabled = false,
        onClick = {},
        modifier = modifier,
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        ), border = CardDefaults.outlinedCardBorder(false)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.sdp, horizontal = 5.sdp),
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.sdp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary,
                )
                QuarterMonthsDropDown(
                    modifier = Modifier.width(50.sdp),
                    selectedMonth = monthCount,
                    onSelectMonth = onChangeMonthCount
                )
                Spacer(Modifier.weight(1f))
                ChipComponent(
                    text = stringResource(R.string.show_values),
                    isSelected = showValues,
                    onSelect = onChangeShowValus
                )
            }
            Spacer(
                modifier = Modifier.height(8.dp)
            )

            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                factory = {

                    BarChart(context).apply {

                        description.isEnabled = false

                        legend.isEnabled = false

                        setDrawGridBackground(false)

                        setDrawBarShadow(false)

                        setDrawValueAboveBar(showValues)

                        setTouchEnabled(false)

                        setScaleEnabled(false)

                        isDoubleTapToZoomEnabled = false

                        setPinchZoom(false)

                        axisRight.isEnabled = false

                        // Y Axis
                        axisLeft.apply {

                            isEnabled = false

                            setDrawLabels(false)

                            setDrawAxisLine(false)

                            setDrawGridLines(false)

                            axisMinimum = 0f
                        }

                        // X Axis
                        xAxis.apply {

                            position =
                                XAxis.XAxisPosition.BOTTOM

                            setDrawAxisLine(false)

                            setDrawGridLines(false)

                            setDrawLabels(true)

                            granularity = 1f

                            textSize = 9f
                            yOffset = 5f

                            labelRotationAngle = 0f

                            textColor = xAxisTextColor.toArgb()
                        }

                        setExtraOffsets(
                            8f,
                            4f,
                            8f,
                            4f
                        )

                        renderer =
                            RoundedBarChartRenderer(
                                this,
                                animator,
                                viewPortHandler,
                                cornerRadius
                                    .toPx(context)
                            )
                    }
                },

                update = { chart ->

                    if (chartItems.isEmpty()) {

                        chart.clear()

                        return@AndroidView
                    }

                    val displayData =
                        if (isRtl) {
                            chartItems.reversed()
                        } else {
                            chartItems
                        }

                    val entries =
                        displayData.mapIndexed { index, item ->

                            BarEntry(
                                index.toFloat(),
                                item.value
                            )
                        }

                    val dataSet =
                        BarDataSet(
                            entries,
                            ""
                        ).apply {

                            color =
                                barColor.toArgb()

                            setDrawValues(showValues)
                            valueTextSize = 7.5f
                            valueTextColor = xAxisTextColor.toArgb()
                            valueFormatter = object : ValueFormatter() {
                                override fun getBarLabel(
                                    barEntry: BarEntry
                                ): String {
                                    return barEntry.y.toLong().convertToReadableText()
                                }
                            }

                        }

                    val barData =
                        BarData(dataSet).apply {

                            barWidth = 0.7f
                        }

                    chart.data = barData
                    chart.xAxis.apply {
                        setLabelCount(displayData.size, false)
                        axisMinimum = -0.5f
                        axisMaximum = displayData.size - 0.5f
                        valueFormatter = object : ValueFormatter() {

                            override fun getFormattedValue(value: Float): String {

                                val index = value.roundToInt()

                                val item = displayData.getOrNull(index)
                                    ?: return ""
                                return formatMonth(
                                    item.month,
                                    locale
                                )
                            }
                        }
                    }


                    chart.notifyDataSetChanged()

                    chart.invalidate()
                }
            )
        }
    }
}