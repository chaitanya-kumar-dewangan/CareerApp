package com.example.aspirehub.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aspirehub.models.Course
import com.example.aspirehub.models.Degree
import com.example.aspirehub.models.Subject
import com.example.aspirehub.sealed.DataState
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _response = MutableStateFlow<DataState<List<Subject>>>(DataState.Loading)
    val response: StateFlow<DataState<List<Subject>>> get() = _response
    val interest = MutableStateFlow<Subject?>(Subject())
    val degree = MutableStateFlow<Degree?>(Degree())
    val option = MutableStateFlow<Course?>(Course())

    init {
        insertSubjects()
        getSubjectList()
    }

    private fun getSubjectList() {
        val db = FirebaseDatabase.getInstance().getReference("subject")

        viewModelScope.launch {
            db.get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    // Deserialize the data into a list of Subject objects
                    val subjects: List<Subject> =
                        snapshot.children.mapNotNull { it.getValue(Subject::class.java) }

                    println("...........................................")
                    println(subjects)
                    _response.value = DataState.Success(subjects)

                    // Now you have a list of all subjects, you can process them
                    // For example, update the UI with the retrieved subjects
                } else {
                    // Handle the case where there is no data
                    println("No subjects found.")
                }
            }.addOnFailureListener { exception ->
                // Handle any errors (e.g., network issues, permission issues)
                println("Error retrieving data: ${exception.message}")
            }
        }

        // Retrieve all subjects from the "subject" node

    }

    private fun getData() {
        val db = FirebaseDatabase.getInstance().getReference("subject")
//        try {
//            db.addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    val subjectList = mutableListOf<Subject>()
//
//                    for (data in snapshot.children) {
//                        // Deserialize each child snapshot into a MathsPG object
//                        val subject = data.getValue(Subject::class.java)
//                        if (subject != null) {
//                            subjectList.add(subject)
//                        }
//                    }
//                    _response.value = DataState.Success(subjectList)
//                    println("Retrieved MathsPG list: $subjectList")
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    println("Failed to read value: ${error.toException()}")
//                }
//            })
//        } catch (e: Exception) {
//            println("Error: $e")
//        }

    }


//    private fun setData() {
//        val db = FirebaseDatabase.getInstance().getReference("subject")
//        db.child("Mathematic")
////
//            .setValue(
//
//                Subject(
//                    name = "Mathematic",
//                    degree = listOf(
//                        Degree(
//                            name = "Bachelor of Science in Mathematics",
//                            options = listOf(
//                                Course(
//                                    name = "Calculus",
//                                    description = "An introductory course in Calculus, covering limits, derivatives, and integrals.",
//                                    img = "url_to_calculus_image",
//                                    eligibility = "High school diploma with a background in math",
//                                    job_role = listOf(
//                                        "Data Analyst",
//                                        "Research Scientist",
//                                        "Statistician"
//                                    ),
//                                    salary = "45,000 - 70,000 USD",
//                                ),
//                                Course(
//                                    name = "Linear Algebra",
//                                    description = "A course on vector spaces, matrices, and linear transformations.",
//                                    img = "url_to_linear_algebra_image",
//                                    eligibility = "High school diploma with a background in math",
//                                    job_role = listOf(
//                                        "Financial Analyst",
//                                        "Machine Learning Engineer"
//                                    ),
//                                    salary = "50,000 - 75,000 USD"
//                                )
//                            )
//                        ),
//                        Degree(
//                            name = "Master of Science in Mathematics",
//                            options = listOf(
//                                Course(
//                                    name = "Advanced Calculus",
//                                    description = "An advanced course in Calculus covering complex integration and differential equations.",
//                                    img = "url_to_advanced_calculus_image",
//                                    eligibility = "Bachelor's degree in Mathematics or related field",
//                                    job_role = listOf(
//                                        "Quantitative Analyst",
//                                        "Operations Research Analyst"
//                                    ),
//                                    salary = "65,000 - 90,000 USD"
//                                ),
//                                Course(
//                                    name = "Abstract Algebra",
//                                    description = "A deep dive into group theory, rings, and fields.",
//                                    img = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSTlcBNWlgRnTCU4NDjr02s9FhbgazKzy88yA&s",
//                                    eligibility = "Bachelor's degree in Mathematics or related field",
//                                    job_role = listOf("Cryptographer", "Research Scientist"),
//                                    salary = "70,000 - 100,000 USD"
//                                )
//                            )
//                        ),
//                        Degree(
//                            name = "Diploma Cources",
//                            options = listOf(
//                                Course(
//                                    name = "Advanced Calculus",
//                                    description = "An advanced course in Calculus covering complex integration and differential equations.",
//                                    img = "url_to_advanced_calculus_image",
//                                    eligibility = "Bachelor's degree in Mathematics or related field",
//                                    job_role = listOf(
//                                        "Quantitative Analyst",
//                                        "Operations Research Analyst"
//                                    ),
//                                    salary = "65,000 - 90,000 USD"
//                                ),
//                                Course(
//                                    name = "Abstract Algebra",
//                                    description = "A deep dive into group theory, rings, and fields.",
//                                    img = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSTlcBNWlgRnTCU4NDjr02s9FhbgazKzy88yA&s",
//                                    eligibility = "Bachelor's degree in Mathematics or related field",
//                                    job_role = listOf("Cryptographer", "Research Scientist"),
//                                    salary = "70,000 - 100,000 USD"
//                                )
//                            )
//                        ),
//                        Degree(
//                            name = "Certification Courses",
//                            options = listOf(
//                                Course(
//                                    name = "Advanced Calculus",
//                                    description = "An advanced course in Calculus covering complex integration and differential equations.",
//                                    img = "url_to_advanced_calculus_image",
//                                    eligibility = "Bachelor's degree in Mathematics or related field",
//                                    job_role = listOf(
//                                        "Quantitative Analyst",
//                                        "Operations Research Analyst"
//                                    ),
//                                    salary = "65,000 - 90,000 USD"
//                                ),
//                                Course(
//                                    name = "Abstract Algebra",
//                                    description = "A deep dive into group theory, rings, and fields.",
//                                    img = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSTlcBNWlgRnTCU4NDjr02s9FhbgazKzy88yA&s",
//                                    eligibility = "Bachelor's degree in Mathematics or related field",
//                                    job_role = listOf("Cryptographer", "Research Scientist"),
//                                    salary = "70,000 - 100,000 USD"
//                                )
//                            )
//                        )
//                    )
//                )
//
//
//            ).addOnSuccessListener {
//                println("success")
//            }.addOnFailureListener {
//                println("failed $it")
//            }
//
//    }

    private fun insertSubjects() {
        val db = FirebaseDatabase.getInstance().getReference("subject")
        db.child("Arts")
            .setValue(
                Subject(
                    name = "Arts",
                    degree = listOf(
                        Degree(img = "https://st3.depositphotos.com/9034578/15912/v/450/depositphotos_159120090-stock-illustration-joy-potato-character-cartoon-style.jpg",
                            name = "Certification Courses",
                            options = listOf(
                                Course(
                                    name = "Calculus Essentials",
                                    img = "url_to_calculus_image",
                                    introduction = listOf("Introduction to calculus concepts like limits, derivatives, and integrals."),
                                    eligibility = listOf(
                                        "High school diploma",
                                        "Background in mathematics"
                                    ),
                                    entryPath = listOf(
                                        "Online registration",
                                        "Payment of course fees"
                                    ),
                                    institutions = listOf("Coursera", "edX"),
                                    jobRoles = listOf("Data Analyst", "Research Scientist"),
                                    workPlace = "Educational institutions, Research centers",
                                    expectedSalary = "40,000 - 65,000 USD",
                                    fees = "5000 INR"
                                ),
                                Course(
                                    name = "Basics of Linear Algebra",
                                    img = "url_to_linear_algebra_image",
                                    introduction = listOf("Fundamental concepts of matrices, vectors, and their applications."),
                                    eligibility = listOf(
                                        "High school diploma",
                                        "Background in mathematics"
                                    ),
                                    entryPath = listOf(
                                        "Online application",
                                        "Basic math assessment"
                                    ),
                                    institutions = listOf("Udemy", "Alison"),
                                    jobRoles = listOf(
                                        "Financial Analyst",
                                        "Machine Learning Engineer"
                                    ),
                                    workPlace = "Finance, Technology",
                                    expectedSalary = "45,000 - 70,000 USD",
                                    fees = "6000 INR"
                                ),
                                Course(
                                    name = "Statistics for Beginners",
                                    img = "url_to_statistics_image",
                                    introduction = listOf("Basics of probability, statistical distributions, and data analysis."),
                                    eligibility = listOf(
                                        "High school diploma",
                                        "Background in statistics"
                                    ),
                                    entryPath = listOf(
                                        "Online registration",
                                        "Payment of course fees"
                                    ),
                                    institutions = listOf("edX", "FutureLearn"),
                                    jobRoles = listOf("Data Scientist", "Statistician"),
                                    workPlace = "Research, Data analysis firms",
                                    expectedSalary = "50,000 - 75,000 USD",
                                    fees = "5000 INR"
                                ),
                                Course(
                                    name = "Introduction to Discrete Mathematics",
                                    img = "url_to_discrete_math_image",
                                    introduction = listOf("Graph theory, logic, and combinatorial analysis."),
                                    eligibility = listOf(
                                        "High school diploma",
                                        "Basic math knowledge"
                                    ),
                                    entryPath = listOf(
                                        "Online registration",
                                        "Submission of identification documents"
                                    ),
                                    institutions = listOf("Coursera", "Khan Academy"),
                                    jobRoles = listOf("Software Developer", "Cryptographer"),
                                    workPlace = "Technology firms, Cybersecurity",
                                    expectedSalary = "50,000 - 80,000 USD",
                                    fees = "4500 INR"
                                ),
                                Course(
                                    name = "Introduction to Data Science using Mathematics",
                                    img = "url_to_data_science_image",
                                    introduction = listOf("Application of mathematical methods in data analysis and data science."),
                                    eligibility = listOf(
                                        "High school diploma",
                                        "Background in math or statistics"
                                    ),
                                    entryPath = listOf("Online registration", "Fee payment"),
                                    institutions = listOf("Udemy", "Coursera"),
                                    jobRoles = listOf("Data Analyst", "Machine Learning Engineer"),
                                    workPlace = "Tech firms, Data science companies",
                                    expectedSalary = "60,000 - 85,000 USD",
                                    fees = "7000 INR"
                                ),
                                Course(
                                    name = "Fundamentals of Probability",
                                    img = "url_to_probability_image",
                                    introduction = listOf("Probability theory basics, random variables, and expected value."),
                                    eligibility = listOf(
                                        "High school diploma",
                                        "Background in math"
                                    ),
                                    entryPath = listOf("Online registration", "Basic math test"),
                                    institutions = listOf("Coursera", "edX"),
                                    jobRoles = listOf("Data Scientist", "Risk Analyst"),
                                    workPlace = "Finance, Research organizations",
                                    expectedSalary = "55,000 - 75,000 USD",
                                    fees = "5000 INR"
                                )
                            )
                        ),
                        Degree(
                            img = "https://st3.depositphotos.com/9034578/15912/v/450/depositphotos_159120090-stock-illustration-joy-potato-character-cartoon-style.jpg",
                            name = "Diploma Courses",
                            options = listOf(
                                Course(
                                    name = "Applied Calculus",
                                    img = "url_to_applied_calculus_image",
                                    introduction = listOf("Applications of calculus in science, engineering, and economics."),
                                    eligibility = listOf(
                                        "High school diploma",
                                        "Background in mathematics"
                                    ),
                                    entryPath = listOf(
                                        "Application submission",
                                        "Course fee payment"
                                    ),
                                    institutions = listOf("IGNOU", "Symbiosis"),
                                    jobRoles = listOf(
                                        "Quantitative Analyst",
                                        "Operations Research Analyst"
                                    ),
                                    workPlace = "Corporate sector, Research organizations",
                                    expectedSalary = "55,000 - 75,000 USD",
                                    fees = "15,000 INR"
                                ),
                                Course(
                                    name = "Discrete Mathematics",
                                    img = "url_to_discrete_math_image",
                                    introduction = listOf("Combinatorics, graph theory, and mathematical logic, relevant for computer science."),
                                    eligibility = listOf(
                                        "High school diploma",
                                        "Basic math knowledge"
                                    ),
                                    entryPath = listOf("Submit application", "Entrance exam"),
                                    institutions = listOf("Jain University", "NIT Delhi"),
                                    jobRoles = listOf("Software Developer", "Cryptographer"),
                                    workPlace = "Tech industry, Cybersecurity",
                                    expectedSalary = "50,000 - 80,000 USD",
                                    fees = "14,000 INR"
                                ),
                                Course(
                                    name = "Probability and Statistics",
                                    img = "url_to_statistics_image",
                                    introduction = listOf("Probability theory, inferential statistics, and hypothesis testing."),
                                    eligibility = listOf(
                                        "High school diploma",
                                        "Basic math background"
                                    ),
                                    entryPath = listOf("Submit application", "Interview"),
                                    institutions = listOf("BITS Pilani", "IISER Pune"),
                                    jobRoles = listOf("Data Scientist", "Market Analyst"),
                                    workPlace = "Finance, Research institutions",
                                    expectedSalary = "60,000 - 85,000 USD",
                                    fees = "16,000 INR"
                                )
                            )
                        ),
                        Degree(
                            name = "Undergraduate Courses",
                            options = listOf(
                                Course(
                                    name = "Bachelor in Mathematics",
                                    img = "url_to_bachelor_math_image",
                                    introduction = listOf("Comprehensive study of mathematics theories, methods, and applications."),
                                    eligibility = listOf(
                                        "High school with 50% in math",
                                        "Entrance exam"
                                    ),
                                    entryPath = listOf(
                                        "Apply through university portal",
                                        "Entrance test"
                                    ),
                                    institutions = listOf("Delhi University", "IIT Bombay"),
                                    jobRoles = listOf("Teacher", "Research Assistant", "Actuary"),
                                    workPlace = "Colleges, Financial sectors",
                                    expectedSalary = "45,000 - 60,000 USD",
                                    fees = "1,50,000 INR"
                                ),
                                Course(
                                    name = "Statistics",
                                    img = "url_to_statistics_course_image",
                                    introduction = listOf("Collection, analysis, and interpretation of numerical data."),
                                    eligibility = listOf(
                                        "High school diploma",
                                        "Strong math skills"
                                    ),
                                    entryPath = listOf("Apply online", "Entrance exam"),
                                    institutions = listOf("IISc Bangalore", "Delhi University"),
                                    jobRoles = listOf("Data Analyst", "Statistician"),
                                    workPlace = "Research, Government services",
                                    expectedSalary = "50,000 - 70,000 USD",
                                    fees = "1,40,000 INR"
                                ),
                                Course(
                                    name = "Applied Mathematics",
                                    img = "url_to_applied_math_image",
                                    introduction = listOf("Applying mathematical methods to practical fields like physics and engineering."),
                                    eligibility = listOf(
                                        "High school with a math focus",
                                        "Entrance test"
                                    ),
                                    entryPath = listOf("University application", "Admission test"),
                                    institutions = listOf("Anna University", "Osmania University"),
                                    jobRoles = listOf(
                                        "Operations Analyst",
                                        "Quantitative Researcher"
                                    ),
                                    workPlace = "Industries, Academic institutions",
                                    expectedSalary = "60,000 - 80,000 USD",
                                    fees = "1,60,000 INR"
                                )
                                // More courses would follow
                            )
                        ),
                        Degree(
                            name = "Postgraduate Courses",
                            options = listOf(
                                Course(
                                    name = "Master in Mathematics",
                                    img = "url_to_master_math_image",
                                    introduction = listOf("Advanced studies in mathematical theories and research."),
                                    eligibility = listOf(
                                        "Bachelor's in mathematics or related field",
                                        "GATE exam"
                                    ),
                                    entryPath = listOf(
                                        "University application",
                                        "Entrance test and interview"
                                    ),
                                    institutions = listOf("IIT Delhi", "IIT Kanpur"),
                                    jobRoles = listOf("Mathematician", "Quantitative Analyst"),
                                    workPlace = "Research, Academia",
                                    expectedSalary = "80,000 - 120,000 USD",
                                    fees = "2,50,000 INR"
                                ),
                                Course(
                                    name = "Applied Mathematics",
                                    img = "url_to_applied_math_master_image",
                                    introduction = listOf("Advanced mathematical methods applied to practical industry problems."),
                                    eligibility = listOf("Bachelor's in applied mathematics or engineering"),
                                    entryPath = listOf("Application", "Interview"),
                                    institutions = listOf("BITS Pilani", "University of Hyderabad"),
                                    jobRoles = listOf(
                                        "Financial Analyst",
                                        "Operations Research Analyst"
                                    ),
                                    workPlace = "Corporate, Research centers",
                                    expectedSalary = "85,000 - 115,000 USD",
                                    fees = ""
                                )
                            )
                        )
                    )
                )
            ).addOnSuccessListener {



            }
    }
}