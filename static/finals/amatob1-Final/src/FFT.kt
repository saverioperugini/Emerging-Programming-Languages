/***********************************************************
/      filename:  FFT.kt
/
/   description: Class for calculating the FFT
/
/       author:  Benjamin W. Amato
/   Copyright (c) 2019 Benjamin W.  Amato, University of Dayton
/
 **********************************************************/
package FFT
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.math.abs
import kotlin.math.sqrt

data class Complex(var real: Double, var imag: Double){
    operator fun plus(c: Complex): Complex {
        return Complex(real + c.real, imag + c.imag)
    }
    operator fun minus(c: Complex): Complex {
        return Complex(real - c.real, imag - c.imag)
    }
    operator fun times(c: Complex): Complex {
        return Complex(real * c.real - imag * c.imag, real * c.imag + imag * c.real)
    }

    operator fun times(d: Double): Complex {
        return Complex(real * d,imag * d)
    }

    operator fun div(c: Complex): Complex {
        val conj = Complex(c.real, - c.imag)
        val num = Complex(real, imag) * conj
        val denom = c * conj
        return Complex(num.real / denom.real, num.imag / denom.real)
    }

    override fun toString(): String {
        if(imag < 0){
            return "$real - ${abs(imag)}i"
        }
        return "$real + ${imag}i"
    }

    fun getMagnitude(): Double {
        return sqrt(real*real + imag+imag)
    }
}
class FFT {
    companion object {
        fun dftComplex(values: List<Complex>): List<Complex>{
            var result :MutableList<Complex> = MutableList(values.size){Complex(0.0,0.0)}
            for(i in result.indices){
                for(j in values.indices){
                    result[i] += eToNeg2PiITimes(i*j/result.size.toDouble()) * values[j]
                }
            }
            return result
        }


        fun dft(values: ArrayList<Double>): List<Complex> {
            return dftComplex(values.toComplex())
        }

        fun dft(values: List<Double>) : List<Complex> {
            return dftComplex(values.toComplex())
        }

        private fun fftComplex(values: List<Complex>, spawn:Boolean):List<Complex>{

            var result :MutableList<Complex> = MutableList(values.size){Complex(0.0,0.0)}
            //At 32 values, DFT is faster
            if(values.size <= 32){
                return dftComplex(values)
            }
            if(values.size % 2 != 0){
                return dftComplex(values)
            }
            if(spawn && values.size > 20000){
                val even = GlobalScope.async { fftComplex(values.filterIndexed{ index, _ -> index%2 == 0}, true)}
                runBlocking {
                    val odd =  fftComplex(values.filterIndexed{index, _ -> index%2 == 1}, true)
                    for(i in odd.indices) {
                        var secondHalf = i + odd.size
                        result[i] = even.await()[i] + odd[i] * eToNeg2PiITimes(i.toDouble()/values.size)
                        result[secondHalf] = even.await()[i] + odd[i] * eToNeg2PiITimes(secondHalf.toDouble()/values.size)
                    }
                }
                return result
            }else {
                val even = fftComplex(values.filterIndexed { index, _ -> index % 2 == 0 }, false)
                val odd = fftComplex(values.filterIndexed { index, _ -> index % 2 == 1 }, false)
                for (i in odd.indices) {
                    var secondHalf = i + odd.size
                    result[i] = even[i] + odd[i] * eToNeg2PiITimes(i.toDouble() / values.size)
                    result[secondHalf] = even[i] + odd[i] * eToNeg2PiITimes(secondHalf.toDouble() / values.size)
                }
                return result
            }
        }

        fun fft(values: Array<Double>, parallel: Boolean) :List<Complex>{
            return fftComplex(values.toComplex(), parallel)
        }

        fun fft(doubles: DoubleArray, parallel: Boolean): List<Complex> {
            return fftComplex(doubles.toComplex(),parallel)
        }

        fun fft(values: ArrayList<Double>, parallel: Boolean): List<Complex>{
            return fftComplex(values.toComplex(), parallel)
        }

        fun fft(values: List<Double>, parallel: Boolean): List<Complex>{
            return fftComplex(values.toComplex(), parallel)
        }

        fun generateFreqList(numSamples: Int, sampleFreq: Int): List<Double>{
            var freqList = MutableList<Double>(numSamples){0.0}
            val freqPerSample = sampleFreq / numSamples.toDouble()
            val startVal = sampleFreq / -2.0
            for(index in freqList.indices){
                freqList[(index + numSamples / 2)%numSamples] = startVal + index*freqPerSample
            }
            return freqList

        }
        private fun eToNeg2PiITimes(num: Double):Complex{
            val pi = 3.141593654
            // From Euler's Formula
            return Complex(Math.cos(2*pi*num), - Math.sin(2*pi*num))
        }

        fun List<Double>.toComplex():List<Complex>{
            var complexList = MutableList<Complex>(this.size){Complex(0.0,0.0)}
            for((index, value) in this.withIndex()){
                complexList[index] = Complex(value,0.0)
            }
            return complexList
        }

        fun Array<Double>.toComplex():List<Complex>{
            var complexList = MutableList<Complex>(this.size){Complex(0.0,0.0)}
            for((index, value) in this.withIndex()){
                complexList[index] = Complex(value,0.0)
            }
            return complexList
        }

        fun DoubleArray.toComplex():List<Complex>{
            var complexList = MutableList<Complex>(this.size){Complex(0.0,0.0)}
            for((index, value) in this.withIndex()){
                complexList[index] = Complex(value,0.0)
            }
            return complexList
        }
    }
}