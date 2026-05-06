package io.shopwave.ui.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable

data class OnboardingPage(val icon: Int, val title: String, val subtitle: String)

private val pages = listOf(
    OnboardingPage(0, "Browse & Discover", "Explore thousands of products"),
    OnboardingPage(1, "Fast Checkout",     "Save addresses and pay in one tap"),
    OnboardingPage(2, "Track Your Orders", "Real-time updates from dispatch to door"),
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onFinished: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { pages.size })
    HorizontalPager(state = pagerState) { page ->
        OnboardingPageContent(page = pages[page], isLast = page == pages.lastIndex,
                              onGetStarted = onFinished)
    }
}
