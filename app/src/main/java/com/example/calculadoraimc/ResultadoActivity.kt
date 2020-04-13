package com.example.calculadoraimc

import android.icu.math.MathContext
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_resultado.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        doImc()
        btnVoltar.setOnClickListener(
            View.OnClickListener {
                finish()
            }

        );

    }

    private fun doImc() {
        val imc = calcularImc(dadosImc()).toBigDecimal().setScale(2, MathContext.ROUND_HALF_EVEN)
        atualizarResultadoImc(imc)
    }


    private fun atualizarResultadoImc(imc: BigDecimal) {
        textViewResultado.text = imcFormatado(imc)
        textViewResultado.contentDescription = getString(R.string.label_resultado)+" "+imcFormatado(imc)
        textViewClassificacao.text = classificacao(imc)
        textViewClassificacao.contentDescription = getString(R.string.label_classificacao)+" "+classificacao(imc)
    }

    private fun classificacao(imc: BigDecimal): String {
        return if (imc < toBigDecimal("18.5")) {
            getString(R.string.label_magreza)
        } else if (imc >= toBigDecimal("18.5") && imc < toBigDecimal("25")) {
            getString(R.string.label_normal)
        } else if (imc >= toBigDecimal("25") && imc < toBigDecimal("30")) {
            getString(R.string.label_sobrepeso)

        } else if (imc >= toBigDecimal("30") && imc < toBigDecimal("40")) {
            getString(R.string.label_obesidade)

        } else {
            getString(R.string.label_obesidade_grave)
        }
    }

    private fun calcularImc(dadosImc: DadosImc): Double {
        return  CalculadoraImc().calcular(dadosImc.peso, dadosImc.altura)
    }

    private fun dadosImc(): DadosImc {
        return intent.getParcelableExtra<DadosImc>("dadosImc")
    }

    private fun toBigDecimal(valor:String):BigDecimal{
        return BigDecimal(valor).setScale(2, RoundingMode.HALF_DOWN)
    }

    private fun imcFormatado(imc: BigDecimal): String {
        val formatter = DecimalFormat("##.##", DecimalFormatSymbols(Locale.getDefault()))
        return formatter.format(imc)
    }
}
