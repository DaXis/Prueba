package com.prueba.db

import com.prueba.utils.applySchedulers
import io.reactivex.Completable
import javax.inject.Inject

class OCRDataRepository @Inject constructor(private val ocrDataDao: OCRDataDao) {

    fun insertOCRData(ocrDataObj: OCRDataObj): Completable = Completable.fromCallable {
        ocrDataDao.insertOCRData(ocrDataObj)
    }.applySchedulers()

    fun getUserDataById(id: String) = ocrDataDao.getUserDataById(id)
        .map {
            it
        }.applySchedulers()

    fun updateUserData(ocr: OCRDataObj) = ocrDataDao.updateUserData(ocr)
        .map {
            it
        }.applySchedulers()
}