package uz.gitauz.mymemorygamenumeric.utils

import android.view.View
import android.widget.ImageView
import uz.gitauz.mymemorygamenumeric.R
import uz.gitauz.mymemorygamenumeric.data.CardData


fun ImageView.openCard(finishAnim : () -> Unit = {}) {
    this.animate()
        .setDuration(300)
        .rotationY(89f)
        .withEndAction {
            this.setImageResource((tag as CardData).imageRes)
            this.rotationY = -91f
            this.setPadding(12,12,12,12)
            this.animate()
                .setDuration(300)
                .rotationY(0f)
                .withEndAction {
                    finishAnim.invoke()
                }.start()
        }
        .start()
}


fun ImageView.closeCard(finishAnim : () -> Unit = {}) {
    this.animate()
        .setDuration(500)
        .rotationY(-91f)
        .withEndAction {
            this.setImageResource(R.drawable.image_back)
//            this.setPadding(12,12,12,12)
            this.rotationY = 89f
            this.animate()
                .setDuration(500)
                .rotationY(0f)
                .withEndAction {
                    finishAnim.invoke()
                }.start()
        }
        .start()
}


fun ImageView.hideCard(finishAnim : () -> Unit = {}) {
    this.animate()
        .setDuration(500)
        .scaleX(0f)
        .scaleY(0f)
        .withEndAction {
            this.visibility = View.GONE
            finishAnim.invoke()
        }
        .start()
}