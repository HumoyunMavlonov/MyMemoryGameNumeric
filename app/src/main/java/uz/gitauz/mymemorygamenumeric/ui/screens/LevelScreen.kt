package uz.gitauz.mymemorygamenumeric.ui.screens

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gitauz.mymemorygamenumeric.R
import uz.gitauz.mymemorygamenumeric.data.LevelEnum
import uz.gitauz.mymemorygamenumeric.databinding.ScreenLevelBinding

class LevelScreen : Fragment(R.layout.screen_level) {
    private val binding: ScreenLevelBinding by viewBinding(ScreenLevelBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.easy.setOnClickListener { openGameScreen(LevelEnum.EASY) }
        binding.medium.setOnClickListener { openGameScreen(LevelEnum.MEDIUM) }
        binding.hard.setOnClickListener { openGameScreen(LevelEnum.HARD) }
        binding.about.setOnClickListener { findNavController().navigate(R.id.action_levelScreen_to_aboutScreen)}
    }

    private fun openGameScreen(level: LevelEnum) {
        findNavController().navigate(
            R.id.action_levelScreen_to_gameScreen, bundleOf(
                "data" to level
            )
        )
    }
}



