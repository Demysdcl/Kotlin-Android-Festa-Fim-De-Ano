package com.devmasterteam.festafimdeano.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.devmasterteam.festafimdeano.R
import com.devmasterteam.festafimdeano.constants.FimDeAnoConstants
import com.devmasterteam.festafimdeano.util.SecurityPreferences
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // Acesso ao SharedPreferences
    private lateinit var mSecurityPreferences: SecurityPreferences
    private val mViewHolder: ViewHolder = ViewHolder()
    private val mSimpleDateForma = SimpleDateFormat("dd/MM/yyyy")

    /**
     * Tratamento de eventos de click
     * */
    override fun onClick(v: View?) {
        val id = v?.id
        if (id == R.id.button_confirm) {

            val presence = mSecurityPreferences.getString(FimDeAnoConstants.PRESENCE)

            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(FimDeAnoConstants.PRESENCE, presence)

            startActivity(intent)

        }
    }

    /**
     * Função Android que é chamada quando Activity é resumida e se torna visível pro usuário
     * */
    override fun onResume() {
        super.onResume()
        verifyPresence()
    }

    /**
     * Função Android que é chamada quando Activity é criada
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Deixar actionBar mostrando somente ícone
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.ic_launcher)

        // Mapeia elementos de interface
        mViewHolder.textToday = findViewById(R.id.text_today) as TextView
        mViewHolder.textDaysLeft = findViewById(R.id.text_days_left) as TextView
        mViewHolder.buttonConfirm = findViewById(R.id.button_confirm) as Button

        // Atribui eventos
        mViewHolder.buttonConfirm.setOnClickListener(this)

        // Incia variável de acesso a dados
        mSecurityPreferences = SecurityPreferences(this)

        // Atualiza o dia de hoje
        mViewHolder.textToday.text = mSimpleDateForma.format(Calendar.getInstance().time)

        // Cálculo dias restantes
        mViewHolder.textDaysLeft.text = "${getDaysLeftEndOfYear()} dias"

    }

    /**
     * Faz o cálculo de dias restantes para o final do ano
     * */
    private fun getDaysLeftEndOfYear(): String {

        // Calendário de hoje
        val calendarToday = Calendar.getInstance()
        val today = calendarToday.get(Calendar.DAY_OF_YEAR)

        // Calendário do fim do ano
        val calendarEndOfYear = Calendar.getInstance()
        val december31 = calendarEndOfYear.getActualMaximum(Calendar.DAY_OF_YEAR)

        // Faz a diferença entre os dois
        return (december31 - today).toString()

    }

    /**
     * Carrega dados do SharedPreferences e verifica a presença do usuário
     * */
    private fun verifyPresence() {
        val presence = mSecurityPreferences.getString(FimDeAnoConstants.PRESENCE)
        if (presence == "") {
            mViewHolder.buttonConfirm.setText(R.string.nao_confirmado)
        } else if (presence == FimDeAnoConstants.CONFIRM_WILL_GO) {
            mViewHolder.buttonConfirm.setText(R.string.sim)
        } else {
            mViewHolder.buttonConfirm.setText(R.string.nao)
        }
    }

    /**
     * ViewHolder - Agrupa os elementos de interface que são usados na activity
     * */
    private class ViewHolder {
        lateinit var textToday: TextView
        lateinit var textDaysLeft: TextView
        lateinit var buttonConfirm: Button
    }

}