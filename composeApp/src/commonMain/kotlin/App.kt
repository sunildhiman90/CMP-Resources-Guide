import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cmp_resources_guide.composeapp.generated.resources.Res
import cmp_resources_guide.composeapp.generated.resources.Res.readBytes
import cmp_resources_guide.composeapp.generated.resources.Workbench_Regular
import cmp_resources_guide.composeapp.generated.resources.app_name
import cmp_resources_guide.composeapp.generated.resources.compose_multiplatform
import cmp_resources_guide.composeapp.generated.resources.plural_text
import cmp_resources_guide.composeapp.generated.resources.simple_text
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.getPluralString
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview


//From Non Composable Code
@OptIn(ExperimentalResourceApi::class)
fun nonComposableCode() {

    val coroutineScope = CoroutineScope(Dispatchers.Main)
    coroutineScope.launch {
        //They are suspend functions, we can use them in coroutines
        val appName = getString(Res.string.app_name)
        val pluralString = getPluralString(Res.plurals.plural_text, 4, 4)

        val bytes = readBytes("files/testData.json")

    }

}

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {

        Column(
            Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(Res.string.app_name),
                style = MaterialTheme.typography.headlineLarge
            )

            ImageResourcesExamples()

            StringResourcesExamples()

            //FileResourcesExamples()

            FontResources()

            //From non composable code

            //For sharing the path with external libraries.

            //Platform specific path
            val path = Res.getUri("files/testData.json")

        }
    }
}

@Composable
private fun FontResources() {
    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = "Fonts",
        style = MaterialTheme.typography.headlineSmall
    )

    val customFont = FontFamily(Font(Res.font.Workbench_Regular))

    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = "Custom Font Text",
        fontFamily = customFont,
        style = MaterialTheme.typography.headlineSmall
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun FileResourcesExamples() {
    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = "Files",
        style = MaterialTheme.typography.headlineSmall
    )

    var bytesCommonFile by remember { mutableStateOf(ByteArray(0)) }
    var bytesJsonFile by remember { mutableStateOf(ByteArray(0)) }
    var platformFile by remember { mutableStateOf(ByteArray(0)) }

    LaunchedEffect(Unit) {
        bytesCommonFile = readBytes("files/common-text.txt")
        platformFile = readBytes("files/platform-specific-file.txt")
        bytesJsonFile = readBytes("files/testData.json")
        //Decoding json data using kotlinx serialization json
    }

    Text(
        "Common Text File Content:",
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold
    )

    Text(
        text = bytesJsonFile.decodeToString(),
        modifier = Modifier.padding(8.dp).wrapContentHeight().fillMaxWidth(),
    )


    Text(
        "Platform Specific File Content:",
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold
    )

    Text(
        text = platformFile.decodeToString(),
        modifier = Modifier.padding(8.dp).wrapContentHeight().fillMaxWidth(),
    )

}

@Composable
private fun StringResourcesExamples() {
    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = "Strings",
        style = MaterialTheme.typography.headlineSmall
    )

    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = "${stringResource(Res.string.simple_text)}"
    )

    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = "Plural with Single Item: ${pluralStringResource(Res.plurals.plural_text, 1, 1)}"
    )

    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = "Plural with Multiple Items: ${pluralStringResource(Res.plurals.plural_text, 4, 4)}"
    )
}

@Composable
private fun ImageResourcesExamples() {
    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = "Images",
        style = MaterialTheme.typography.headlineMedium
    )

    Image(
        painter = painterResource(Res.drawable.compose_multiplatform),
        //imageVector = vectorResource(Res.drawable.compose_multiplatform),
        //bitmap = imageResource(Res.drawable.compose_multiplatform),
        contentDescription = null,
        modifier = Modifier.size(300.dp)
    )
}