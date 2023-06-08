package com.example.googleonetaplogin

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.PopupWindow
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.BeginSignInRequest.PasswordRequestOptions
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import java.lang.Exception
import java.util.Arrays
import kotlin.concurrent.thread
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    var tvLogin: TextView? = null
    var tvSignup: TextView? = null
    private var spinner: MaxSpinner? = null
    private var cardView: CardView? = null

    private var oneTapClient: SignInClient? = null
    private var signInRequest: BeginSignInRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        oneTapClient = Identity.getSignInClient(this);
        signInRequest = BeginSignInRequest.builder()
            .setPasswordRequestOptions(PasswordRequestOptions.builder()
                .setSupported(true)
                .build())
            .setGoogleIdTokenRequestOptions(GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                // Your server's client ID, not your Android client ID.
                .setServerClientId("925104115708-dukd9jr5sssgo3r91gig4pbecork6uaa.apps.googleusercontent.com")
                // Only show accounts previously used to sign in.
                .setFilterByAuthorizedAccounts(false)
                .build())
            // Automatically sign in when exactly one credential is retrieved.
            .setAutoSelectEnabled(false)
            .build();

        tvLogin = findViewById(R.id.tv_login)
        tvLogin?.setOnClickListener {
            showGoogleOneTap()
            //showPopupWidnow()
        }

        tvSignup = findViewById(R.id.tv_signup)
        tvSignup?.setOnClickListener {
            // 구글 회원가입
        }

        spinner = findViewById(R.id.sp_category)
        cardView = findViewById(R.id.my_cardview)
        cardView?.setBackgroundResource(R.drawable.bg_shadow)

        val list = mutableListOf<String>()
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, R.id.tv_spinner, list)
        list.add("사과")
        list.add("귤")
        list.add("바나나")
        list.add("포도")
        list.add("키위")
        list.add("참외")
        list.add("사과")
        list.add("사과")
        list.add("귤")
        list.add("바나나")
        list.add("포도")
        list.add("키위")
        list.add("참외")
        list.add("사과")
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spinner?.adapter = adapter
        spinner?.dropDownVerticalOffset = resources.getDimensionPixelSize(R.dimen.size_50)

        /*val popupWindow = PopupWindow(this)
        val view = layoutInflater.inflate(R.layout.simple_spinner_item, null)
        popupWindow.contentView = view
        popupWindow.width = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow.height = resources.getDimensionPixelSize(R.dimen.size_50)
        popupWindow.showAsDropDown(spinner)*/
    }

    private fun showGoogleOneTap() {
        // 구글 로그인
        signInRequest?.let {
            oneTapClient?.beginSignIn(it)
                ?.addOnSuccessListener(this, OnSuccessListener { result ->
                    try {
                        startIntentSenderForResult(result.pendingIntent.intentSender, 2,
                            null, 0, 0, 0, null)
                    } catch (e: ApiException) {

                    }
                })
                ?.addOnFailureListener(this, OnFailureListener {e ->
                    e.printStackTrace()
                })
        }
    }

    private fun showPopupWidnow() {
        val window = PopupWindow(this)
        val view = layoutInflater.inflate(R.layout.view_card_shadowed, null)
        val clMenu: ConstraintLayout = view.findViewById(R.id.cl_menu)
        val rvList: RecyclerView = view.findViewById(R.id.rv_list)
        val adapter = TmpAdapter(this)

        val list = mutableListOf<String>()
        list.add("사과")
        list.add("귤")
        list.add("바나나")
        list.add("포도")
        list.add("키위")
        list.add("참외")
        list.add("사과")
        list.add("사과")
        list.add("귤")
        list.add("바나나")
        list.add("포도")
        list.add("키위")
        list.add("참외")
        list.add("사과")
        adapter.setDataList(list)
        rvList.adapter = adapter
        clMenu.setBackgroundResource(R.drawable.bg_drop_down_menu)

        window.contentView = view
        window.width = resources.getDimensionPixelSize(R.dimen.size_106)
        window.height = resources.getDimensionPixelSize(R.dimen.size_106)
        window.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg_round))
        window.isOutsideTouchable = false
        window.isFocusable = true
        window.elevation = resources.getDimensionPixelSize(R.dimen.size_4).toFloat()
        window.showAsDropDown(tvLogin)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            2 -> {
                try {
                    val credential = oneTapClient?.getSignInCredentialFromIntent(data)
                    val idToken = credential?.googleIdToken
                    val username = credential?.id
                    val password = credential?.password

                    val verifier = GoogleIdTokenVerifier.Builder(NetHttpTransport(), GsonFactory())
                        .setAudience(Arrays.asList("925104115708-dukd9jr5sssgo3r91gig4pbecork6uaa.apps.googleusercontent.com"))
                        .build()

                    thread(start = true) {
                        try {
                            val token = verifier.verify(idToken)
                            val payload = token?.payload
                        } catch (ex: Exception) {
                            ex.stackTrace
                        }
                    }

                    when {
                        idToken != null -> {

                        }
                        password != null -> {
                            // Got a saved username and password. Use them to authenticate
                            // with your backend.
                        }
                        else -> {
                            // Shouldn't happen.
                        }
                    }
                } catch (e: ApiException) {
                    // ...
                }
            }
        }
    }
}