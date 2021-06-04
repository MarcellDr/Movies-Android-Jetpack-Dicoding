package com.marcelldr.cinemaz.utils

import androidx.paging.PagedList
import org.mockito.Mockito.*

object PagedListUtil {

    fun <T> mockPagedList(list: List<T>): PagedList<*> {
        val pagedList = mock(PagedList::class.java) as PagedList<*>
        `when`(pagedList[anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        `when`(pagedList.size).thenReturn(list.size)

        return pagedList
    }
}