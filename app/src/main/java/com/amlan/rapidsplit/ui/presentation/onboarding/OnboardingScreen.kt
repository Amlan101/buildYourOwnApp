package com.amlan.rapidsplit.ui.presentation.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.amlan.rapidsplit.R
import com.amlan.rapidsplit.domain.model.OnboardingPage
import com.amlan.rapidsplit.ui.theme.RapidoBlack
import com.amlan.rapidsplit.ui.theme.RapidoLightGrey
import com.amlan.rapidsplit.ui.theme.RapidoYellow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    navController: NavHostController,
    viewModel: OnboardingViewModel = get()
) {
    val onboardingPages = listOf(
        OnboardingPage(
            image = R.drawable.onboarding_bike_taxi,
            title = "Quick Bike Taxis",
            description = "Get around the city quickly with our reliable bike taxi service. Beat the traffic and reach your destination on time."
        ),
        OnboardingPage(
            image = R.drawable.onboarding_auto,
            title = "Affordable Auto Rides",
            description = "Need more space? Take an auto for your travel needs at budget-friendly prices and enjoy a comfortable ride."
        ),
        OnboardingPage(
            image = R.drawable.onboarding_split_fare,
            title = "Split Fares with Friends",
            description = "Sharing your ride? Split the fare easily with friends and make group travel more affordable with our FuelSplit feature."
        )
    )

    val pagerState = rememberPagerState(pageCount = { onboardingPages.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Skip button at the top right
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            if (pagerState.currentPage < onboardingPages.size - 1) {
                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(onboardingPages.size - 1)
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Text(
                        text = "Skip",
                        color = RapidoBlack,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        // Pager content
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            pageSpacing = 16.dp
        ) { page ->
            OnboardingPageContent(onboardingPages[page])
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Page indicators
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(onboardingPages.size) { iteration ->
                    val color = if (pagerState.currentPage == iteration) RapidoYellow else RapidoLightGrey
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(if (pagerState.currentPage == iteration) 10.dp else 8.dp)
                    )
                }
            }

            // Next/Continue buttons
            AnimatedBottomButtons(
                pagerState = pagerState,
                pageCount = onboardingPages.size,
                onNextClick = {
                    coroutineScope.launch {
                        if (pagerState.currentPage < onboardingPages.size - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                onContinueClick = {
                    viewModel.onContinueClick(navController)
                }
            )
        }
    }
}

@Composable
fun OnboardingPageContent(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = page.title,
            modifier = Modifier
                .size(280.dp)
                .padding(bottom = 32.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = page.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = RapidoBlack
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = RapidoBlack.copy(alpha = 0.7f),
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimatedBottomButtons(
    pagerState: PagerState,
    pageCount: Int,
    onNextClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = pagerState.currentPage < pageCount - 1,
            enter = fadeIn() + slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(300, easing = FastOutSlowInEasing)
            ),
            exit = fadeOut() + slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(300, easing = FastOutSlowInEasing)
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Back button (only show after first page)
                if (pagerState.currentPage > 0) {
                    OutlinedButton(
                        onClick = {
                            // Go back one page
                            onNextClick()
                        },
                        modifier = Modifier
                            .width(120.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        border = null
                    ) {
                        Text("Back", fontSize = 16.sp)
                    }
                } else {
                    Spacer(modifier = Modifier.width(120.dp))
                }

                // Next button
                Button(
                    onClick = onNextClick,
                    modifier = Modifier
                        .width(120.dp)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RapidoYellow),
                    shape = RoundedCornerShape(24.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    Text("Next", color = RapidoBlack, fontSize = 16.sp)
                }
            }
        }

        // Continue button
        AnimatedVisibility(
            visible = pagerState.currentPage == pageCount - 1,
            enter = fadeIn() + slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(300, easing = FastOutSlowInEasing)
            ),
            exit = fadeOut() + slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(300, easing = FastOutSlowInEasing)
            )
        ) {
            Button(
                onClick = onContinueClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RapidoYellow),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("Let's Get Started", color = RapidoBlack, fontSize = 18.sp)
            }
        }
    }
}