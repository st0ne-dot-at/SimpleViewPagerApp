package at.st0ne.simpleviewpagerapp

import android.content.res.Resources
import android.util.TypedValue
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

fun Dp.toPx(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.value,
        Resources.getSystem().displayMetrics
    )
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun SimpleViewPager(
    modifier: Modifier = Modifier,
    initialPage: Int = 0,
    itemsCount: Int,
    itemsFactory: @Composable (index: Int) -> Unit
) {
    val scrollState: ScrollState = rememberScrollState()
    val swipeableState: SwipeableState<Int> = rememberSwipeableState(initialValue = 0)
    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
    ) {
        // the magic happens here
        // compose ScrollState from SwipeableState
        LaunchedEffect(key1 = swipeableState.offset.value) {
            scrollState.scrollTo(swipeableState.offset.value.toInt())
        }
        LaunchedEffect(Unit) {
            if (initialPage <= itemsCount - 1)
                swipeableState.animateTo(initialPage)
        }

        if (itemsCount > 0) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(maxWidth * itemsCount)
                    .swipeable(
                        state = swipeableState,
                        reverseDirection = true,
                        orientation = Orientation.Horizontal,
                        anchors = (0 until itemsCount)
                            .map { i ->
                                i * maxWidth.toPx() to i
                            }
                            .toMap()
                    )
                    .horizontalScroll(state = scrollState, enabled = false)
            ) {
                (0 until itemsCount).forEach { index: Int ->
                    Box(
                        modifier = modifier.width(this@BoxWithConstraints.maxWidth)
                    ) {
                        itemsFactory(index)
                    }
                }
            }
        }
    }
}