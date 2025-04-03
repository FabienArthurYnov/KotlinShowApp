package com.ynov.newsapp.everything.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ynov.newsapp.everything.presentation.EverythingViewModel
import com.ynov.newsapp.ui.theme.Pink40
import com.ynov.newsapp.ui.theme.PurpleGrey40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EverythingScreen(
    everythingViewModel: EverythingViewModel = hiltViewModel()
) {
    val everythingState by everythingViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        everythingViewModel.getEverything()
    }

    Scaffold(
        containerColor = Pink40,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PurpleGrey40,
                ),
                title = {
                    Text(
                        text = "My Daily News",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif
                    )
                },
            )
        }
    ) { innerPadding ->

        when {
            everythingState.isLoading -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    CircularProgressIndicator()
                }
            }

            everythingState.error != null -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text(
                        text = everythingState.error ?: "Unknown error",
                        color = Color.Red,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            else -> {
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.padding(innerPadding),
                    columns = StaggeredGridCells.Fixed(1),
                    verticalItemSpacing = 10.dp,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items(everythingState.articles.size) { position ->
                        EverythingItem(article = everythingState.articles[position])
                    }
                }
            }
        }
    }
}
