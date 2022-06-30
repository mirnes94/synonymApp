@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.example.synonymssearchtoolapp.feature.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.synonymssearchtoolapp.feature.domain.model.Synonym

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SynonymItem(
    synonym: Synonym?,
    modifier: Modifier?
) {
    modifier?.let {
        Column(modifier = it)  {
        Text(
            text = synonym?.word ?: "aa",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black

        )
        synonym?.synonyms?.forEach { synonym ->
            Text(text = synonym, fontWeight = FontWeight.Bold)
        }


    }
    }

}