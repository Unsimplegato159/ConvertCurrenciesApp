package com.santiagotorres.convertcurrenciesapp.ui.converter

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.santiagotorres.convertcurrenciesapp.R
import com.santiagotorres.convertcurrenciesapp.databinding.ActivityConverterBinding
import java.text.DecimalFormat


class ConverterActivity : AppCompatActivity() {

    private lateinit var converterBinding: ActivityConverterBinding

    private var valor: Int = 0
    private lateinit var firstcurrencie: String
    private lateinit var secondcurrencie: String
    private var resultado: Double = 0.0


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        converterBinding = ActivityConverterBinding.inflate(layoutInflater)
        val view = converterBinding.root
        setContentView(view)

        val spinner: Spinner = findViewById(R.id.select_from_currencie)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.Currencies_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        val spinner2: Spinner = findViewById(R.id.select_to_currencie)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.Currencies_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner2.adapter = adapter
        }

        class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

        //Botón para calcular
        converterBinding.calculateButton.setOnClickListener {

            //Si el campo está vacío, muestra un error
            if (converterBinding.currencieInput.text?.isEmpty() == true){
                valor = 0
                spinner.setSelection(0)
                spinner2.setSelection(0)
                converterBinding.resultText.text = ""
                Snackbar.make(converterBinding.totalView, "Ingrese un valor", Snackbar.LENGTH_INDEFINITE).setAction("Aceptar"){
                    converterBinding.currencieInput.setText("")
                }.show()

            }
            else {

                leerDatos()

                //Map (diccionario) para los factores de conversión dependiendo de las entradas
                val factores: Map<String, Double> = mapOf(
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

                //Verifica si ambas divisas seleccionadas son iguales
                if (firstcurrencie == secondcurrencie) {
                    Snackbar.make(converterBinding.totalView, "Seleccione divisas diferentes",
                    Snackbar.LENGTH_INDEFINITE).setAction("Aceptar"){}.show()
                }
                if (firstcurrencie == "Escoja una Divisa" || secondcurrencie == "Escoja una Divisa"){
                    converterBinding.resultText.text = ""
                    Snackbar.make(converterBinding.totalView, "Escoja una divisa",
                        Snackbar.LENGTH_INDEFINITE).setAction("Aceptar"){}.show()
                }

                val df = DecimalFormat("###,###,###.##")

                // Se hacen todas las comprobaciones.
                if (firstcurrencie == "Dolar Estadounidense" && secondcurrencie == "Peso Colombiano") {
                    resultado = valor * (factores["USD to COP"].toString().toDouble())
                    converterBinding.resultText.text = "$valor USD son ${df.format(resultado)} COP"

                }
                if (firstcurrencie == "Dolar Estadounidense" && secondcurrencie == "Euro") {
                    resultado = valor * (factores["USD to EUR"].toString().toDouble())
                    converterBinding.resultText.text = "$valor USD son ${df.format(resultado)} EUR"
                }
                if (firstcurrencie == "Dolar Estadounidense" && secondcurrencie == "Yen") {
                    resultado = valor * (factores["USD to JPY"].toString().toDouble())
                    converterBinding.resultText.text = "$valor USD son ${df.format(resultado)} JPY"
                }
                if (firstcurrencie == "Dolar Estadounidense" && secondcurrencie == "Libra Esterlina") {
                    resultado = valor * (factores["USD to GBP"].toString().toDouble())
                    converterBinding.resultText.text = "$valor USD son ${df.format(resultado)} GBP"
                }

                if (firstcurrencie == "Peso Colombiano" && secondcurrencie == "Dolar Estadounidense") {
                    resultado = valor * (factores["COP to USD"].toString().toDouble())
                    converterBinding.resultText.text = "$valor COP son ${df.format(resultado)} USD"
                }
                if (firstcurrencie == "Peso Colombiano" && secondcurrencie == "Euro") {
                    resultado = valor * (factores["COP to EUR"].toString().toDouble())
                    converterBinding.resultText.text = "$valor COP son ${df.format(resultado)} EUR"
                }
                if (firstcurrencie == "Peso Colombiano" && secondcurrencie == "Yen") {
                    resultado = valor * (factores["COP to JPY"].toString().toDouble())
                    converterBinding.resultText.text = "$valor COP son ${df.format(resultado)} JPY"
                }
                if (firstcurrencie == "Peso Colombiano" && secondcurrencie == "Libra Esterlina") {
                    resultado = valor * (factores["COP to GBP"].toString().toDouble())
                    converterBinding.resultText.text = "$valor COP son ${df.format(resultado)} GBP"
                }


                if (firstcurrencie == "Euro" && secondcurrencie == "Dolar Estadounidense") {
                    resultado = valor * (factores["EUR to USD"].toString().toDouble())
                    converterBinding.resultText.text = "$valor EUR son ${df.format(resultado)} USD"
                }
                if (firstcurrencie == "Euro" && secondcurrencie == "Peso Colombiano") {
                    resultado = valor * (factores["EUR to COP"].toString().toDouble())
                    converterBinding.resultText.text = "$valor EUR son ${df.format(resultado)} COP"
                }
                if (firstcurrencie == "Euro" && secondcurrencie == "Yen") {
                    resultado = valor * (factores["EUR to JPY"].toString().toDouble())
                    converterBinding.resultText.text = "$valor EUR son ${df.format(resultado)} JPY"
                }
                if (firstcurrencie == "Euro" && secondcurrencie == "Libra Esterlina") {
                    resultado = valor * (factores["EUR to GBP"].toString().toDouble())
                    converterBinding.resultText.text = "$valor EUR son ${df.format(resultado)} GBP"
                }


                if (firstcurrencie == "Yen" && secondcurrencie == "Dolar Estadounidense") {
                    resultado = valor * (factores["JPY to USD"].toString().toDouble())
                    converterBinding.resultText.text = "$valor JPY son ${df.format(resultado)} USD"
                }
                if (firstcurrencie == "Yen" && secondcurrencie == "Peso Colombiano") {
                    resultado = valor * (factores["JPY to COP"].toString().toDouble())
                    converterBinding.resultText.text = "$valor JPY son ${df.format(resultado)} COP"
                }
                if (firstcurrencie == "Yen" && secondcurrencie == "Euro") {
                    resultado = valor * (factores["JPY to EUR"].toString().toDouble())
                    converterBinding.resultText.text = "$valor JPY son ${df.format(resultado)} EUR"
                }
                if (firstcurrencie == "Yen" && secondcurrencie == "Libra Esterlina") {
                    resultado = valor * (factores["JPY to GBP"].toString().toDouble())
                    converterBinding.resultText.text = "$valor JPY son ${df.format(resultado)} GBP"
                }


                if (firstcurrencie == "Libra Esterlina" && secondcurrencie == "Dolar Estadounidense") {
                    resultado = valor * (factores["GBP to USD"].toString().toDouble())
                    converterBinding.resultText.text = "$valor GBP son ${df.format(resultado)} USD"
                }
                if (firstcurrencie == "Libra Esterlina" && secondcurrencie == "Peso Colombiano") {
                    resultado = valor * (factores["GBP to COP"].toString().toDouble())
                    converterBinding.resultText.text = "$valor GBP son ${df.format(resultado)} COP"
                }
                if (firstcurrencie == "Libra Esterlina" && secondcurrencie == "Euro") {
                    resultado = valor * (factores["GBP to EUR"].toString().toDouble())
                    converterBinding.resultText.text = "$valor GBP son ${df.format(resultado)} EUR"
                }
                if (firstcurrencie == "Libra Esterlina" && secondcurrencie == "Yen") {
                    resultado = valor * (factores["GBP to JPY"].toString().toDouble())
                    converterBinding.resultText.text = "$valor GBP son ${df.format(resultado)} JPY"
                }
            }
        }

        //Botón para invertir las divisas
        converterBinding.buttonChange.setOnClickListener {

            val selectionPosition1 = spinner.selectedItemPosition
            val selectionPosition2 = spinner2.selectedItemPosition

            spinner.setSelection(selectionPosition2)
            spinner2.setSelection(selectionPosition1)

            converterBinding.resultText.text = ""
            converterBinding.calculateButton.performClick()
        }
    }

    //Función leer datos
    private fun leerDatos(){
        valor = converterBinding.currencieInput.text.toString().toInt()
        firstcurrencie = converterBinding.selectFromCurrencie.selectedItem.toString()
        secondcurrencie = converterBinding.selectToCurrencie.selectedItem.toString()
    }

}