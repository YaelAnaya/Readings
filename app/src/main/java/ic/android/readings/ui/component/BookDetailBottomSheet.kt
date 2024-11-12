package ic.android.readings.ui.component

import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import ic.android.readings.R
import ic.android.readings.data.database.entity.Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailBottomSheet(
    modifier: Modifier,
    sheetState: SheetState,
    book: Book,
    onDismiss: () -> Unit = {}
) {
    BoxWithConstraints {
        val sheetHeight = this.constraints.maxHeight * 0.5f
        ModalBottomSheet(
            modifier = modifier.height(with(LocalDensity.current) { sheetHeight.toDp() }),
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            containerColor = Color.White,
        ) {
            BookDetailLayout(book = book)
        }
    }

}

@Composable
private fun BookDetailLayout(
    modifier: Modifier = Modifier,
    book: Book
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        BookDetail(book = book)
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { searchBookIntent(context, book) },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "Get this book",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Composable
private fun BookDetail(
    modifier: Modifier = Modifier,
    book: Book
) {

    Row(
        modifier = modifier.height(215.dp),
        verticalAlignment = Alignment.Top
    ) {
        BookCover(
            modifier = Modifier
                .width(130.dp)
                .height(215.dp),
            book = book
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
        ) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 18.sp,
                    lineHeight = 20.sp,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                modifier = Modifier.padding(top = 6.dp, bottom = 6.dp),
                text = book.author,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )

            Text(
                modifier = Modifier.padding(top = 6.dp, bottom = 16.dp),
                text = stringResource(R.string.book_detail_description),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                ),
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                HorizontalDivider(
                    modifier = Modifier
                        .width(60.dp)
                        .padding(end = 8.dp),
                    color = Color.Gray.copy(alpha = 0.4f)
                )
                Text(
                    text = "${book.totalPages} pages",
                    style = MaterialTheme.typography.bodySmall,
                    fontFamily = FontFamily.Serif,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.primary
                )
                HorizontalDivider(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(start = 8.dp),
                    color = Color.Gray.copy(alpha = 0.4f)
                )
            }


        }
    }
}

private fun searchBookIntent(context: Context, book: Book) {
    val intent = Intent(Intent.ACTION_WEB_SEARCH)
    intent.putExtra("query", "${book.title} ${book.author}")
    context.startActivity(intent)
}

@Composable
fun LoadingAnimation() {
    val animation = rememberInfiniteTransition(label = stringResource(R.string.loading_animation_label))
    val progress by animation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Restart,
        ),
        label = stringResource(R.string.progress_animation_label)
    )

    Box(
        modifier = Modifier
            .size(60.dp)
            .scale(progress)
            .alpha(1f - progress)
            .border(
                width = 5.dp,
                color = Color.Black,
                shape = CircleShape
            )
    )
}