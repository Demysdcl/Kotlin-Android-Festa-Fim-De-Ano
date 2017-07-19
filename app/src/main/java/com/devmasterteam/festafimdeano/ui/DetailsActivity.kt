package com.devmasterteam.festafimdeano.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import com.devmasterteam.festafimdeano.R
import com.devmasterteam.festafimdeano.constants.FimDeAnoConstants
import com.devmasterteam.festafimdeano.util.SecurityPreferences

class DetailsActivity : AppCompatActivity(), View.OnClickListener {

    // Acesso ao SharedPreferences
    private lateinit var mSecurityPreferences: SecurityPreferences
    private val mViewHolder: ViewHolder = ViewHolder()

    /**
     * Tratamento de eventos de click
     * */
    override fun onClick(v: View?) {
        val id = v?.id
        if (id == R.id.check_participate) {

            if (mViewHolder.checkConfirm.isChecked) {
                mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRM_WILL_GO)
            } else {
                mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRM_WONT_GO)
            }

        }
    }

    /**
     * Função Android que é chamada quando Activity é criada
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Deixar actionBar mostrando somente ícone
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.ic_launcher)

        // Mapeia elementos de interface
        mViewHolder.checkConfirm = findViewById(R.id.check_participate) as CheckBox

        // Atribui eventos
        mViewHolder.checkConfirm.setOnClickListener(this)

        // Incia variável de acesso a dados
        mSecurityPreferences = SecurityPreferences(this)

        // Carrega dados da activity
        loadDataFromActivity()

    }

    /**
     * Carrega dados recebidos através da Intent
     * */
    private fun loadDataFromActivity() {
        val extras = intent.extras
        if (extras != null) {
            val presence = mSecurityPreferences.getString(FimDeAnoConstants.PRESENCE)
            mViewHolder.checkConfirm.isChecked = (presence == FimDeAnoConstants.CONFIRM_WILL_GO)
        }
    }

    /**
     * ViewHolder - Agrupa os elementos de interface que são usados na activity
     * */
    private class ViewHolder {
        lateinit var checkConfirm: CheckBox
    }
}
