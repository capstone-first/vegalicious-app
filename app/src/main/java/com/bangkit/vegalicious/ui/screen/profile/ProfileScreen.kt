package com.bangkit.vegalicious.ui.screen.profile

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme
import com.bangkit.vegalicious.ui.theme.overweight
import com.bangkit.vegalicious.ui.theme.underweight
import com.bangkit.vegalicious.utils.Injection
import com.bangkit.vegalicious.utils.ViewModelFactory

@Composable
fun ProfileScreen(
	viewModel: ProfileViewModel = viewModel(
		factory = ViewModelFactory(
			Injection.provideProfileRepository()
		)
	),
	onClickLogout: () -> Unit = {},
	username: String,
) {
	Column(
		modifier = Modifier
			.verticalScroll(rememberScrollState()),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Box(
			contentAlignment = Alignment.TopCenter,
			modifier = Modifier.padding(bottom = 52.dp)
		) {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.height(112.dp)
					.background(MaterialTheme.colorScheme.primary),
				
				)
			Box(
				modifier = Modifier
					.size(size = 140.dp)
					.offset(y = 38.dp)
					.border(
						width = 8.dp,
						color = MaterialTheme.colorScheme.background,
						shape = CircleShape,
						
						)
			)
			AsyncImage(
				model = "https://raw.githubusercontent.com/capstone-first/Vegalicious-Avatar/main/personas-1686142004455.png",
				contentDescription = null,
				contentScale = ContentScale.Crop,
				modifier = Modifier
					.size(110.dp)
					.offset(y = 52.dp)
					.clip(CircleShape)
			)
		}
		val mod = Modifier
			.padding(horizontal = 32.dp)
			.fillMaxWidth()
		Text(
			text = "John Doe",
			style = MaterialTheme.typography.headlineSmall,
			textAlign = TextAlign.Center,
			modifier = mod
		)
		ElevatedCard(
			modifier = mod.padding(top = 16.dp),
			colors = CardDefaults.elevatedCardColors(
				containerColor = MaterialTheme.colorScheme.background
			),
		) {
			Row(
				modifier = Modifier
					.padding(16.dp)
					.height(IntrinsicSize.Min),
			) {
				Column(
					Modifier.weight(1f)
				) {
					Text(
						text = "Height",
						textAlign = TextAlign.Center,
						modifier = Modifier.fillMaxWidth(),
						style = MaterialTheme.typography.labelMedium
							.copy(fontWeight = FontWeight.Bold)
					)
					Row(
						Modifier.fillMaxHeight(),
						verticalAlignment = Alignment.CenterVertically,
					) {
						Text(
							text = "168cm",
							textAlign = TextAlign.Center,
							modifier = Modifier
								.fillMaxWidth(),
							style = MaterialTheme.typography.bodyMedium
						)
					}
				}
				Column(
					Modifier
						.weight(1f)
						.fillMaxHeight()
				) {
					Text(
						text = "Weight",
						textAlign = TextAlign.Center,
						modifier = Modifier.fillMaxWidth(),
						style = MaterialTheme.typography.labelMedium
							.copy(fontWeight = FontWeight.Bold)
					)
					Row(
						Modifier.fillMaxHeight(),
						verticalAlignment = Alignment.CenterVertically,
					) {
						Text(
							text = "72kg",
							textAlign = TextAlign.Center,
							modifier = Modifier
								.fillMaxWidth(),
							style = MaterialTheme.typography.bodyMedium
						)
					}
				
				}
				Column(
					Modifier.weight(1f).background(color = overweight),
				) {
					Text(
						text = "BMI",
						textAlign = TextAlign.Center,
						modifier = Modifier.fillMaxWidth(),
						style = MaterialTheme.typography.labelMedium
							.copy(fontWeight = FontWeight.Bold)
					)
					Text(
						text = "25.5",
						textAlign = TextAlign.Center,
						modifier = Modifier
							.padding(vertical = 4.dp)
							.fillMaxWidth(),
						style = MaterialTheme.typography.bodyMedium
					)
					Text(
						text = "Overweight",
						textAlign = TextAlign.Center,
						modifier = Modifier.fillMaxWidth(),
						style = MaterialTheme.typography.labelSmall
					)
				
				}
			}
		}
		InformationElement(title = "Username", value = "johndoe123", modifier = mod)
		InformationElement(title = "Email", value = "johndoe@email.com", modifier = mod)
		InformationElement(title = "Date of Birth", value = "January 16, 2002", modifier = mod)
		
		Divider(
			modifier = mod.padding(vertical = 16.dp)
		)
		
		Button(
			onClick = { /*TODO*/ },
			modifier = Modifier.width(156.dp),
			colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
		) {
			Text("Log Out")
		}
	}
}

@Composable
fun InformationElement(modifier: Modifier = Modifier, title: String, value: String) {
	Text(
		text = title,
		modifier = modifier.padding(top = 16.dp),
		style = MaterialTheme.typography.titleSmall,
	)
	Text(
		text = value,
		modifier = modifier,
	)
}

@Preview
@Composable
fun ProfileScreenPreview() {
	VegaliciousTheme {
		Surface(
			modifier = Modifier.fillMaxSize(),
			color = MaterialTheme.colorScheme.background,
		) {
			ProfileScreen(username = "johndoe123")
		}
	}
}