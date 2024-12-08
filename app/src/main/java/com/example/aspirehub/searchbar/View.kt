package com.example.aspirehub.searchbar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aspirehub.models.Subject
import com.example.aspirehub.sealed.DataState
import com.google.firebase.database.FirebaseDatabase

class YourViewModel : ViewModel() {
    private val _response = MutableLiveData<DataState<List<Subject>>>()
    val response: LiveData<DataState<List<Subject>>> = _response

    fun searchSubjects(query: String, callback: (List<Subject>) -> Unit) {
//        FirebaseDatabase.getInstance().getReference("subject")
//
//        // Perform the query to Firebase Realtime Database (filter by name, for example)
////        db.orderByChild("Arts") // Assuming "name" is a field in your Subject data
////            .startAt(query)
////            .endAt("$query\uf8ff") // Use this to search for names starting with the query
//            .get()
//            .addOnSuccessListener { snapshot ->
//                if (snapshot.exists()) {
//                    println(query)
////                    val subjects: List<Subject> = snapshot.children.mapNotNull { it.getValue(Subject::class.java) }
////                    val query_Subjects_list: List<Subject> = subjects.filter {
////                        it.toString().lowercase().contains(query.lowercase())
////                    }
//                    val subjects: List<Subject> = snapshot.children.mapNotNull { it.getValue(Subject::class.java) }
//
//                    val query_Subjects_list: List<Subject> = subjects.filter {
//                        // Assuming 'name' is the property you're searching
//                        it.name?.lowercase()?.contains(query.lowercase()) == true || it.degree.toString()?.lowercase()?.contains(query.lowercase()) == true
//                    }
//
//                    println("xxxxxxxxxxxxxxxxxxxxxxxx")
//                    println(subjects.size)
//                    println("000000000000000000000000")
//                    println(query_Subjects_list.size)
//                    println("xxxxxxxxxxxxxxxxxxxxxxxx")
//                    _response.value = DataState.Success(query_Subjects_list)
//                } else {
//                    _response.value = DataState.Empty
//                }
//            }
//            .addOnFailureListener { exception ->
//                _response.value = DataState.Error(exception)
//            }
        val db = FirebaseDatabase.getInstance().getReference("subject")

        db.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val subjects: List<Subject> =
                    snapshot.children.mapNotNull { it.getValue(Subject::class.java) }
                println("000000000000000000000000000")
                println(subjects.size)
                // Filter the subjects based on the search query
                val query_Subjects_list: List<Subject> = subjects.filter { subject ->
                    matchesQuery(subject, query)
                }

                // Update the response with filtered subjects
                if (query_Subjects_list.isNotEmpty()) {
                    _response.value = DataState.Success(query_Subjects_list)
                    callback(query_Subjects_list)
                } else {
                    _response.value = DataState.Empty
                }
            } else {
                _response.value = DataState.Empty
            }
        }.addOnFailureListener { exception ->
            _response.value = DataState.Error(exception)
        }


    }

    fun matchesQuery(subject: Subject, query: String): Boolean {
        val lowercaseQuery = query.lowercase()

        // Check top-level Subject fields
        if (subject.name?.lowercase()?.contains(lowercaseQuery) == true ||
            subject.img?.lowercase()?.contains(lowercaseQuery) == true
        ) return true

        // Check Degrees within the Subject
        subject.degree?.forEach { degree ->
            if (degree.name?.lowercase()?.contains(lowercaseQuery) == true ||
                degree.img.lowercase().contains(lowercaseQuery)
            ) return true

            // Check Courses within the Degree
            degree.options?.forEach { course ->
                if (course.name?.lowercase()?.contains(lowercaseQuery) == true ||
                    course.img?.lowercase()?.contains(lowercaseQuery) == true ||
                    course.introduction.any { it.lowercase().contains(lowercaseQuery) } ||
                    course.eligibility.any { it.lowercase().contains(lowercaseQuery) } ||
                    course.entryPath.any { it.lowercase().contains(lowercaseQuery) } ||
                    course.institutions.any { it.lowercase().contains(lowercaseQuery) } ||
                    course.jobRoles.any { it.lowercase().contains(lowercaseQuery) } ||
                    course.workPlace?.lowercase()?.contains(lowercaseQuery) == true ||
                    course.expectedSalary?.lowercase()?.contains(lowercaseQuery) == true ||
                    course.fees?.lowercase()?.contains(lowercaseQuery) == true
                ) return true
            }
        }

        // If no matches are found
        return false
    }


}
