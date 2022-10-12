package com.swhite.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var currentPosition: Int = 1
    private var questionsList:ArrayList<Question>? = null
    private var selectedOptionPosition:Int = 0

    private var userName:String? = null
    private var correctAnswers: Int = 0

    private var progressBar : ProgressBar? = null
    private var progressTextView : TextView? = null
    private var questionTextView: TextView? = null
    private var imageView: ImageView? = null

    private var optionOneTextView: TextView? = null
    private var optionTwoTextView: TextView? = null
    private var optionThreeTextView: TextView? = null
    private var optionFourTextView: TextView? = null
    private var submitButton:Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        userName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        progressTextView = findViewById(R.id.progressTextView)
        questionTextView = findViewById(R.id.questionTextView)
        imageView = findViewById(R.id.flagImageView)

        optionOneTextView = findViewById(R.id.optionOneTextView)
        optionTwoTextView = findViewById(R.id.optionTwoTextView)
        optionThreeTextView = findViewById(R.id.optionThreeTextView)
        optionFourTextView = findViewById(R.id.optionFourTextView)
        submitButton = findViewById(R.id.submitButton)

        optionOneTextView?.setOnClickListener(this)
        optionTwoTextView?.setOnClickListener(this)
        optionThreeTextView?.setOnClickListener(this)
        optionFourTextView?.setOnClickListener(this)
        submitButton?.setOnClickListener(this)

        questionsList = Constants.getQuestions()

        setQuestion()

    }

    private fun setQuestion() {
        //Reset options from previous questions.
        defaultOptionsView()

        val question: Question = questionsList!![currentPosition - 1]

        imageView?.setImageResource(question.image)

        progressBar?.progress = currentPosition
        progressTextView?.text = "$currentPosition / ${progressBar?.max}"
        questionTextView?.text = question.question

        optionOneTextView?.text = question.optionOne
        optionTwoTextView?.text = question.optionTwo
        optionThreeTextView?.text = question.optionThree
        optionFourTextView?.text = question.optionFour

        if(currentPosition == questionsList!!.size) {
            submitButton?.text = "FINISH"
        } else {
            submitButton?.text = "SUBMIT"
        }
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        optionOneTextView?.let {
            options.add(0, it)
        }
        optionTwoTextView?.let {
            options.add(1, it)
        }
        optionThreeTextView?.let {
            options.add(2, it)
        }
        optionFourTextView?.let {
            options.add(3, it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(tv:TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        selectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    override fun onClick(view: View?) {
        Log.d("TEST123", "onclock")
        when(view?.id) {
            R.id.optionOneTextView -> {
                optionOneTextView?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.optionTwoTextView -> {
                optionTwoTextView?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.optionThreeTextView -> {
                optionThreeTextView?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.optionFourTextView -> {
                optionFourTextView?.let {
                    selectedOptionView(it, 4)
                }
            }
            R.id.submitButton -> {
                    if (selectedOptionPosition == 0) {
                        currentPosition++

                        when {
                            currentPosition <= questionsList!!.size -> {
                                setQuestion()
                            }
                            else -> {
                                val intent = Intent(this, ResultActivity::class.java)
                                intent.putExtra(Constants.USER_NAME, userName)
                                intent.putExtra(Constants.CORRECT_ANSWERS, correctAnswers)
                                intent.putExtra(Constants.TOTAL_QUESTIONS, questionsList?.size)
                                startActivity(intent)
                                finish()
                            }
                        }
                    } else {
                        val question = questionsList?.get(currentPosition - 1)
                        if (question!!.correctAnswer != selectedOptionPosition) {
                            answerView(selectedOptionPosition, R.drawable.wrong_option_border_bg)
                        } else {
                            correctAnswers++
                        }
                        answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                        if(currentPosition == questionsList!!.size) {
                            submitButton?.text = "FINISH"
                        } else {
                            submitButton?.text = "GO TO NEXT QUESTION"
                        }

                        selectedOptionPosition = 0


                    }
            }
        }
    }

    private fun answerView(answer:Int, drawableView:Int) {
        when(answer) {
            1 -> {
                optionOneTextView?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                optionTwoTextView?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                optionThreeTextView?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                optionFourTextView?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

        }
    }
}