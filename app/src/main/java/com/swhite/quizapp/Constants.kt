package com.swhite.quizapp

object Constants {

    private const val questionTitle:String = "What country does this flag belong to?"

    const val USER_NAME:String = "user_name"
    const val TOTAL_QUESTIONS:String = "total_questions"
    const val CORRECT_ANSWERS:String = "correct_answers"

    fun getQuestions():ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val q1 = Question (
            1, questionTitle, R.drawable.ic_flag_of_argentina,
            "Argentina", "Australia", "Armenia", "Austria",
            1)

        questionsList.add(q1)

        val q2 = Question (
            2, questionTitle, R.drawable.ic_flag_of_australia,
            "New Zealand", "Belarus", "Australia", "Figi",
            3)

        questionsList.add(q2)

        val q3 = Question (
            3, questionTitle, R.drawable.ic_flag_of_belgium,
            "Italy", "Paraguay", "Poland", "Belgium",
            4)

        questionsList.add(q3)

        val q4 = Question (
            4, questionTitle, R.drawable.ic_flag_of_brazil,
            "Brazil", "Chile", "Barbados", "Spain",
            1)

        questionsList.add(q4)

        val q5 = Question (
            5, questionTitle, R.drawable.ic_flag_of_denmark,
            "Denmark", "Sweden", "Norway", "Poland",
            1)

        questionsList.add(q5)

        val q6 = Question (
            6, questionTitle, R.drawable.ic_flag_of_fiji,
            "New Zealand", "Madagascar", "Fiji", "Zimbabwe",
            3)

        questionsList.add(q6)

        val q7 = Question (
            7, questionTitle, R.drawable.ic_flag_of_germany,
            "Switzerland", "Germany", "Sweden", "Italy",
            2)

        questionsList.add(q7)

        val q8 = Question (
            8, questionTitle, R.drawable.ic_flag_of_india,
            "Pakistan", "Thailand", "India", "Vietnam",
            3)

        questionsList.add(q8)

        val q9 = Question (
            9, questionTitle, R.drawable.ic_flag_of_kuwait,
            "Ghana", "Kuwait", "Columbia", "DR Congo",
            2)

        questionsList.add(q9)

        val q10 = Question (
            10, questionTitle, R.drawable.ic_flag_of_new_zealand,
            "Samoa", "Switzerland", "Thailand", "New Zealand",
            4)

        questionsList.add(q10)

        return questionsList
    }
}