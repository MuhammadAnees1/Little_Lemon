package com.aneesferoz.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun DishDetails(id: Int) {
    val dish = requireNotNull(DishRepository.getDish(id))
    val favorite by remember { mutableStateOf(false) }
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // TopAppBar
        TopAppBar(
            title = { Text(text = dish.name) },
            navigationIcon = {
                IconButton(onClick = { /* Handle navigation */ }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            },
            actions = {
                // Add your custom actions here if needed
                IconButton(onClick = { /* Handle favorite button click */ }) {
                    Icon(
                        imageVector = if (favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = null
                    )
                }
            }
        )

        // Image
        Image(
            painter = painterResource(id = dish.imageResource),
            contentDescription = "Dish image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.FillWidth
        )

        // Column for Text elements
        Column {
            // Display dish name
            Text(
                text = dish.name,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )

            // Display dish description
            Text(text = dish.description, style = MaterialTheme.typography.body1)

            Text(
                text = "Ingredients:",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )

            for (ingredient in dish.toString()) {
                Text(text = "• $ingredient")
            }
        }

        // Counter
        Counter(count = count, onCountChange = { newCount -> count = newCount })

        // Button
        Button(
            onClick = { /* Handle button click */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            // Display "Add to Cart" button with the dish price
            Text(
                text = "Add for $${dish.price}",
            )
        }
    }
}

@Composable
fun Counter(count: Int, onCountChange: (Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = { onCountChange(count - 1) },
            modifier = Modifier.size(48.dp)
        ) {
            Icon(Icons.Default.Clear, contentDescription = "Decrease")
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(text = count.toString(), style = MaterialTheme.typography.h4)

        Spacer(modifier = Modifier.width(16.dp))

        IconButton(
            onClick = { onCountChange(count + 1) },
            modifier = Modifier.size(48.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Increase")
        }
    }
}

@Preview
@Composable
fun DishDetailsPreview() {
    DishDetails(id = 1)
}
