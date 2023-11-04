package uz.gitauz.mymemorygamenumeric.ui.screens

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gitauz.mymemorygamenumeric.R
import uz.gitauz.mymemorygamenumeric.data.CardData
import uz.gitauz.mymemorygamenumeric.data.LevelEnum
import uz.gitauz.mymemorygamenumeric.databinding.ScreenGameBinding
import uz.gitauz.mymemorygamenumeric.ui.viewmodel.factory.GameViewModelFactory
import uz.gitauz.mymemorygamenumeric.ui.viewmodel.impl.GameViewModelImpl
import uz.gitauz.mymemorygamenumeric.utils.closeCard
import uz.gitauz.mymemorygamenumeric.utils.hideCard
import uz.gitauz.mymemorygamenumeric.utils.openCard

class GameScreen : Fragment(R.layout.screen_game) {
    private var cardsMatched = 0
    private val binding: ScreenGameBinding by viewBinding(ScreenGameBinding::bind)
    private val viewModel by lazy {
        ViewModelProvider(this, GameViewModelFactory())[GameViewModelImpl::class.java]
    }
    private var level = LevelEnum.EASY
    private var _h = 0
    private var _w = 0
    private val images = ArrayList<ImageView>()
    private var firstOpen = -1
    private var secondOpen = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        level = requireArguments().getSerializable("data") as LevelEnum

        binding.container.post {
            _h = binding.container.height / level.horCount
            _w = binding.container.width / level.verCount
            viewModel.loadCardByLevel(level)
        }

        viewModel.cardsLiveData.observe(viewLifecycleOwner, cardsObserver)
    }

    private val cardsObserver = Observer<List<CardData>> {
        for (i in 0 until level.verCount) {
            for (j in 0 until level.horCount) {
                val imageView = ImageView(requireContext())
//                imageView.setPadding(14,14,14,14)
                binding.container.addView(imageView)
                val lp = imageView.layoutParams as ConstraintLayout.LayoutParams
                lp.apply {
                    width = _w-10
                    height = _h-10
                }
                lp.setMargins(4, 4,4,4)
//                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                imageView.x = i * _w * 1f
                imageView.y = j * _h * 1f
//                imageView.setPadding(5,5,5,5)
                imageView.layoutParams = lp
                imageView.tag = it[i * level.horCount + j]
//                imageView.setImageResource(it[i * level.horCount + j].imageRes)
                imageView.setImageResource(R.drawable.image_back)
//                imageView.animate()
//                    .x(i * _w * 1f)
//                    .y(j * _h * 1f)
//                    .setDuration(3000)
//                    .start()
                images.add(imageView)
            }
        }
        setClickEventToImages()
    }

    private fun setClickEventToImages() {
        images.forEachIndexed { index, view ->
            view.setOnClickListener {
                if (firstOpen == -1 && index != firstOpen) {
                    firstOpen = index
                    view.openCard()
                } else if (secondOpen == -1 && index != secondOpen && index != firstOpen) {
                    secondOpen = index
                    view.openCard {
                        check()
                    }
                }
            }
        }
        binding.reload.setOnClickListener {
            images.clear()
            binding.container.removeAllViews()
            level = requireArguments().getSerializable("data") as LevelEnum
            binding.container.post {
                _h = binding.container.height / level.horCount
                _w = binding.container.width / level.verCount
                viewModel.loadCardByLevel(level)
            }
            viewModel.cardsLiveData.observe(viewLifecycleOwner, cardsObserver)
            cardsMatched = 0
        }
    }

    private fun check() {
        if ((images[firstOpen].tag as CardData).id == (images[secondOpen].tag as CardData).id){
            images[firstOpen].hideCard()
            images[secondOpen].hideCard {
                cardsMatched += 2
                firstOpen = -1
                secondOpen = -1
                    Log.d("TTT", "check: ${images.size}")
                    Log.d("TTT", "check:${cardsMatched}")

                if (cardsMatched == images.size) {
                    showWinDialog()
                    Log.d("TTT", "check: win")
                }
            }
        } else {
            images[firstOpen].closeCard()
            images[secondOpen].closeCard {
                firstOpen = -1
                secondOpen = -1
            }
        }
    }


    @SuppressLint("MissingInflatedId")
    private fun showWinDialog() {
        val winDialog = AlertDialog.Builder(requireContext())
            .create()
        val view = layoutInflater.inflate(R.layout.win_dialogue,null)
        val menu = view.findViewById<ImageView>(R.id.menudialog)
        val restart = view.findViewById<ImageView>(R.id.loadrestart)
        winDialog.setView(view)
        menu.setOnClickListener {
            activity?.onBackPressed()
            winDialog.dismiss()
        }
        restart.setOnClickListener {
            images.clear()
            binding.container.removeAllViews()
            level = requireArguments().getSerializable("data") as LevelEnum
            binding.container.post {
                _h = binding.container.height / level.horCount
                _w = binding.container.width / level.verCount
                viewModel.loadCardByLevel(level)
            }
            viewModel.cardsLiveData.observe(viewLifecycleOwner, cardsObserver)
            cardsMatched = 0
            winDialog.dismiss()
        }
        winDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        winDialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        winDialog.setCanceledOnTouchOutside(false)
        winDialog.show()
    }

}