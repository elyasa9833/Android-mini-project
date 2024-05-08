package org.d3if0043.monefysafe.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0043.monefysafe.R
import org.d3if0043.monefysafe.ui.theme.MonefySafeTheme

const val KEY_ID_TRANSAKSI = "id_transaksi"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, id: Long? = null){
    var jumlahUang by rememberSaveable { mutableStateOf("") }
    var keterangan by rememberSaveable { mutableStateOf("") }
    var tipeTransaksi by rememberSaveable { mutableStateOf("") }

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
                    if(id == null)
                        Text(text = stringResource(id = R.string.tambah_transaksi))
                    else
                        Text(text = stringResource(id = R.string.edit_transaksi))
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
        FormTransaksi(
            jumlahUang = jumlahUang,
            onUangChange = { jumlahUang = it },
            keterangan = keterangan,
            onKeteranganChange = { keterangan = it },
            tipe = tipeTransaksi,
            onTipeChange = { tipeTransaksi = it},
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormTransaksi(
    jumlahUang: String, onUangChange: (String) -> Unit,
    keterangan: String, onKeteranganChange: (String) -> Unit,
    tipe: String, onTipeChange: (String) -> Unit,
    modifier: Modifier
) {
    val configuration = LocalConfiguration.current
    val radioOption = listOf(
        stringResource(id = R.string.deposit),
        stringResource(id = R.string.withdraw)
    )

    Column(
        modifier = if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        else modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = jumlahUang,
            onValueChange = { onUangChange(it) },
            label = { Text(stringResource(id = R.string.nominal)) },
            leadingIcon = { Text(text = stringResource(id = R.string.leadIcon)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = keterangan,
            onValueChange = { onKeteranganChange(it) },
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
                    isSelected = tipe == text,
                    modifier = Modifier
                        .selectable(
                            selected = tipe == text,
                            onClick = { onTipeChange(text) },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
        ResponsiveImage()
    }
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