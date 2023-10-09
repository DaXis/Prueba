package com.prueba.flows.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import com.prueba.common.base.BaseActivity
import com.prueba.databinding.ActivitySplashBinding
import com.prueba.flows.splash.actions.SplashActions
import com.prueba.flows.splash.viewmodels.SplashActivityViewModel
import com.prueba.utils.launchLoginActivity
import dagger.android.AndroidInjection.inject
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: SplashActivityViewModel

    private val binding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindingViewModel()
    }

    private fun bindingViewModel() {
        viewModel.apply {
            getShowErrorMessage().observe(this@SplashActivity, Observer(::genToast))
            getShowProgress().observe(this@SplashActivity, Observer(::showLoadDialog))
            getAction().observe(this@SplashActivity, Observer(::eventListener))
            goToMain()
        }
    }

    private fun eventListener(actions: SplashActions) {
        if (actions is SplashActions.NavigateToMain) {
            launchLoginActivity()
        }
    }

    private companion object {
    }
}