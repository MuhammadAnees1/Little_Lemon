package com.aneesferoz.littlelemon

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.R
import com.aneesferoz.littlelemon.ui.theme.LittleLemonColor


@Composable
fun LowerPanel(navController: NavHostController, dishes: List<Dish> = listOf()) {
    Column {
        WeeklySpecialCard()
        LazyColumn {
            itemsIndexed(dishes) { _, dish ->
                MenuDish(navController, dish)
                Divider(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    thickness = 1.dp,
                    color = LittleLemonColor.yellow
                )
            }
        }
    }
}

@Composable
fun WeeklySpecialCard() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.weekly_special),
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MenuDish(navController: NavHostController? = null, dish: Dish) {
    Card(
        onClick = {
            Log.d("AAA", "Click ${dish.id}")
            navController?.navigate(DishDetails.route + "/${dish.id}")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Column for dish details
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(8.dp)
            ) {
                // Image
                Image(
                    painter = painterResource(id = dish.imageResource),
                    contentDescription = dish.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(MaterialTheme.shapes.medium)
                )

                // Display dish name with h2 style
                Text(
                    text = dish.name,
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.primary
                )

                // Display dish description with body1 style
                Text(
                    text = dish.description,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface
                )

                // Display dish price with body2 style
                Text(
                    text = "$${dish.price}",
                    style = MaterialTheme.typography.body2,
                    color = LittleLemonColor.green
                )
            }

            // Add other elements if needed
        }
    }
}

