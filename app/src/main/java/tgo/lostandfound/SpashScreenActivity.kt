package tgo.lostandfound

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent



class SpashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
