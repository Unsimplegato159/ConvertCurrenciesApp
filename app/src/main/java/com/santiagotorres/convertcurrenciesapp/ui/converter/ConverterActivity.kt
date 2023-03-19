package com.santiagotorres.convertcurrenciesapp.ui.converter

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.santiagotorres.convertcurrenciesapp.R
import com.santiagotorres.convertcurrenciesapp.databinding.ActivityConverterBinding

//Aplicacion en su versión MVVM
class ConverterActivity : AppCompatActivity() {

    private lateinit var converterBinding: ActivityConverterBinding
    private lateinit var converterViewModel: ConverterViewModel

    private var valor: Int = 0
    private lateinit var firstcurrencie: String
    private lateinit var secondcurrencie: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        converterBinding = ActivityConverterBinding.inflate(layoutInflater)

        converterViewModel = ViewModelProvider(this)[ConverterViewModel::class.java]


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

            val verificarTextObserver = Observer<String> {
                converterBinding.resultText.text = ""
            }
            converterViewModel.emptyText.observe(this, verificarTextObserver)

            val verificarValorObserver = Observer<Int> { result ->
                valor = result
            }
            converterViewModel.total.observe(this, verificarValorObserver)

            val verificarDivisasObserver = Observer<String> {
                converterBinding.resultText.text = ""
            }
            converterViewModel.divisas.observe(this, verificarDivisasObserver)

            val calcularDivisasObserver = Observer<String> {result->
                converterBinding.resultText.text = result
            }
            converterViewModel.textoFinal.observe(this, calcularDivisasObserver)

            leerDatos()
            converterViewModel.verificarValor(converterBinding.totalView, converterBinding.currencieInput)
            converterViewModel.verificarDivisas(firstcurrencie, secondcurrencie, converterBinding.totalView)
            converterViewModel.calcularDivisas(firstcurrencie, secondcurrencie, valor)

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
    private fun leerDatos() {

        firstcurrencie = converterBinding.selectFromCurrencie.selectedItem.toString()
        secondcurrencie = converterBinding.selectToCurrencie.selectedItem.toString()

    }

}