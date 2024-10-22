package com.example.mylogin5a

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mylogin5a.databinding.ActivityMainRegistrarBinding

class MainActivityRegistrar : AppCompatActivity() {

    private lateinit var bindingRegis: ActivityMainRegistrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingRegis = ActivityMainRegistrarBinding.inflate(layoutInflater)
        setContentView(bindingRegis.root)

        //Para regresar a la principal
        val intentPrinci = Intent(this, MainActivity::class.java)

        bindingRegis.btnCancelar.setOnClickListener {
            startActivity(intentPrinci)
        }

        val dbServicios = DBHelperUsuario(this)
        val db = dbServicios.writableDatabase

        bindingRegis.btnRegistrar.setOnClickListener {
            //Tomamos los valores de las cajas de texto
            val user = bindingRegis.txtNewUsuario.getText().toString()
            val pass = bindingRegis.txtNewPassword.getText().toString()
            val corr = bindingRegis.txtCorreo.getText().toString()
            val nom = bindingRegis.txtNombre.getText().toString()

            //Creamos una variable para colocar llave -> valor
            val newReg = ContentValues()
            newReg.put("userLogin", user)
            newReg.put("userPass", pass)
            newReg.put("userEmail", corr)
            newReg.put("userNombre", nom)

            val res = db.insert("usuarios", null, newReg)

            //Cerramos la BD
            db.close()

            //Evaluamos si se hizo el insert mediante la variable res
            if (res.toInt() == -1) {
                Toast.makeText(this,"No se pudo registrar el usuario", Toast.LENGTH_LONG).show()
            } else {
                //Limpiamos las cajas
                bindingRegis.txtNewUsuario.text.clear()
                bindingRegis.txtNewPassword.text.clear()
                bindingRegis.txtCorreo.text.clear()
                bindingRegis.txtNombre.text.clear()

                Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_LONG).show()
                startActivity(intentPrinci)
            }
        }



    }
}