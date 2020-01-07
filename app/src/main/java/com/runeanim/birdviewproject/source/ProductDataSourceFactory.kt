package com.runeanim.birdviewproject.source

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.runeanim.birdviewproject.model.Product
import javax.inject.Inject
import javax.inject.Provider

class CharactersPageDataSourceFactory(
    val providerDataSource: Provider<ProductDataSource>
) : DataSource.Factory<Int, Product>() {

    var sourceLiveData = MutableLiveData<ProductDataSource>()

    /**
     * Create a DataSource.
     *
     * @return The new DataSource.
     * @see DataSource.Factory.create
     */
    override fun create(): DataSource<Int, Product> {
        val dataSource = providerDataSource.get()
        sourceLiveData.postValue(dataSource)
        return dataSource
    }

    /**
     * Force refresh data source by invalidating and re-create again.
     */
    fun refresh() {
        sourceLiveData.value?.invalidate()
    }

    /**
     * Force retry last fetch operation on data source.
     */
}