package uz.gitauz.mymemorygamenumeric.ui.viewmodel

import androidx.lifecycle.LiveData
import uz.gitauz.mymemorygamenumeric.data.CardData
import uz.gitauz.mymemorygamenumeric.data.LevelEnum

interface GameViewModel {
    val cardsLiveData: LiveData<List<CardData>>

    fun loadCardByLevel(level: LevelEnum)
}

