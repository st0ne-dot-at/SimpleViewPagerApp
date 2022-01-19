package at.st0ne.simpleviewpagerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import at.st0ne.simpleviewpagerapp.ui.theme.SimpleViewPagerAppTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val list = listOf(1, 2, 3, 4, 5)

        setContent {
            SimpleViewPagerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        SimpleViewPager(
                            initialPage = (list.size-1)/2, // start in the middle
                            itemsCount = list.size,
                            itemsFactory = { index ->
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        //modifier = Modifier.width(392.72726.dp),
                                        text = "SwipeMe - ${list[index]}",
                                        style = MaterialTheme.typography.h2
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}