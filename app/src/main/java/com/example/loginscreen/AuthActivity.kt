package com.example.loginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import kotlin.concurrent.thread

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setup()
    }

    private fun setup()
    {
        signUpButton.setOnClickListener{

            /*if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.text.toString(),passwordEditText.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful) {
                        showHome(it.result?.user?.email ?:"", ProviderType.EmailPassword)
                        txtError.visibility = View.INVISIBLE
                        emailEditText.setText("")
                        passwordEditText.setText("")
                    }else{
                        //showAlert()
                        txtError.visibility = View.VISIBLE
                        txtError.text = " ERROR! Email y/o contraseña invalido"
                        emailEditText.setText("")
                        passwordEditText.setText("")
                    }
                }
            }
            else
            {
                txtError.visibility = View.VISIBLE
                txtError.text = " ERROR! Campos vacíos"
            }
            */
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)

        }

        logInButton.setOnClickListener{
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(),passwordEditText.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful) {
                        showHome(it.result?.user?.email ?:"", ProviderType.EmailPassword)
                        txtError.visibility = View.INVISIBLE
                        emailEditText.setText("")
                        passwordEditText.setText("")
                    }else{
                        //showAlert()
                        txtError.visibility = View.VISIBLE
                        txtError.text = " ERROR! Email y/o contraseña invalido"
                        emailEditText.setText("")
                        passwordEditText.setText("")
                    }
                }
            }
            else
            {
                txtError.visibility = View.VISIBLE
                txtError.text = " ERROR! Campos vacíos"
            }
        }
    }

    private fun showAlert()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("OK", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String,provider: ProviderType) {
        val homeIntent: Intent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
}