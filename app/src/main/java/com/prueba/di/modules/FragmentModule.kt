package com.prueba.di.modules

import com.prueba.flows.main.fragments.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributesFlowManagerFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributesChangePasswordFragment(): ExampleFourDetailFragment

    @ContributesAndroidInjector
    abstract fun contributesConfirmUserFragment(): PokeDetailFragment

    @ContributesAndroidInjector
    abstract fun contributesForgotPasswordFragment(): ExampleFourFragment

    @ContributesAndroidInjector
    abstract fun contributesLoginFragment(): ExampleOneFragment

    @ContributesAndroidInjector
    abstract fun contributesRegisterFragment(): ExampleTwoThreeFragment

}