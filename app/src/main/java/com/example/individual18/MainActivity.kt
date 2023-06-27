package com.example.individual18

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


class MainActivity : AppCompatActivity() {
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var etIntValue: EditText
    private lateinit var etTextValue: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var etFloatValue: EditText
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button
    private lateinit var tvSavedIntValue: TextView
    private lateinit var tvSavedTextValue: TextView
    private lateinit var tvSavedBoolValue: TextView
    private lateinit var tvSavedFloatValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar los elementos de vista
        etIntValue = findViewById(R.id.etIntValue)
        etTextValue = findViewById(R.id.etTextValue)
        radioGroup = findViewById(R.id.radioGroup)
        etFloatValue = findViewById(R.id.etFloatValue)
        btnSave = findViewById(R.id.btnSave)
        btnDelete = findViewById(R.id.btnDelete)
        tvSavedIntValue = findViewById(R.id.tvSavedIntValue)
        tvSavedTextValue = findViewById(R.id.tvSavedTextValue)
        tvSavedBoolValue = findViewById(R.id.tvSavedBoolValue)
        tvSavedFloatValue = findViewById(R.id.tvSavedFloatValue)

        // Inicializar el PreferenceManager
        preferenceManager = PreferenceManager(getSharedPreferences("MyPreferences", MODE_PRIVATE))

        // Mostrar los datos guardados en las preferencias
        showSavedPreferences()

        // Configurar el botón Guardar
        btnSave.setOnClickListener {
            savePreferences()
        }

        // Configurar el botón Borrar
        btnDelete.setOnClickListener {
            deletePreferences()
        }
    }

    private fun savePreferences() {
        // Obtener los valores ingresados por el usuario
        val intValue = etIntValue.text.toString()
        val textValue = etTextValue.text.toString()
        val boolValue = radioGroup.checkedRadioButtonId == R.id.rbValueTrue
        val floatValue = etFloatValue.text.toString()

        // Validar los campos vacíos
        if (intValue.isEmpty() || textValue.isEmpty() || floatValue.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()
            return
        }

        // Validar los valores ingresados
        if (!Utils.isValidInteger(intValue)) {
            Toast.makeText(this, "Ingrese un número entero válido.", Toast.LENGTH_SHORT).show()
            return
        }

        if (!Utils.isValidText(textValue)) {
            Toast.makeText(this, "Ingrese un texto válido (solo letras).", Toast.LENGTH_SHORT).show()
            return
        }

        if (!Utils.isValidFloat(floatValue)) {
            Toast.makeText(this, "Ingrese un número decimal válido.", Toast.LENGTH_SHORT).show()
            return
        }

        // Guardar los valores en las preferencias
        preferenceManager.saveIntValue(intValue.toInt())
        preferenceManager.saveTextValue(textValue)
        preferenceManager.saveBoolValue(boolValue)
        preferenceManager.saveFloatValue(floatValue.toFloat())

        // Mostrar los datos guardados en las preferencias
        showSavedPreferences()

        // Limpiar los campos de entrada
        etIntValue.text.clear()
        etTextValue.text.clear()
        etFloatValue.text.clear()

        // Mostrar mensaje de éxito
        Toast.makeText(this, "Datos guardados correctamente.", Toast.LENGTH_SHORT).show()
    }

    private fun deletePreferences() {
        // Eliminar todas las preferencias
        preferenceManager.deletePreferences()

        // Mostrar los datos guardados en las preferencias (vacíos)
        showSavedPreferences()

        // Mostrar mensaje de éxito
        Toast.makeText(this, "Preferencias borradas correctamente.", Toast.LENGTH_SHORT).show()
    }

    private fun showSavedPreferences() {
        // Obtener los datos guardados en las preferencias
        val intValue = preferenceManager.getIntValue()
        val textValue = preferenceManager.getTextValue()
        val boolValue = preferenceManager.getBoolValue()
        val floatValue = preferenceManager.getFloatValue()

        // Mostrar los datos en los TextViews
        tvSavedIntValue.text = "Número entero: $intValue"
        tvSavedTextValue.text = "Texto: $textValue"
        tvSavedBoolValue.text = "Valor booleano: $boolValue"
        tvSavedFloatValue.text = "Número decimal: $floatValue"
    }
}