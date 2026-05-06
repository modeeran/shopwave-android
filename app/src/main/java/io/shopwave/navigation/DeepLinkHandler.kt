package io.shopwave.navigation

import android.net.Uri

sealed class DeepLinkDestination {
    data class Product(val slug: String) : DeepLinkDestination()
    data class Order(val id: String)     : DeepLinkDestination()
    data class Category(val slug: String): DeepLinkDestination()
    object Profile                        : DeepLinkDestination()
}

object DeepLinkHandler {
    fun resolve(uri: Uri): DeepLinkDestination? {
        val segments = uri.pathSegments
        return when (segments.getOrNull(0)) {
            "products"   -> segments.getOrNull(1)?.let { DeepLinkDestination.Product(it) }
            "orders"     -> segments.getOrNull(1)?.let { DeepLinkDestination.Order(it) }
            "categories" -> segments.getOrNull(1)?.let { DeepLinkDestination.Category(it) }
            "profile"    -> DeepLinkDestination.Profile
            else         -> null
        }
    }
}
