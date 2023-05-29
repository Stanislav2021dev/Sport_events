package com.example.sportevents.presentation.sportevents.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sportevents.R
import com.example.sportevents.presentation.presentationModels.SportCategoriesPresentationModel
import com.example.sportevents.ui.theme.Colors
import com.example.sportevents.ui.theme.Dimens

@Composable
fun Header(event: SportCategoriesPresentationModel, expandAction: (String) -> Unit) {
    Row(modifier = Modifier
        .padding(vertical = Dimens.padding_mini)
        .background(Colors.HeaderColor)
        .fillMaxWidth()
        .defaultMinSize(minHeight = Dimens.headerHeight)) {
        event.icon?.let { icon ->
            Icon(
                modifier = Modifier
                    .padding(start = Dimens.padding_horizontal)
                    .align(CenterVertically),
                tint = Colors.DefaultIconColor,
                painter = painterResource(id = icon),
                contentDescription = event.sportName
            )
        }
        Text(
            modifier = Modifier
                .align(CenterVertically)
                .padding(start = Dimens.padding_horizontal)
                .weight(1f),
            text = event.sportName
        )

        Icon(
            modifier = Modifier
                .align(CenterVertically)
                .padding(horizontal = Dimens.padding_horizontal)
                .clickable {
                    expandAction(event.sportId)
                },
            tint = Colors.DefaultIconColor,
            painter = if (event.isCategoryExpanded) painterResource(id = R.drawable.ic_down_chevron)
                      else painterResource(id = R.drawable.ic_up_chevron),
            contentDescription = if (event.isCategoryExpanded) stringResource(id = R.string.collapse_sport_category_content_description)
                                 else stringResource(id = R.string.expand_sport_category_content_description)   
        )
    }
}

@Composable
@Preview
fun HeaderPreview() {
    Header(
        SportCategoriesPresentationModel(
            sportId = "",
            icon = R.drawable.ic_soccer,
            sportName = "Football",
            eventData = emptyList(),
            isCategoryExpanded = true
        ),
        expandAction = {}
    )
}