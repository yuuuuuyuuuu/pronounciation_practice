package com.dev.android.yuu.pronounciationpractice.util;

import java.util.ArrayList;

/**
 * Created by Chieko on 8/15/14.
 */
public class QuestionDataUtil {

    public static ArrayList<String> GetQuestion(int level)
    {
        ArrayList<String> questions = new ArrayList<String>();

        int TARGET_CLASS = 1; // 0:Beginner 1:Intermidiate 3:Advanced

        if(0 == TARGET_CLASS)
        {
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
        }
        else if(1 == TARGET_CLASS)
        {
            switch (level)
            {
                case 1:
                    questions.add("Abroad");
                    questions.add("Aspect");
                    questions.add("Deposit");
                    questions.add("Determination");
                    questions.add("Misunderstand");
                    break;

                case 2:
                    questions.add("Organize");
                    questions.add("Replacement");
                    questions.add("Recognition");
                    questions.add("Extension");
                    questions.add("Corporation");
                    break;

                case 3:
                    questions.add("Translation");
                    questions.add("Temptation");
                    questions.add("Investigation");
                    questions.add("Independence");
                    questions.add("Volunteer");
                    questions.add("Extraordinary");
                    questions.add("Emergency");
                    questions.add("Fundamental");
                    questions.add("Flavor");
                    questions.add("Gamble");
                    break;

                case 4:
                    questions.add("Inspector");
                    questions.add("Isolated");
                    questions.add("Revolutionary");
                    questions.add("Reputation");
                    questions.add("Biological");
                    questions.add("Abandoned");
                    questions.add("Appreciation");
                    questions.add("Conservative");
                    questions.add("Optimistic");
                    questions.add("Jealously");
                    break;

                case 5:
                    questions.add("Justification");
                    questions.add("Entertaining");
                    questions.add("Exhaustion");
                    questions.add("Hesitation");
                    questions.add("Grammatical");
                    questions.add("Bounce");
                    questions.add("Blueprint");
                    questions.add("Violation");
                    questions.add("Identification");
                    questions.add("Instrument");
                    break;

                case 6:
                    questions.add("Irritating");
                    questions.add("Annoying");
                    questions.add("Refreshment");
                    questions.add("Multimedia");
                    questions.add("Manuscript");
                    questions.add("Ultimate");
                    questions.add("Embarrassment");
                    questions.add("Authority");
                    questions.add("Discrimination");
                    questions.add("Exceptional");
                    break;

                case 7:
                    questions.add("Junction");
                    questions.add("Exclamation");
                    questions.add("Eccentric");
                    questions.add("Inconvenient");
                    questions.add("Illumination");
                    questions.add("Forbidden");
                    questions.add("Furnished");
                    questions.add("Telecommunication");
                    questions.add("Theoretical");
                    questions.add("Constitutional");
                    break;

                case 8:
                    questions.add("Correspondence");
                    questions.add("Comprehensive");
                    questions.add("Rehabilitation");
                    questions.add("Wheelchair");
                    questions.add("Subscription");
                    questions.add("Sentimental");
                    questions.add("handicraft");
                    questions.add("Homecoming");
                    questions.add("Gigantic");
                    questions.add("Wreck");
                    break;

                case 9:
                    questions.add("Vacancy");
                    questions.add("Virtually");
                    questions.add("Merchandise");
                    questions.add("Meditation");
                    questions.add("Application");
                    questions.add("Atmosphere");
                    questions.add("Bankruptcy");
                    questions.add("Complement");
                    questions.add("Dictation");
                    questions.add("Distribution");
                    break;

                case 10:
                    questions.add("Duration");
                    questions.add("Emission");
                    questions.add("Gordian");
                    questions.add("Installation");
                    questions.add("Interference");
                    questions.add("Inclusive");
                    questions.add("Liability");
                    questions.add("Monotonous");
                    questions.add("Operating");
                    questions.add("Pessimist");
                    break;

                default:
                    break;

            }
        }
        else if(2 == TARGET_CLASS)
        {

        }
        else
        {

        }


        return questions;
    }

}
