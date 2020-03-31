package com.example.calculadoraimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCalcularImc.setOnClickListener(View.OnClickListener {
            val radio = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            val sexo = if (getString(R.string.checkbox_masculino) == radio.text.toString()) 1 else 2
            val dadosImc = DadosImc(sexo, editIdade.text.toString().toInt(), editAltura.text.toString().toDouble(), editPeso.text.toString().toDouble())
            val intentResultado = Intent(this, ResultadoActivity::class.java)
            intentResultado.putExtra("dadosImc", dadosImc)
            startActivity(intentResultado)
        })
    }
}
