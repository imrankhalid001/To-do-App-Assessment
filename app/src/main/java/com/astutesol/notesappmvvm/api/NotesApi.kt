package com.astutesol.notesappmvvm.api

import com.astutesol.notesappmvvm.models.GetCallListModelResopnseItem
import retrofit2.Response
import retrofit2.http.*

interface NotesApi {




    @GET("call")
    suspend fun getCallListData(): Response<List<GetCallListModelResopnseItem>>

    @GET("buy")
    suspend fun getBuyListData(): Response<List<GetCallListModelResopnseItem>>




}