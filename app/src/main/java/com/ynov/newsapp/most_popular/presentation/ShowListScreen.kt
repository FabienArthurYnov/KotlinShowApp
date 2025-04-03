import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ynov.newsapp.most_popular.data.Show
import com.ynov.newsapp.most_popular.presentation.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowListScreen(navController: NavController, viewModel: MainViewModel = viewModel()) {
    // Collect the shows data from the ViewModel
    val shows by viewModel.shows.collectAsState()

    // Display the shows in a LazyColumn
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text("Most Popular Shows") })

        LazyColumn(modifier = Modifier.padding(8.dp)) {
            items(shows) { show ->
                ShowItem(show, onClick = {
                    navController.navigate("show_details/${show.id}")
                })
            }
        }
    }
}

@Composable
fun ShowItem(show: Show, onClick: () -> Unit) {
    Card(modifier = Modifier.padding(8.dp).clickable { onClick() }) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = show.name)
            // Add other show properties here if needed
        }
    }
}

/*
@Composable
fun ShowItem(show: Show) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            // show image with coil
            AsyncImage(
                model = show.image_thumbnail_path, // Image URL or path
                contentDescription = "Show Image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 8.dp)
            )

            // Display the show name
            Column {
                Text(show.name, fontWeight = FontWeight.Bold)
            }
        }
    }
}
*/