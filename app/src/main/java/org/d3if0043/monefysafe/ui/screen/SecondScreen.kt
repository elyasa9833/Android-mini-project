package org.d3if0043.monefysafe.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0043.monefysafe.R
import org.d3if0043.monefysafe.model.Transaksi
import org.d3if0043.monefysafe.ui.theme.MonefySafeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavHostController){
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
    val data =  viewModel.data

    if(data.isEmpty()){
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = stringResource(id = R.string.data_kosong))
        }
    }else{
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 84.dp)
        ){
            items(data){
                ListItem(transaksi = it)
                Divider()
            }
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
        SecondScreen(rememberNavController())
    }
}