package org.d3if0043.monefysafe.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0043.monefysafe.R
import org.d3if0043.monefysafe.model.Transaksi
import org.d3if0043.monefysafe.navigation.Screen
import org.d3if0043.monefysafe.ui.theme.MonefySafeTheme
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary

                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.tambah_transaksi))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = stringResource(id = R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) {padding ->
        InputContent(navController, Modifier.padding(padding))

    }
}

@Composable
fun InputContent(navController: NavHostController, modifier: Modifier){
    val radioOption = listOf(
        stringResource(id = R.string.deposit),
        stringResource(id = R.string.withdraw)
    )
    val viewModel: MainViewModel = viewModel()
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    var type by rememberSaveable { mutableStateOf(radioOption[0]) }
    var jumlahUang by rememberSaveable { mutableStateOf("") }
    var jumlahError by rememberSaveable { mutableStateOf(false) }
    var keterangan by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            modifier.padding(16.dp).verticalScroll(rememberScrollState())
            else modifier.padding(16.dp)

    ) {
        OutlinedTextField(
            value = jumlahUang,
            onValueChange = {jumlahUang = it},
            label = { Text(stringResource(id = R.string.nominal)) },
            isError = jumlahError,
            leadingIcon = { Text(text = stringResource(id = R.string.leadIcon)) },
            trailingIcon = { IconPicker(isError = jumlahError) },
            supportingText = { ErrorHint(isError = jumlahError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = keterangan,
            onValueChange = {keterangan = it},
            label = { Text(stringResource(id = R.string.keterangan)) },
            minLines = 3,
            maxLines = 3,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ){
            radioOption.forEach { text ->
                TransactionTypes(
                    label = text,
                    isSelected = type == text,
                    modifier = Modifier
                        .selectable(
                            selected = type == text,
                            onClick = { type = text },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
        ResponsiveImage()

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                jumlahError = (jumlahUang == "" || jumlahUang == "0" || jumlahUang.toIntOrNull() == null)
                if(jumlahError) return@Button

                viewModel.addData(
                    Transaksi(jumlahUang.toInt(), if(keterangan == "") "-" else keterangan, type, getCurrentDate() )
                )
                navController.navigate(Screen.Second.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.simpan))
        }
    }
}

private fun shareData(context: Context, message: String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if(shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

fun getCurrentDate(): String {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1 // Januari dimulai dari 0
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    return "$day/$month/$year" // format "dd/mm/yyyy"
}

@Composable
fun TransactionTypes(label: String, isSelected: Boolean, modifier: Modifier){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun IconPicker(isError: Boolean){
    if(isError) Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
}

@Composable
fun ErrorHint(isError: Boolean){
    if(isError) Text(text = stringResource(id = R.string.invalid_input))
}

@Composable
fun ResponsiveImage() {
    val configuration = LocalConfiguration.current

    if (configuration.orientation != Configuration.ORIENTATION_LANDSCAPE) {
        Image(
            painter = painterResource(id = R.drawable.banner_image),
            contentDescription = stringResource(id = R.string.banner_text),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Divider(thickness = 4.dp)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun SecondPreview() {
    MonefySafeTheme {
        MainScreen(rememberNavController())
    }
}