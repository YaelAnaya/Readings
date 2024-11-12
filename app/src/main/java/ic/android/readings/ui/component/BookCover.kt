package ic.android.readings.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.magnifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.AsyncImagePainter.State.Loading
import coil3.compose.AsyncImagePainter.State.Success
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import ic.android.readings.R
import ic.android.readings.data.database.entity.Book
import kotlin.math.min

@Composable
fun BookCover(
    modifier: Modifier = Modifier,
    book: Book,
) {
    val painter = rememberAsyncImagePainter(model = book.imageUrl, contentScale = ContentScale.Crop)
    val state by painter.state.collectAsState()

    val transition by animateFloatAsState(
        targetValue = if (state is AsyncImagePainter.State.Success) 1f else 0f,
        label = "image content transition - ${book.title}"
    )

    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium),
        contentAlignment = Alignment.Center
    ) {
        if (state is Loading) LoadingAnimation()
        else
            Image(
                painter = painter,
                contentDescription = "book cover for ${book.title}",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .scale(.8f + (.2f * transition))
                    .graphicsLayer { rotationX = (1f - transition) * 5f }
                    .alpha(min(1f, transition / .2f))
            )
    }
}