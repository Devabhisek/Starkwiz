package com.starkwiz.starkwiz.ModelClass;

public class Quiz_Modelclass {

    String question_id,question,correct_answer,wrong_answer_1,wrong_answer_2,
            wrong_answer_3,wrong_answer_4,image,txt_qn_hint,explanation,mark;

    public Quiz_Modelclass(String question_id, String question, String correct_answer, String wrong_answer_1,
                           String wrong_answer_2, String wrong_answer_3, String wrong_answer_4, String image,
                           String txt_qn_hint, String explanation, String mark) {
        this.question_id = question_id;
        this.question = question;
        this.correct_answer = correct_answer;
        this.wrong_answer_1 = wrong_answer_1;
        this.wrong_answer_2 = wrong_answer_2;
        this.wrong_answer_3 = wrong_answer_3;
        this.wrong_answer_4 = wrong_answer_4;
        this.image = image;
        this.txt_qn_hint = txt_qn_hint;
        this.explanation = explanation;
        this.mark = mark;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getWrong_answer_1() {
        return wrong_answer_1;
    }

    public void setWrong_answer_1(String wrong_answer_1) {
        this.wrong_answer_1 = wrong_answer_1;
    }

    public String getWrong_answer_2() {
        return wrong_answer_2;
    }

    public void setWrong_answer_2(String wrong_answer_2) {
        this.wrong_answer_2 = wrong_answer_2;
    }

    public String getWrong_answer_3() {
        return wrong_answer_3;
    }

    public void setWrong_answer_3(String wrong_answer_3) {
        this.wrong_answer_3 = wrong_answer_3;
    }

    public String getWrong_answer_4() {
        return wrong_answer_4;
    }

    public void setWrong_answer_4(String wrong_answer_4) {
        this.wrong_answer_4 = wrong_answer_4;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTxt_qn_hint() {
        return txt_qn_hint;
    }

    public void setTxt_qn_hint(String txt_qn_hint) {
        this.txt_qn_hint = txt_qn_hint;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
