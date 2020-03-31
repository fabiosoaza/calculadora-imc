package com.example.calculadoraimc

import android.icu.math.MathContext
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        val dadosImc = dadosImc()
        val imc = calcularImc(dadosImc).toBigDecimal().setScale(2, MathContext.ROUND_HALF_EVEN)
        val textViewResultadoImc = findViewById<TextView>(R.id.labelResultado)
        textViewResultadoImc.text = textViewResultadoImc.text.toString()+" "+imcFormatado(imc)
    }

    private fun calcularImc(dadosImc: DadosImc): Double {
        return  CalculadoraImc().calcular(dadosImc.peso, dadosImc.altura)
    }

    private fun dadosImc(): DadosImc {
        return intent.getParcelableExtra<DadosImc>("dadosImc")
    }

    private fun imcFormatado(imc: BigDecimal?): String? {
        val formatter = DecimalFormat("##.##", DecimalFormatSymbols(Locale.getDefault()))
        return formatter.format(imc)
    }
}
