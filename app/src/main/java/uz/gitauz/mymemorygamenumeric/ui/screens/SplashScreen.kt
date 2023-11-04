package uz.gitauz.mymemorygamenumeric.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gitauz.mymemorygamenumeric.R

@SuppressLint("CustomSplashScreen")
class SplashScreen :Fragment(R.layout.splashscreen) {

    private val handler by lazy { Handler(Looper.getMainLooper()!!) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

       handler.postDelayed(
           { findNavController().navigate(R.id.action_splashScreen_to_levelScreen) },
           1000)
    }
}