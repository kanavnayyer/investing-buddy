package com.awesome.investingbuddy.models

import com.google.firebase.firestore.DocumentId

data class Note(
    @DocumentId
    var id: String = "", // Firestore document ID (annotated with @DocumentId)
    var title: String = "",
    var description: String = "",
    var userEmail: String = "" // User's email associated with the note ,

)
