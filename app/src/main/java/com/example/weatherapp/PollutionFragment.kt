package com.example.weatherapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.databinding.FragmentPollutionBinding
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class PollutionFragment: Fragment() {
    private var _binding: FragmentPollutionBinding? = null

    private val binding get() = _binding!!

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPollutionBinding.inflate(inflater, container, false)

        val data = arguments
        val pollutants = listOf(
            "co" to "CO",
            "nh3" to "NH3",
            "no" to "NO",
            "no2" to "NO2",
            "o3" to "O3",
            "pm10" to "PM10",
            "pm2_5" to "PM2_5",
            "so2" to "SO2"
        )

        val list = arrayListOf<BarEntry>()
        val txtBuilder = StringBuilder()

        pollutants.forEachIndexed { index, (key,label) ->
            val value = data?.getDouble(key)
            if (value != null) {
                list.add(BarEntry((index + 1).toFloat(), value.toFloat()))
            }
            txtBuilder.append("$label: ${value?: "-"}\n")
        }

        binding.textView.text = txtBuilder.toString()

        val barDataSet = BarDataSet(list, "Pollutants")
        binding.barChart.data = BarData(barDataSet)

        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS,255)
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.barBorderColor = Color.BLACK

        barDataSet.barBorderWidth = 1f

        val barData = BarData(barDataSet)
        binding.barChart.data = barData

        binding.barChart.apply {
            description.text = "Air Pollutants"
                animateY(1000)
        }

        val quarters = arrayOf("", "CO", "NH3", "NO", "NO2", "PM10", "O3", "PM2_5", "SO2")
        val formatter : ValueFormatter= object : ValueFormatter(){
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return quarters[value.toInt()]
            }
        }
        val xAxis = binding.barChart.xAxis
        xAxis.valueFormatter = formatter

        return binding.root
    }
}