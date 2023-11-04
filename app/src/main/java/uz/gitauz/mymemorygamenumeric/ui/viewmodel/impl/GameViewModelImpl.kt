package uz.gitauz.mymemorygamenumeric.ui.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gitauz.mymemorygamenumeric.data.CardData
import uz.gitauz.mymemorygamenumeric.data.LevelEnum
import uz.gitauz.mymemorygamenumeric.repository.AppRepository
import uz.gitauz.mymemorygamenumeric.ui.viewmodel.GameViewModel

class GameViewModelImpl(private val repository: AppRepository) : ViewModel(), GameViewModel {
    override val cardsLiveData = MutableLiveData<List<CardData>>()

    override fun loadCardByLevel(level: LevelEnum) {
        cardsLiveData.value = repository.getCardDataByLevel(level)
    }
}