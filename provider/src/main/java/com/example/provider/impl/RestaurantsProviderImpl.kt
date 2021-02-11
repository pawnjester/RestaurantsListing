package com.example.provider.impl

import android.content.Context
import com.example.data.contracts.provider.RestaurantsProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.InputStream
import java.nio.charset.Charset
import javax.inject.Inject

class RestaurantsProviderImpl @Inject constructor(
    @ApplicationContext val context: Context
) : RestaurantsProvider {

    override suspend fun getRestaurants(): String {
        val inputStream: InputStream = this.context.assets.open("sample.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charset.defaultCharset())
    }
}