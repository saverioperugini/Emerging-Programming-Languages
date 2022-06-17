/***********************************************************
/      filename:  App.kt
/
/   description:  Run examples to test FFT.kt
/
/       author:  Benjamin W. Amato
/   Copyright (c) 2019 Benjamin W.  Amato, University of Dayton
/
 **********************************************************/
package FFT

import java.io.File
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartUtils
import kotlin.system.measureTimeMillis
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import java.awt.Color
import java.lang.Math.pow
import kotlin.math.sin

class App {
}

fun main(args: Array<String>) {
    var music = WavFile.openWavFile(File("test.wav"))
    var numFrames: Int = music.numFrames.toInt()
    var numChannels = music.numChannels
    val buffer = arrayOf(DoubleArray(1024){0.0}, DoubleArray(1024){0.0})
    var musicArrayList = arrayListOf<Double>()
//    var count = 0
    do
    {
//        count++
        var framesRead = music.readFrames(buffer, 1024)
        musicArrayList.addAll(buffer[0].toList())
    }while (framesRead != 0)
    var temp = musicArrayList.slice(0..1023)
    var fiveHundHz = createSinList(2048, 500, music.sampleRate.toInt())
    var fiveThouHz = createSinList(2048, 5000, music.sampleRate.toInt())
    createFFTGraph(fiveHundHz, music.sampleRate.toInt(),"500hz")
    createFFTGraph(fiveThouHz, music.sampleRate.toInt(),"5000hz")
    createFFTGraph(musicArrayList.slice(0 .. 65535),music.sampleRate.toInt(),"music")
    createFFTGraph(addLists(fiveHundHz, fiveThouHz), music.sampleRate.toInt(), "500+5000hz")
    createWaveform(musicArrayList.slice(0..65535),music.sampleRate.toInt(),"music_wave")
    createWaveform(addLists(fiveHundHz, fiveThouHz),music.sampleRate.toInt(),"5500hz_wave")
    var count = 2
    while(count<12){
        compareFFTandDFT(musicArrayList.slice(0..pow(2.0,count.toDouble()).toInt()-1))
        count++
    }
    count = 10
    while(count<19){
        compareConcurrent(musicArrayList.slice(0..pow(2.0,count.toDouble()).toInt()-1))
        count++
    }

}

fun compareFFTandDFT(data: List<Double>){
    println("Size: ${data.size} FFT Time: ${measureTimeMillis {FFT.fft(data, true)}}")
    println("Size: ${data.size} DFT Time: ${measureTimeMillis {FFT.dft(data)}}")
}

fun compareConcurrent(data: List<Double>){
    println("Size: ${data.size} FFT Non-Concurrent Time: ${measureTimeMillis {FFT.fft(data, false)}}")
    println("Size: ${data.size} FFT Concurrent Time: ${measureTimeMillis {FFT.fft(data, true)}}")
}

fun createFFTGraph(values: List<Double>, sampleRate: Int, name: String){
    val fftResult = FFT.fft(values, true)
    val freqList = FFT.generateFreqList(values.size,sampleRate)
    val fftMag = fftResult.getMagnitude()
    var dataset = XYSeriesCollection()
    var xSeries = XYSeries("data")
    for((index, value) in fftMag.withIndex()){
        xSeries.add(freqList[index], value)
    }
    dataset.addSeries(xSeries)
    val chart = ChartFactory.createXYLineChart(name, "Frequency (Hz)", "", dataset)
    chart.setBackgroundPaint(Color.WHITE)
    var pic = File(name +".png")
    ChartUtils.saveChartAsPNG(pic, chart,500,500)

}

fun createWaveform(values: List<Double>, sampleRate: Int, name: String){
    var dataset = XYSeriesCollection()
    var xSeries = XYSeries("data")
    for((index, value) in values.withIndex()){
        xSeries.add(1/sampleRate.toDouble()*index, value)
    }
    dataset.addSeries(xSeries)
    val chart = ChartFactory.createXYLineChart(name, "Time(s)", "", dataset)
    chart.setBackgroundPaint(Color.WHITE)
    var pic = File(name +".png")
    ChartUtils.saveChartAsPNG(pic, chart,500,500)

}
fun List<Complex>.getMagnitude(): List<Double>{
    var magList = MutableList<Double>(this.size){0.0}
    for((index, value) in this.withIndex()){
        magList[index] = value.getMagnitude()
    }
    return magList
}

fun createSinList(length: Int, freq: Int, sampleFreq: Int):  List<Double>{
    var wave = MutableList<Double>(length){0.0}
    for(index in wave.indices){
        wave[index] = sin(2*3.14159*index*freq/sampleFreq)
    }
    return wave
}

fun addLists(first: List<Double>, second:List<Double>): List<Double>{
    if(first.size != second.size){
        return first
    }
    var outList = MutableList<Double>(first.size){0.0}
    for((index, value) in second.withIndex()){
        outList[index] =  value + first[index]
    }
    return outList
}