package me.ibrahimsn.wallet.ui.wallet

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.fragment_wallet.*
import me.ibrahimsn.wallet.R
import me.ibrahimsn.wallet.base.BaseFragment
import me.ibrahimsn.wallet.ui.home.HomeActivity
import javax.inject.Inject

class WalletFragment : BaseFragment<HomeActivity>() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: WalletViewModel

    override fun layoutRes(): Int {
        return R.layout.fragment_wallet
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity, viewModelFactory).get(WalletViewModel::class.java)

        val walletAdapter = WalletAdapter()
        val transactionAdapter = TransactionAdapter()

        rvTransactions.layoutManager = LinearLayoutManager(activity)
        rvTransactions.adapter = transactionAdapter

        viewModel.wallets.observe(this, Observer {
            if (it != null)
                walletAdapter.setItems(it)
        })

        viewModel.transactions.observe(this, Observer {
            if (it != null)
                transactionAdapter.setItems(it)
        })

        /*viewModel.walletBalance.observe(this, Observer {
            if (it != null)
                Log.d("###", "Wallet: ${it.first.name} Balance: ${it.second}")
        })*/
    }
}