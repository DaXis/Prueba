package com.prueba.flows.main

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.prueba.R
import com.prueba.common.base.BaseNavActivity
import com.prueba.databinding.ActivityMainBinding
import com.prueba.flows.main.actions.MainActions
import com.prueba.flows.main.interfaces.NextStepListener
import com.prueba.flows.main.viewmodels.MainActivityViewModel
import com.google.android.material.appbar.AppBarLayout
import com.prueba.flows.main.fragments.ExampleFourFragmentDirections
import com.prueba.flows.main.fragments.ExampleTwoThreeFragmentDirections
import com.prueba.flows.main.fragments.MainFragmentDirections
import dagger.android.AndroidInjection.inject
import javax.inject.Inject

class MainActivity : BaseNavActivity(), NextStepListener {

    @Inject
    lateinit var viewModel: MainActivityViewModel

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var content: ContentResolver? = null
    private var appbar: AppBarLayout? = null

    override fun currentNavController() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        inject(this)
        super.onCreate(savedInstanceState)
        content = applicationContext.contentResolver
        setContentView(binding.root)
        bindingViewModel()
        initToolbar()
    }

    private fun bindingViewModel() {
        viewModel.apply {
            getShowErrorMessage().observe(this@MainActivity, Observer(::genToast))
            getShowProgress().observe(this@MainActivity, Observer(::showLoadDialog))
            getAction().observe(this@MainActivity, Observer(::eventListener))
        }
    }

    private fun initToolbar() {
        binding.apply {
            appbar = includeToolbar.appbar
            includeToolbar.toolbar.setOnClickListener {

            }
        }
    }

    private fun eventListener(actions: MainActions) {
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }


    override fun onGoToExampleOne() {
        currentNavController().navigate(
            MainFragmentDirections.actionToExampleOne()
        )
    }

    override fun onGoToExampleTwoThree() {
        currentNavController().navigate(
            MainFragmentDirections.actionToExampleTwoThree()
        )
    }

    override fun onGoToPokeDetail(pokeId: Int) {
        currentNavController().navigate(
            ExampleTwoThreeFragmentDirections.actionToPokeDetail(pokeId)
        )
    }

    override fun onGoToExampleFour() {
        currentNavController().navigate(
            MainFragmentDirections.actionToExampleFour()
        )
    }

    override fun onGoToExampleFourDetail() {
        currentNavController().navigate(
            ExampleFourFragmentDirections.actionForgotToExampleFourDetail()
        )
    }
}