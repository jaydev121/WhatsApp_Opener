package com.example.whatsappopener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var number: String = "0"

        if(intent.action == Intent.ACTION_PROCESS_TEXT){
            number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()

        }

        if(number.isDigitsOnly()){
            startWhatsapp(number)
        }else{
            Toast.makeText(this,"Please Check The number",Toast.LENGTH_SHORT).show()
        }
    }
    private fun startWhatsapp(number: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.setPackage("com.whatsapp")
        var data = if (number[0] == '+') {
            number.substring(1)
        } else {
            number
        }
        data = if (data.length == 10) {
            "91$data"
        } else {
            data
        }
        i.data = Uri.parse("https://wa.me/$data")
        if (packageManager.resolveActivity(i, 0) != null) {
            startActivity(i)
        } else {
            Toast.makeText(this, "Please install Whatsapp!!", Toast.LENGTH_SHORT).show()
        }
        finish()
    }
}
//    private fun startWhatsApp(number: String) {
//        val intent = Intent(Intent.ACTION_VIEW)
//        intent.setPackage("com.whatsapp")
//        if(number[0] == '+'){
//            number.substring(1)
//        }else if(number.length == 10){
//          "91" + number
//        }else{
//            number
//        }
//        intent.data = Uri.parse("https://wa.me/$data")
//        if(packageManager.resolveActivity(intent,0) != null){
//            startActivity(intent)
//        }else{
//            Toast.makeText(this,"Please Install WhatsApp",Toast.LENGTH_SHORT).show()
//        }
//        finish()
//    }
//}