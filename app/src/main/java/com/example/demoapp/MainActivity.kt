package com.example.demoapp

import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                sendEmail()
            }

        }
    }

    private fun sendEmail() {
        val hostEmail = "sendemaildemo04@gmail.com"
        val hostPass = "svzycdokussdnpli"
        val props = Properties()
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"
        props["mail.smtp.host"] = "smtp.gmail.com"
        props["mail.smtp.port"] = "587"

        val session = Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(hostEmail, hostPass)
            }
        })
        try {
            val message: Message = MimeMessage(session)
            message.setFrom(InternetAddress(hostEmail))
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("yoneya.ohm1221@gmail.com"))
            message.subject = "sending email"
            message.setText("messageToSend")
            Transport.send(message)
        } catch (e: MessagingException) {
        }
    }

}