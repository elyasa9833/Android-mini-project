package org.d3if0043.monefysafe.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.d3if0043.monefysafe.R
import org.d3if0043.monefysafe.model.Transaksi
import org.d3if0043.monefysafe.ui.theme.MonefySafeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {padding ->
        ScreenContent(Modifier.padding(padding))

    }
}

@Composable
fun ScreenContent(modifier: Modifier){
    val viewModel: MainViewModel = viewModel()
    val data = viewModel.data

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ){
        items(data){
            ListItem(transaksi = it)
            Divider()
        }
    }
}

@Composable
fun ListItem(transaksi: Transaksi){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ){
        Text(text = transaksi.Jumlah.toString())
        Text(text = transaksi.Jenis)
        Text(text = transaksi.keterangan)
        Text(text = transaksi.tanggal)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun GreetingPreview() {
    MonefySafeTheme {
        MainScreen()
    }
}