package tgo.lostandfound

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import tgo.lostandfound.screen.user.UserInfoActivity


class SpashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, UserInfoActivity::class.java)
        startActivity(intent)
        finish()
    }
}
