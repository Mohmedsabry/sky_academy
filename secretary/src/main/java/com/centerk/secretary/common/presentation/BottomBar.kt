package com.centerk.secretary.common.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.centerk.secretary.common.presentation.util.BottomNavigationItem
import com.centerk.secretary.common.presentation.util.NavigationRoute
import com.centerk.secretary.common.presentation.util.handleBottomNavigation
import com.centerk.secretary.navigation.NavigationRoutes
import com.centery.ui.R
import com.core.ui.theme.newTypography

/**
 * Never forget update selected item with NavigationRoute.Home ,
 * NavigationRoute.Groups,NavigationRoute.Student,NavigationRoute.Finance
 * */
@Composable
fun BottomBar(
    selectedItem: NavigationRoute = NavigationRoute.Home,
    items: List<BottomNavigationItem> = listOf(
        BottomNavigationItem(
            title = stringResource(R.string.home),
            icon = R.drawable.home,
            navigationRoute = NavigationRoute.Home
        ),
        BottomNavigationItem(
            title = stringResource(R.string.student),
            icon = R.drawable.student,
            navigationRoute = NavigationRoute.Student
        ),
        BottomNavigationItem(
            title = stringResource(R.string.groups),
            icon = R.drawable.groups,
            navigationRoute = NavigationRoute.Groups
        ),
        BottomNavigationItem(
            title = stringResource(R.string.finance),
            icon = R.drawable.finance,
            navigationRoute = NavigationRoute.Finance
        ),
    ),
    onClick: (NavigationRoutes?) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                isSelected = selectedItem == item.navigationRoute,
                bottomNavigationItem = item,
                onClick = {
                    onClick(
                        handleBottomNavigation(selectedItem, item.navigationRoute)
                    )
                }
            )
        }
    }
}

@Composable
fun BottomNavigationItem(
    isSelected: Boolean,
    bottomNavigationItem: BottomNavigationItem,
    onClick: (NavigationRoute) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .clickable { onClick(bottomNavigationItem.navigationRoute) },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(bottomNavigationItem.icon),
            contentDescription = bottomNavigationItem.title,
            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary
        )
        Text(
            text = bottomNavigationItem.title,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
            style = newTypography.bodySmall
        )
    }
}