package com.wan.android.compose.component

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.animateDecay
import androidx.compose.animation.core.animateTo
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.BottomAppBarState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import com.wan.android.compose.theme.BottomTabIconSize
import com.wan.android.compose.theme.BottomTabLabelSize
import kotlin.math.abs
import kotlin.math.roundToInt

/**
 * @Description:
 * @Date: 2024/9/12 11:06
 * @author:  liuwenjie09
 * @version: 1.0
 */


private val BottomAppBarHeight = 56.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationBar(
    tab: List<Pair<String, Int>>,
    selectedIndex: Int,
    onSelectTab: (index: Int) -> Unit,
    height:Dp = BottomAppBarHeight,
    scrollBehavior: BottomAppBarScrollBehavior? = null,
    selectedIconColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedIconColor: Color = MaterialTheme.colorScheme.onBackground,
    selectLabelColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedLabelColor: Color = MaterialTheme.colorScheme.onBackground,
    unSelectedIconSize: Dp = MaterialTheme.BottomTabIconSize,
    selectedIconSize: Dp = MaterialTheme.BottomTabIconSize,
    unSelectedTextSize: TextUnit = MaterialTheme.BottomTabLabelSize,
    selectedTextSize: TextUnit = MaterialTheme.BottomTabLabelSize
) {
    val selectTab = rememberUpdatedState(onSelectTab)
    AppBottomBar(
        modifier = Modifier
            .fillMaxWidth(),
        height = height,
        contentPadding = PaddingValues(0.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        scrollBehavior = scrollBehavior
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .height(56.dp)) {
            HorizontalDivider(thickness = 0.5.dp)
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                for (index in tab.indices) {
                    key(index) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                                .rippleClickable(radius = 50.dp) {
                                    selectTab.value(index)
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                modifier = if (index == selectedIndex) {
                                    Modifier.size(selectedIconSize)
                                } else {
                                    Modifier.size(unSelectedIconSize)
                                },
                                painter = painterResource(id = tab.get(index).second),
                                contentDescription = "Localized description",
                                tint = if (index == selectedIndex) {
                                    selectedIconColor
                                } else {
                                    unSelectedIconColor
                                },
                            )
                            Text(
                                text = tab.get(index).first, color = if (index == selectedIndex) {
                                    selectLabelColor
                                } else {
                                    unSelectedLabelColor
                                }, fontSize = if (index == selectedIndex) {
                                    selectedTextSize
                                } else {
                                    unSelectedTextSize
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    height:Dp = BottomAppBarHeight,
    containerColor: Color = BottomAppBarDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    tonalElevation: Dp = BottomAppBarDefaults.ContainerElevation,
    contentPadding: PaddingValues = BottomAppBarDefaults.ContentPadding,
    windowInsets: WindowInsets = BottomAppBarDefaults.windowInsets,
    scrollBehavior: BottomAppBarScrollBehavior? = null,
    content: @Composable RowScope.() -> Unit
) {
    // Set up support for resizing the bottom app bar when vertically dragging the bar itself.
    val appBarDragModifier = if (scrollBehavior != null && !scrollBehavior.isPinned) {
        Modifier.draggable(
            orientation = Orientation.Vertical,
            state = rememberDraggableState { delta ->
                scrollBehavior.state.heightOffset -= delta
            },
            onDragStopped = { velocity ->
                settleAppBarBottom(
                    scrollBehavior.state,
                    velocity,
                    scrollBehavior.flingAnimationSpec,
                    scrollBehavior.snapAnimationSpec
                )
            }
        )
    } else {
        Modifier
    }

    // Compose a Surface with a Row content.
    // The height of the app bar is determined by subtracting the bar's height offset from the
    // app bar's defined constant height value (i.e. the ContainerHeight token).
    Surface(
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        // TODO(b/209583788): Consider adding a shape parameter if updated design guidance allows
        shape = RectangleShape,
        modifier = modifier
            .layout { measurable, constraints ->
                // Sets the app bar's height offset to collapse the entire bar's height when content
                // is scrolled.
                scrollBehavior?.state?.heightOffsetLimit =
                    -height.toPx()

                val placeable = measurable.measure(constraints)
                val height = placeable.height + (scrollBehavior?.state?.heightOffset ?: 0f)
                layout(placeable.width, height.roundToInt()) {
                    placeable.place(0, 0)
                }
            }
            .then(appBarDragModifier)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .windowInsetsPadding(windowInsets)
                .height(height)
                .padding(contentPadding),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

@ExperimentalMaterial3Api
private suspend fun settleAppBarBottom(
    state: BottomAppBarState,
    velocity: Float,
    flingAnimationSpec: DecayAnimationSpec<Float>?,
    snapAnimationSpec: AnimationSpec<Float>?
): Velocity {
    // Check if the app bar is completely collapsed/expanded. If so, no need to settle the app bar,
    // and just return Zero Velocity.
    // Note that we don't check for 0f due to float precision with the collapsedFraction
    // calculation.
    if (state.collapsedFraction < 0.01f || state.collapsedFraction == 1f) {
        return Velocity.Zero
    }
    var remainingVelocity = velocity
    // In case there is an initial velocity that was left after a previous user fling, animate to
    // continue the motion to expand or collapse the app bar.
    if (flingAnimationSpec != null && abs(velocity) > 1f) {
        var lastValue = 0f
        AnimationState(
            initialValue = 0f,
            initialVelocity = velocity,
        )
            .animateDecay(flingAnimationSpec) {
                val delta = value - lastValue
                val initialHeightOffset = state.heightOffset
                state.heightOffset = initialHeightOffset + delta
                val consumed = abs(initialHeightOffset - state.heightOffset)
                lastValue = value
                remainingVelocity = this.velocity
                // avoid rounding errors and stop if anything is unconsumed
                if (abs(delta - consumed) > 0.5f) this.cancelAnimation()
            }
    }
    // Snap if animation specs were provided.
    if (snapAnimationSpec != null) {
        if (state.heightOffset < 0 &&
            state.heightOffset > state.heightOffsetLimit
        ) {
            AnimationState(initialValue = state.heightOffset).animateTo(
                if (state.collapsedFraction < 0.5f) {
                    0f
                } else {
                    state.heightOffsetLimit
                },
                animationSpec = snapAnimationSpec
            ) { state.heightOffset = value }
        }
    }

    return Velocity(0f, remainingVelocity)
}