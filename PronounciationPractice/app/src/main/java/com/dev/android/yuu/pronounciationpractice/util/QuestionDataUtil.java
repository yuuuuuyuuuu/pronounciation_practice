package com.dev.android.yuu.pronounciationpractice.util;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Chieko on 8/15/14.
 */
public class QuestionDataUtil {

    public static ArrayList<String> GetQuestion(int level)
    {
        ArrayList<String> questions = new ArrayList<String>();

        switch (level)
        {
            case 1:
                questions.add("Idea");
                questions.add("Ear");
                questions.add("Newspaper");
                questions.add("Jump");
                questions.add("October");
                break;

            case 2:
                questions.add("Action");
                questions.add("Lemon");
                questions.add("Quiz");
                questions.add("University");
                questions.add("Understand");

                break;

            case 3:
                questions.add("Paint");
                questions.add("Department");
                questions.add("Hair");
                questions.add("Chicken");
                questions.add("Angry");
                questions.add("Tax");
                questions.add("Million");
                questions.add("Final");
                questions.add("Knowledge");
                questions.add("Haircut");
                break;

            case 4:
                questions.add("Classic");
                questions.add("Legal");
                questions.add("Observation");
                questions.add("Major");
                questions.add("Article");
                questions.add("Geography");
                questions.add("Weapon");
                questions.add("Economy");
                questions.add("Licence");
                questions.add("Visa");
                break;

            case 5:
                questions.add("Collapse");
                questions.add("Minority");
                questions.add("Sidewalk");
                questions.add("Transportation");
                questions.add("Gallery");
                questions.add("Paragraph");
                questions.add("Yield");
                questions.add("Development");
                questions.add("Resolution");
                questions.add("Injury");
                break;

            case 6:
                questions.add("Infant");
                questions.add("Logic");
                questions.add("Heavily");
                questions.add("Notion");
                questions.add("Quit");
                questions.add("Honesty");
                questions.add("engagement");
                questions.add("Guarantee");
                questions.add("Realistic");
                questions.add("Agriculture");
                break;

            case 7:
                questions.add("Pollution");
                questions.add("Laundry");
                questions.add("Minority");
                questions.add("Obviously");
                questions.add("Negotiation");
                questions.add("Amazing");
                questions.add("Guidebook");
                questions.add("Wildlife");
                questions.add("Promotion");
                questions.add("Kindergarten");
                break;

            case 8:
                questions.add("Idiot");
                questions.add("Moderate");
                questions.add("Headline");
                questions.add("Consumption");
                questions.add("Nightmare");
                questions.add("Technically");
                questions.add("Viewpoint");
                questions.add("Biology");
                questions.add("Embarrassing");
                questions.add("Ornament");
                break;

            case 9:
                questions.add("Register");
                questions.add("Literally");
                questions.add("Highlight");
                questions.add("Ridiculous");
                questions.add("Quotation");
                questions.add("Commitment");
                questions.add("Divorce");
                questions.add("Reluctant");
                questions.add("Transform");
                questions.add("Independent");
                break;

            case 10:
                questions.add("Regulation");
                questions.add("Package");
                questions.add("Incredible");
                questions.add("Illusion");
                questions.add("Reservation");
                questions.add("Motorbike");
                questions.add("Pillow");
                questions.add("Broadcasting");
                questions.add("Inspiration");
                questions.add("Missile");
                break;

            default:
                break;

        }

        return questions;
    }
}
