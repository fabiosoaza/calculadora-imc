package com.example.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.text.MessageFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCalcularImc.setOnClickListener(View.OnClickListener {
            val radio = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            val sexo = sexo(radio)
            if(validar()){
                abrirResultado(sexo)
            }
        })

        editIdade.addTextChangedListener(clearErrorMessage(layoutEditIdade))
        editAltura.addTextChangedListener(clearErrorMessage(layoutEditAltura))
        editPeso.addTextChangedListener(clearErrorMessage(layoutEditPeso))

    }

    private fun sexo(radio: RadioButton) =
        if (getString(R.string.checkbox_masculino) == radio.text.toString()) 1 else 2

    private fun abrirResultado(sexo: Int) {
        val dadosImc = DadosImc(
            sexo,
            editIdade.text.toString().toInt(),
            editAltura.text.toString().toDouble(),
            editPeso.text.toString().toDouble()
        )
        val intentResultado = Intent(this, ResultadoActivity::class.java)
        intentResultado.putExtra("dadosImc", dadosImc)
        startActivity(intentResultado)
    }

    private fun validar() : Boolean{
        return validarCampo(editIdade, layoutEditIdade, this.getString(R.string.label_idade))
                && validarCampo(editAltura, layoutEditAltura, this.getString(R.string.label_altura))
                && validarCampo(editPeso, layoutEditPeso, this.getString(R.string.label_peso))
    }

    private fun validarCampo(editText: TextInputEditText, layoutEditText: TextInputLayout, campo: String ): Boolean {
        if (editText.text.toString() == "") {
            layoutEditText.error = formatarMensagem(campo)
            editText.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
            editText.requestFocus()
            return false
        }
        return true
    }

    private fun formatarMensagem(campo:String) : String{
        val message = this.getString(R.string.labelMessageErrorMandatory)
        return  MessageFormat.format(message, campo)
    }

    private fun clearErrorMessage(text: TextInputLayout): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int ) { }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int ) {
                text.error = ""
            }
        }
    }
}
