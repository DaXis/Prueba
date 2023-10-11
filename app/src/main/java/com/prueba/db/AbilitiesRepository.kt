package com.prueba.db

import com.prueba.utils.PruebaPreferencesManager
import com.prueba.utils.applySchedulers
import io.reactivex.Completable
import javax.inject.Inject

class AbilitiesRepository @Inject constructor(
    private val abilitiesDao: AbilitiesDao,
    private val provider: PruebaPreferencesManager
) {

    fun insertAbility(ability: AbilitiesObj): Completable = Completable.fromCallable {
        abilitiesDao.insertAbility(ability)
    }.applySchedulers()

}