package id.ac.ukdw.final_71190440

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import id.ac.ukdw.final_71190440.databinding.ActivityMainBinding
import id.ac.ukdw.final_71190440.fragments.ProfileFragment
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    //view binding
    private lateinit var binding: ActivityMainBinding

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    //constants
    private companion object{
        private const val RC_SIGN_IN = 100
        private const val TAG = "GOOGLE_SIGN_IN_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root);

        //configure google sign in
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //Google SignIn Button
        binding.googleSignInBtn.setOnClickListener{
            Log.d(TAG, "onCreate: begin Google SignIn")
            val intent = googleSignInClient.signInIntent

            startActivityForResult(intent, RC_SIGN_IN)
        }


    }

    private fun checkUser() {
        //check apakah user login atau tidak
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser!=null){
            //user sudah login
            //start profile activity
            startActivity(Intent(this@MainActivity,ProfileActivity::class.java))
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN){
            Log.d(TAG, "onActivityResult: Google SignIn intent result")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                //sukses sign in
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)
            }catch (e: Exception){
                Log.d(TAG, "onActivityResult: ${e.message}")
            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
        Log.d(TAG, "firebaseAuthWithGoogleAccount: begin firebase auth with google account")

        val credential = GoogleAuthProvider.getCredential(account!!.idToken,null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener { authResult ->
                //sukses login
                Log.d(TAG, "firebaseAuthWithGoogleAccount:  LoggedIn")

                //ambil user login
                val firebaseUser = firebaseAuth.currentUser

                //ambil info user
                val uid =firebaseUser!!.uid
                val email = firebaseUser.email
                val uphoto = firebaseUser.photoUrl
                val displayname = firebaseUser.displayName

                Log.d(TAG, "firebaseAuthWithGoogleAccount: Uid: $uid")
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Email: $email")

                Log.d(TAG, "firebaseAuthWithGoogleAccount: PhotoUrl: $uphoto")
                Log.d(TAG, "firebaseAuthWithGoogleAccount: DisplayName: $displayname")


                //cek apakah baru atau lama
                if (authResult.additionalUserInfo!!.isNewUser){
                    //jika baru, create akun baru
                    Log.d(TAG, "firebaseAuthWithGoogleAccount: Account created... \n$email")
                    Toast.makeText(this@MainActivity, "Account created... \n$email", Toast.LENGTH_SHORT).show()

                }else{
                    //akun sudah ada
                    Log.d(TAG, "firebaseAuthWithGoogleAccount: Existing user... \n$email")
                    Toast.makeText(this@MainActivity, "LoggedIn... \n$email", Toast.LENGTH_SHORT).show()

                }

                //start profile activity
                startActivity(Intent(this@MainActivity,ProfileActivity::class.java))
                finish()
            }
            .addOnFailureListener{ e->
                //login gagal
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Loggin failed due to ${e.message}")
                Toast.makeText(this@MainActivity, "Loggin failed due to ${e.message}", Toast.LENGTH_SHORT).show()


            }


    }
}