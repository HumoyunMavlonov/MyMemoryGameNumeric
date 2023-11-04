package uz.gitauz.mymemorygamenumeric.repository

import android.util.Log
import uz.gitauz.mymemorygamenumeric.R
import uz.gitauz.mymemorygamenumeric.data.CardData
import uz.gitauz.mymemorygamenumeric.data.LevelEnum

class AppRepository private constructor() {
    companion object {
        private lateinit var instance: AppRepository

        fun getInstance(): AppRepository {
            if (!(Companion::instance.isInitialized))
                instance = AppRepository()
            return instance
        }
    }

    private val ls = ArrayList<CardData>()

    init {
        ls.add(CardData(1, R.drawable.image_1))
        ls.add(CardData(2, R.drawable.image_2))
        ls.add(CardData(3, R.drawable.image_3))
        ls.add(CardData(4, R.drawable.image_4))
        ls.add(CardData(5, R.drawable.image_5))
        ls.add(CardData(6, R.drawable.image_6))
        ls.add(CardData(7, R.drawable.image_7))
        ls.add(CardData(8, R.drawable.image_8))
        ls.add(CardData(9, R.drawable.image_9))
        ls.add(CardData(10, R.drawable.image_10))
        ls.add(CardData(11, R.drawable.image_11))
        ls.add(CardData(12, R.drawable.image_12))
        ls.add(CardData(13, R.drawable.image_13))
        ls.add(CardData(14, R.drawable.image_14))
        ls.add(CardData(15, R.drawable.image_15))
        ls.add(CardData(16, R.drawable.image_16))
        ls.add(CardData(17, R.drawable.image_17))
        ls.add(CardData(18, R.drawable.image_18))
        ls.add(CardData(19, R.drawable.image_19))
        ls.add(CardData(20, R.drawable.image_20))
        ls.add(CardData(21, R.drawable.image_21))
        ls.add(CardData(22, R.drawable.image_22))
        ls.add(CardData(23, R.drawable.image_23))
        ls.add(CardData(24, R.drawable.image_24))
        ls.add(CardData(25, R.drawable.image_25))
        ls.add(CardData(26, R.drawable.image_26))
        ls.add(CardData(27, R.drawable.image_27))
        ls.add(CardData(28, R.drawable.image_28))
        ls.add(CardData(29, R.drawable.image_29))
        ls.add(CardData(30, R.drawable.image_30))
        ls.add(CardData(31, R.drawable.image_31))
        ls.add(CardData(32, R.drawable.image_32))
        ls.add(CardData(33, R.drawable.image_33))
        ls.add(CardData(34, R.drawable.image_34))
        ls.add(CardData(35, R.drawable.image_35))
        ls.add(CardData(36, R.drawable.image_36))
        ls.add(CardData(37, R.drawable.image_37))
        ls.add(CardData(38, R.drawable.image_38))
        ls.add(CardData(39, R.drawable.image_39))
        ls.add(CardData(40, R.drawable.image_40))
        ls.add(CardData(41, R.drawable.image_41))
        ls.add(CardData(42, R.drawable.image_42))
        ls.add(CardData(43, R.drawable.image_43))
        ls.add(CardData(44, R.drawable.image_44))
        ls.add(CardData(45, R.drawable.image_45))
        ls.add(CardData(46, R.drawable.image_46))
        ls.add(CardData(47, R.drawable.image_47))
        ls.add(CardData(48, R.drawable.image_48))
    }

    fun getCardDataByLevel(level: LevelEnum): List<CardData> {
        val count = level.horCount * level.verCount
        ls.shuffle()
        val result = ArrayList<CardData>(count)
        val l = ls.subList(0, count/2)
        result.addAll(l)
        result.addAll(l)
        result.shuffle()
        for (i in result.indices) {
            Log.d("TTT", "id = ${result[i].id}")
        }
        return result
    }
}