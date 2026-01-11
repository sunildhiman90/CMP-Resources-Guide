import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import cmp_resources_guide.composeapp.generated.resources.Res
import cmp_resources_guide.composeapp.generated.resources.Res.readBytes
import cmp_resources_guide.composeapp.generated.resources.Workbench_Regular
import cmp_resources_guide.composeapp.generated.resources.allStringResources
import cmp_resources_guide.composeapp.generated.resources.app_name
import cmp_resources_guide.composeapp.generated.resources.compose_multiplatform
import cmp_resources_guide.composeapp.generated.resources.font_awesome
import cmp_resources_guide.composeapp.generated.resources.platforms
import cmp_resources_guide.composeapp.generated.resources.plural_text
import cmp_resources_guide.composeapp.generated.resources.simple_text
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                //imageVector = vectorResource(Res.drawable.compose_multiplatform),
                //bitmap = imageResource(Res.drawable.compose_multiplatform),
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(Res.string.app_name)
            )

            val scope = rememberCoroutineScope()
            scope.launch {
                getString(Res.string.simple_text)
            }

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = pluralStringResource(Res.plurals.plural_text, 1, 1)
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = pluralStringResource(Res.plurals.plural_text, 2, 3)
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringArrayResource(Res.array.platforms).joinToString()
            )


            val customFont = FontFamily(Font(Res.font.Workbench_Regular))

            Text(
                fontFamily = customFont,
                modifier = Modifier.padding(top = 16.dp),
                text = stringArrayResource(Res.array.platforms).joinToString()
            )


            var filesContent by remember {  mutableStateOf(ByteArray(0)) }

            LaunchedEffect(Unit) {
                filesContent = readBytes("files/common-text.txt")
            }

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = filesContent.decodeToString()
            )

            val string = stringResource(Res.allStringResources["app_name"]!!)

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = string
            )

            val uri = Res.getUri("files/testData.json")
            println("uri: $uri")
        }
    }
}