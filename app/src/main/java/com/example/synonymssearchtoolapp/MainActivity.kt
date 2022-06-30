package com.example.synonymssearchtoolapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.synonymssearchtoolapp.feature.presentation.SynonymItem
import com.example.synonymssearchtoolapp.feature.presentation.SynonymViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: SynonymViewModel = hiltViewModel()
            val state = viewModel.state.value
            val scaffoldState = rememberScaffoldState()
            LaunchedEffect(key1 = true) {
                viewModel.eventFlow.collectLatest { event ->
                    when(event) {
                        is SynonymViewModel.UIEvent.ShowSnackbar -> {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = event.message
                            )
                        }
                    }
                }
            }
            Scaffold(
                scaffoldState = scaffoldState
            ){
                Box(
                    modifier = Modifier.background(MaterialTheme.colors.background)
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(14.dp)
                    ){
                        TextField(
                            value = viewModel.searchQuery.value,
                            onValueChange = viewModel::onSearch,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Search...")
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(state.synonymItems.size){ i ->
                            val word = state.synonymItems[i]
                            if(i > 0) {
                                Spacer(modifier = Modifier.height(7.dp))
                            }
                            SynonymItem(synonym = word, modifier = Modifier.fillMaxWidth())

                            if(i < state.synonymItems.size - 1) {
                                Divider()
                            }
                        }
                    }
                }

            }
        }
    }

}