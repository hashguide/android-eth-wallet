package me.ibrahimsn.wallet.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.ibrahimsn.wallet.ui.send.confirm.ConfirmFragment
import me.ibrahimsn.wallet.ui.send.send.SendFragment

@Module
abstract class SendFragmentBuilder {

    /**
     * Build Send Transaction Fragment
     */
    @ContributesAndroidInjector
    internal abstract fun buildSendFragment(): SendFragment

    /**
     * Build Confirm Transaction Fragment
     */
    @ContributesAndroidInjector
    internal abstract fun buildConfirmFragment(): ConfirmFragment
}