package me.ibrahimsn.wallet.repository

import android.text.TextUtils
import me.ibrahimsn.wallet.entity.NetworkInfo
import me.ibrahimsn.wallet.util.Constants.ETHEREUM_NETWORK_NAME
import me.ibrahimsn.wallet.util.Constants.ETH_SYMBOL
import me.ibrahimsn.wallet.util.Constants.KOVAN_NETWORK_NAME
import me.ibrahimsn.wallet.util.Constants.ROPSTEN_NETWORK_NAME
import java.util.HashSet
import javax.inject.Inject

class EthereumNetworkRepository @Inject constructor(private val preferencesRepository: PreferencesRepository) {

    // SGPX7HN5MJNWMMYDFUKUW7XTM21EDG2T1N

    private val NETWORKS = arrayOf(NetworkInfo(ETHEREUM_NETWORK_NAME, ETH_SYMBOL,
            "https://mainnet.infura.io/llyrtzQ3YhkdESt2Fzrk",
            "https://api.etherscan.io/api/",
            "https://etherscan.io/", 1, true),

            /*NetworkInfo(CLASSIC_NETWORK_NAME, ETC_SYMBOL,
                    "https://mewapi.epool.io/",
                    "https://classic.trustwalletapp.com",
                    "https://gastracker.io", 61, true),*/

            NetworkInfo(KOVAN_NETWORK_NAME, ETH_SYMBOL,
                    "https://kovan.infura.io/llyrtzQ3YhkdESt2Fzrk",
                    "https://kovan.etherscan.io/api/",
                    "https://kovan.etherscan.io", 42, false),

            NetworkInfo(ROPSTEN_NETWORK_NAME, ETH_SYMBOL,
                    "https://ropsten.infura.io/llyrtzQ3YhkdESt2Fzrk",
                    "https://ropsten.etherscan.io/api/",
                    "https://ropsten.etherscan.io", 3, false))

    private var defaultNetwork = getByName(preferencesRepository.getDefaultNetwork()) ?: NETWORKS[0]
    private val onNetworkChangedListeners = HashSet<OnNetworkChangeListener>()

    fun getAvailableNetworkList(): Array<NetworkInfo> {
        return NETWORKS
    }

    fun setDefaultNetworkInfo(networkInfo: NetworkInfo) {
        defaultNetwork = networkInfo
        preferencesRepository.setDefaultNetwork(defaultNetwork.name)

        for (listener in onNetworkChangedListeners)
            listener.onNetworkChanged(networkInfo)
    }

    fun getDefaultNetwork(): NetworkInfo {
        return defaultNetwork
    }

    private fun getByName(name: String?): NetworkInfo? {
        if (!TextUtils.isEmpty(name))
            for (NETWORK in NETWORKS)
                if (name == NETWORK.name)
                    return NETWORK
        return null
    }

    fun addOnChangeDefaultNetwork(onNetworkChanged: OnNetworkChangeListener) {
        onNetworkChangedListeners.add(onNetworkChanged)
    }

    interface OnNetworkChangeListener {
        fun onNetworkChanged(networkInfo: NetworkInfo)
    }
}