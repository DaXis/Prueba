package com.prueba.db

import com.prueba.utils.PruebaPreferencesManager
import com.prueba.utils.applySchedulers
import io.reactivex.Completable
import javax.inject.Inject

class UserDbRepository @Inject constructor(
    private val userDao: UserDao,
    private val provider: PruebaPreferencesManager
) {

    fun insertUser(userObj: UserObj): Completable = Completable.fromCallable {
        userDao.insertUser(userObj)
    }.applySchedulers()

    fun getUserById() = userDao.getUserById("")
        .map {
            it
        }.applySchedulers()
}