package com.santiagotorres.convertcurrenciesapp.ui.converter

import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.text.DecimalFormat


class ConverterViewModel : ViewModel() {
    private val factores: Map<String, Double> = mapOf(
        "USD to COP" to 4848.28,
        "USD to EUR" to 0.94,
        "USD to JPY" to 136.41,
        "USD to GBP" to 0.83,

        "COP to USD" to 0.00021,
        "COP to EUR" to 0.00019,
        "COP to JPY" to 0.028,
        "COP to GBP" to 0.00017,

        "EUR to USD" to 1.06,
        "EUR to COP" to 5162.81,
        "EUR to JPY" to 145.21,
        "EUR to GBP" to 0.89,

        "JPY to USD" to 0.0073,
        "JPY to COP" to 35.54,
        "JPY to EUR" to 0.0069,
        "JPY to GBP" to 0.0061,

        "GBP to USD" to 1.2,
        "GBP to COP" to 5821.89,
        "GBP to EUR" to 1.13,
        "GBP to JPY" to 163.79,
    )

    private val df = DecimalFormat("###,###,###.##")

    val emptyText: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val total: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val divisas: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val resultado: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }
    val textoFinal: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


    fun verificarValor (currencieInput1: LinearLayout, currencieInput: TextInputEditText) {

        if (currencieInput.text?.isEmpty() == true){
            emptyText.value = "Ingrese un valor"
            total.value = 0

            Snackbar.make(currencieInput1,
                emptyText.value.toString(),
                Snackbar.LENGTH_INDEFINITE).setAction("Aceptar") {
            }.show()
        }
        else{
            total.value = currencieInput.text.toString().toInt()
        }
    }

    fun verificarDivisas (first_currencie: String, second_currencie: String, currencieInput1: LinearLayout) {

        if (first_currencie == second_currencie) {
            divisas.value = "Seleccione divisas diferentes"
            Snackbar.make(
                currencieInput1, divisas.value.toString(),
                Snackbar.LENGTH_INDEFINITE
            ).setAction("Aceptar") {}.show()
        }

        if (first_currencie == "Escoja una Divisa" || second_currencie == "Escoja una Divisa"){

            divisas.value = "Escoja la divisa faltante"
            Snackbar.make(currencieInput1, divisas.value.toString(),
                Snackbar.LENGTH_INDEFINITE).setAction("Aceptar"){}.show()

        }

    }

    fun calcularDivisas(firstCurrencie: String, secondCurrencie: String, valor: Int) {

        if (valor == 0){
            textoFinal.value = ""
        }

        else{

            if (firstCurrencie == "Dolar Estadounidense" && secondCurrencie == "Peso Colombiano") {
                resultado.value = valor * (factores["USD to COP"].toString().toDouble())
                textoFinal.value = "$valor USD son ${df.format(resultado.value)} COP"
            }
            if (firstCurrencie == "Dolar Estadounidense" && secondCurrencie == "Euro") {
                resultado.value = valor * (factores["USD to EUR"].toString().toDouble())
                textoFinal.value = "$valor USD son ${df.format(resultado.value)} EUR"
            }
            if (firstCurrencie == "Dolar Estadounidense" && secondCurrencie == "Yen") {
                resultado.value = valor * (factores["USD to JPY"].toString().toDouble())
                textoFinal.value = "$valor USD son ${df.format(resultado.value)} JPY"
            }
            if (firstCurrencie == "Dolar Estadounidense" && secondCurrencie == "Libra Esterlina") {
                resultado.value = valor * (factores["USD to GBP"].toString().toDouble())
                textoFinal.value = "$valor USD son ${df.format(resultado.value)} GBP"
            }


            if (firstCurrencie == "Peso Colombiano" && secondCurrencie == "Dolar Estadounidense") {
                resultado.value = valor * (factores["COP to USD"].toString().toDouble())
                textoFinal.value = "$valor COP son ${df.format(resultado.value)} USD"
            }
            if (firstCurrencie == "Peso Colombiano" && secondCurrencie == "Euro") {
                resultado.value = valor * (factores["COP to EUR"].toString().toDouble())
                textoFinal.value = "$valor COP son ${df.format(resultado.value)} EUR"
            }
            if (firstCurrencie == "Peso Colombiano" && secondCurrencie == "Yen") {
                resultado.value = valor * (factores["COP to JPY"].toString().toDouble())
                textoFinal.value = "$valor COP son ${df.format(resultado.value)} JPY"
            }
            if (firstCurrencie == "Peso Colombiano" && secondCurrencie == "Libra Esterlina") {
                resultado.value = valor * (factores["COP to GBP"].toString().toDouble())
                textoFinal.value = "$valor COP son ${df.format(resultado.value)} GBP"
            }


            if (firstCurrencie == "Euro" && secondCurrencie == "Dolar Estadounidense") {
                resultado.value = valor * (factores["EUR to USD"].toString().toDouble())
                textoFinal.value = "$valor EUR son ${df.format(resultado.value)} USD"
            }
            if (firstCurrencie == "Euro" && secondCurrencie == "Peso Colombiano") {
                resultado.value = valor * (factores["EUR to COP"].toString().toDouble())
                textoFinal.value = "$valor EUR son ${df.format(resultado.value)} COP"
            }
            if (firstCurrencie == "Euro" && secondCurrencie == "Yen") {
                resultado.value = valor * (factores["EUR to JPY"].toString().toDouble())
                textoFinal.value = "$valor EUR son ${df.format(resultado.value)} JPY"
            }
            if (firstCurrencie == "Euro" && secondCurrencie == "Libra Esterlina") {
                resultado.value = valor * (factores["EUR to GBP"].toString().toDouble())
                textoFinal.value = "$valor EUR son ${df.format(resultado.value)} GBP"
            }


            if (firstCurrencie == "Yen" && secondCurrencie == "Dolar Estadounidense") {
                resultado.value = valor * (factores["JPY to USD"].toString().toDouble())
                textoFinal.value = "$valor JPY son ${df.format(resultado.value)} USD"
            }
            if (firstCurrencie == "Yen" && secondCurrencie == "Peso Colombiano") {
                resultado.value = valor * (factores["JPY to COP"].toString().toDouble())
                textoFinal.value = "$valor JPY son ${df.format(resultado.value)} COP"
            }
            if (firstCurrencie == "Yen" && secondCurrencie == "Euro") {
                resultado.value = valor * (factores["JPY to EUR"].toString().toDouble())
                textoFinal.value = "$valor JPY son ${df.format(resultado.value)} EUR"
            }
            if (firstCurrencie == "Yen" && secondCurrencie == "Libra Esterlina") {
                resultado.value = valor * (factores["JPY to GBP"].toString().toDouble())
                textoFinal.value = "$valor JPY son ${df.format(resultado.value)} GBP"
            }


            if (firstCurrencie == "Libra Esterlina" && secondCurrencie == "Dolar Estadounidense") {
                resultado.value = valor * (factores["GBP to USD"].toString().toDouble())
                textoFinal.value = "$valor GBP son ${df.format(resultado.value)} USD"
            }
            if (firstCurrencie == "Libra Esterlina" && secondCurrencie == "Peso Colombiano") {
                resultado.value = valor * (factores["GBP to COP"].toString().toDouble())
                textoFinal.value = "$valor GBP son ${df.format(resultado.value)} COP"
            }
            if (firstCurrencie == "Libra Esterlina" && secondCurrencie == "Euro") {
                resultado.value = valor * (factores["GBP to EUR"].toString().toDouble())
                textoFinal.value = "$valor GBP son ${df.format(resultado.value)} EUR"
            }
            if (firstCurrencie == "Libra Esterlina" && secondCurrencie == "Yen") {
                resultado.value = valor * (factores["GBP to JPY"].toString().toDouble())
                textoFinal.value = "$valor GBP son ${df.format(resultado.value)} JPY"
            }
        }
    }
}