package com.example.procurementconstructionindustry.database;


public class DatabaseTable {

    public class User{
        /*restrict creating objects*/
        private User(){}

        /*Table name*/
        public static final String TABLE_NAME = "user";

        /*Table properties*/
        public static final String USER_ID = "user_id";
        public static final String USER_NAME = "user_name";
        public static final String USER_LEVEL = "user_level";
        public static final String USER_PASSWORD = "user_password";

        /*Create String*/
        public static final String CREATE_TABLE_STRING = "create table if not exists "+TABLE_NAME +
                " ("+USER_ID+" integer not null primary key autoincrement,"
                + USER_NAME + " varchar(60) not null ,"
                + USER_LEVEL + " integer not null , "
                + USER_PASSWORD + " varchar(255) not null )";

    }

    public class PurchaseOrder{
        /*restrict creating objects*/
        private PurchaseOrder(){}

        /*Table name*/
        public static final String TABLE_NAME = "purchase_order";

        /*Table properties*/
        public static final String ORDER_ID = "order_id";
        public static final String ORDER_SUPPLER = "order_suppler";
        public static final String ORDER_PLACEDBY = "order_placedby";
        public static final String ORDER_DELIVERYADDRESS = "order_deliveryaddress";
        public static final String ORDER_STATUS = "order_status";

        /*Create String*/
        public static final String CREATE_TABLE_STRING = "create table if not exists "+TABLE_NAME +
                " ("+ORDER_ID+" integer not null primary key autoincrement,"
                + ORDER_SUPPLER + " integer not null ,"
                + ORDER_PLACEDBY + " integer not null , "
                + ORDER_DELIVERYADDRESS + " text not null ,"
                + ORDER_STATUS + " varchar(30) not null , "
                + " foreign key("+ORDER_SUPPLER+") references "+User.TABLE_NAME+"("+User.USER_ID+") on delete cascade on update cascade) "
                + " foreign key("+ORDER_PLACEDBY+") references "+User.TABLE_NAME+"("+User.USER_ID+") on delete cascade on update cascade) ";
    }

    public class Item{
        /*restrict creating objects*/
        private Item(){}

        /*Table name*/
        public static final String TABLE_NAME = "item";

        /*Table properties*/
        public static final String ITEM_ID = "item_id";
        public static final String ITEM_NAME = "item_name";
        public static final String ITEM_QUANTITY = "item_quantity";
        public static final String ITEM_AGREED_UNIT_PRICE = "item_agreed_unit_price";

        /*Create String*/
        public static final String CREATE_TABLE_STRING = "create table if not exists "+TABLE_NAME +
                " ("+ITEM_ID+" integer not null primary key autoincrement,"
                + ITEM_NAME + " varchar(60) not null ,"
                + ITEM_QUANTITY + " integer not null , "
                + ITEM_AGREED_UNIT_PRICE + " float not null )";

    }

    public class PurchaseOrderItem{

        /*restrict creating objects*/
        private PurchaseOrderItem(){};

        /*Table name*/
        public static final String TABLE_NAME = "purchaseorderitem";

        /*Table properties*/
        public static final String ORDER_ID = "order_id";
        public static final String ITEM_ID = "item_id";

        /*Create String*/
        public static final String CREATE_TABLE_STRING = "create table if not exists "+TABLE_NAME+" (" +
                " "+ORDER_ID+" integer not null, " +
                " "+ITEM_ID+" integer not null , " +
                " foreign key("+ORDER_ID+") references "+PurchaseOrder.TABLE_NAME+"("+PurchaseOrder.ORDER_ID+") on delete cascade on update cascade, " +
                " foreign key("+ITEM_ID+") references "+Item.ITEM_ID+"("+Item.ITEM_ID+") on delete cascade on update cascade , " +
                " primary key("+ORDER_ID+" , "+ITEM_ID+"))";

    }

//    /*Patient Table*/
//    public class Patient{
//
//        /*restrict creating objects*/
//        private Patient(){}
//
//        /*Table name*/
//        public static final String TABLE_NAME = "patient";
//
//        /*Table properties*/
//        public static final String PATIENT_ID = "pid";
//        public static final String PATIENT_FIRST_NAME = "first_name";
//        public static final String PATIENT_LAST_NAME = "last_name";
//        public static final String PATIENT_DOB = "dob"; /*Format (dd/mm/yyyy)*/
//        public static final String PATIENT_REASON = "reason";
//        public static final String PATIENT_DATE_ADMITTED = "date_admitted";
//        public static final String PATIENT_ADDITIONAL_INFO = "additional_info";
//
//        /*Create String*/
//        public static final String CREATE_TABLE_STRING = "create table if not exists "+TABLE_NAME +
//                " ("+PATIENT_ID+" integer not null primary key autoincrement,"
//                + PATIENT_FIRST_NAME + " varchar(60) not null ,"
//                + PATIENT_LAST_NAME + " varchar(60) not null , "
//                + PATIENT_DOB + " date not null ,"
//                + PATIENT_REASON + " text not null , "
//                + PATIENT_DATE_ADMITTED + " date not null , "
//                + PATIENT_ADDITIONAL_INFO + " text )";
//
//
//    }
//
//    /*Guardian Table*/
//    public class Guardian{
//
//        /*restrict creating objects*/
//        private Guardian(){}
//
//        /*Table name*/
//        public static final String TABLE_NAME = "guardian";
//
//        /*Table properties*/
//        public static final String GUARDIAN_ID = "gid";
//        public static final String GUARDIAN_NAME = "name";
//        public static final String GUARDIAN_ADDRESS = "address";
//        public static final String GUARDIAN_CONTACT_NUMBER = "contact_number";
//        public static final String PATIENT_ID_FK = "pid";
//
//        /*Create String*/
//        public static final String CREATE_TABLE_STRING = "create table if not exists " +TABLE_NAME +
//                "( "+GUARDIAN_ID+" integer not null primary key  autoincrement, " +
//                " "+GUARDIAN_NAME+" varchar(60) not null , " +
//                " "+GUARDIAN_ADDRESS+" text not null , " +
//                " "+GUARDIAN_CONTACT_NUMBER+" varchar(10) not null , " +
//                " "+PATIENT_ID_FK+" integer," +
//                " foreign key("+PATIENT_ID_FK+") references "+Patient.TABLE_NAME+"("+Patient.PATIENT_ID+") on delete cascade on update cascade) ";
//
//    }
//
//
//    /*Bed Head Table*/
//    public class BeaHeadCard{
//
//        /*restrict creating objects*/
//        private BeaHeadCard(){}
//
//        /*Table name*/
//        public static final String TABLE_NAME = "bed_head_card";
//
//        /*Table properties*/
//        public static final String CARD_ID = "id";
//        public static final String CARD_DATE = "card_date";
//        public static final String CARD_BLOOD_PRESSURE = "blood_pressure";
//        public static final String CARD_SUGAR_LEVEL = "sugar_level";
//        public static final String CARD_DIAGNOSIS = "diagnosis";
//        public static final String PATIENT_ID_FK = "pid";
//
//        /*Create String*/
//        public static final String CREATE_TABLE_STRING = "create table if not exists "+TABLE_NAME+"" +
//                "("+CARD_ID+" integer not null primary key autoincrement , " +
//                " "+CARD_DATE+" date not null , " +
//                " "+CARD_BLOOD_PRESSURE+" real not null , " +
//                " "+CARD_SUGAR_LEVEL+" real not null , " +
//                " "+CARD_DIAGNOSIS+" text , " +
//                " "+PATIENT_ID_FK+" integer not null , " +
//                " foreign key("+PATIENT_ID_FK+") references "+Patient.TABLE_NAME+"("+Patient.PATIENT_ID+") on delete cascade on update cascade )";
//
//
//    }
//
//
//    /*meal plan Table*/
//    public class MealPlan{
//
//        /*restrict creating objects*/
//        private MealPlan(){}
//
//        /*Table name*/
//        public static final String TABLE_NAME = "meal_plan";
//
//        /*Table properties*/
//        public static final String PLAN_ID = "id";
//        public static final String PLAN_NAME = "plan_name";
//        public static final String PLAN_TYPE = "plan_type";
//        public static final String PLAN_DAY = "day";
//
//        /*Create String*/
//        public static final String CREATE_TABLE_STRING = "create table if not exists "+TABLE_NAME+" (" +
//                " "+PLAN_ID+" integer not null primary key autoincrement , " +
//                " "+PLAN_NAME+" varchar(60) not null , " +
//                " "+PLAN_TYPE+" varchar(60) not null , " +
//                " "+PLAN_DAY+" varchar(20) not null)";
//
//
//    }
//
//    /*portion Table*/
//    public class Portion{
//
//        /*restrict creating objects*/
//        private Portion(){}
//
//        /*Table name*/
//        public static final String TABLE_NAME = "portion";
//
//        /*Table properties*/
//        public static final String PORTION_ID = "id";
//        public static final String PORTION_NAME = "portion_name";
//        public static final String PORTION_SIZE = "size";
//        public static final String PLAN_ID_FK = "plan_id";
//
//        /*Create String*/
//        public static final String CREATE_TABLE_STRING= "create table if not exists "+TABLE_NAME+"(" +
//                " "+PORTION_ID+" integer not null primary key autoincrement , " +
//                " "+PORTION_NAME+" varchar(60) not null , " +
//                " "+PORTION_SIZE+" varchar(60) not null , " +
//                " "+PLAN_ID_FK+" integer not null ," +
//                " foreign key("+PLAN_ID_FK+") references "+MealPlan.TABLE_NAME+"("+MealPlan.PLAN_ID+") on delete cascade on update cascade)";
//
//
//    }
//
//    /*patent many to may table Table*/
//    public class PatentHasMealPlan{
//
//        /*restrict creating objects*/
//        private PatentHasMealPlan(){}
//
//        /*Table name*/
//        public static final String TABLE_NAME = "patent_has_meal_plan";
//
//        /*Table properties*/
//        public static final String PATENT_ID = "pid";
//        public static final String MEAL_PLAN_ID = "plan_id";
//
//        /*Create String*/
//        public static final String CREATE_TABLE_STRING = "create table if not exists "+TABLE_NAME+" (" +
//                " "+PATENT_ID+" integer not null, " +
//                " "+MEAL_PLAN_ID+" integer not null , " +
//                " foreign key("+PATENT_ID+") references "+Patient.TABLE_NAME+"("+Patient.PATIENT_ID+") on delete cascade on update cascade, " +
//                " foreign key("+MEAL_PLAN_ID+") references "+MealPlan.TABLE_NAME+"("+MealPlan.PLAN_ID+") on delete cascade on update cascade , " +
//                " primary key("+PATENT_ID+" , "+MEAL_PLAN_ID+"))";
//
//
//    }
//
//
//    /*Workout Plan Table*/
//    public class WorkoutPlan{
//
//        /*restrict creating objects*/
//        private WorkoutPlan(){}
//
//        /*Table name*/
//        public static final String TABLE_NAME = "workout_plan";
//
//        /*Table properties*/
//        public static final String PLAN_ID = "id";
//        public static final String PLAN_NAME = "plan_name";
//        public static final String PLAN_INSTRUCTOR = "instructor";
//        public static final String PATENT_ID = "pid";
//
//        /*Create String*/
//        public static final String CREATE_TABLE_STRING = "create table if not exists "+TABLE_NAME+"(" +
//                " "+PLAN_ID+" integer not null primary key autoincrement , " +
//                " "+PLAN_NAME+" varchar(60) not null , " +
//                " "+PLAN_INSTRUCTOR+" varchar(60) not null , " +
//                " "+PATENT_ID+" integer , " +
//                " foreign key("+PATENT_ID+") references "+Patient.TABLE_NAME+"("+Patient.PATIENT_ID+") on delete set null on update cascade )";
//
//    }
//
//
//    /*Exercise Table*/
//    public class Exercise{
//
//        /*restrict creating objects*/
//        private Exercise(){}
//
//        /*Table name*/
//        public static final String TABLE_NAME = "exercise";
//
//        /*Table properties*/
//        public static final String EXE_ID = "id";
//        public static final String EXE_NAME = "exercise_name";
//        public static final String EXE_AREA = "area";
//        public static final String EXE_STEPS = "steps";
//        public static final String PLAN_ID = "plan_id";
//
//        /*Create String*/
//        public static final String CREATE_TABLE_STRING = "create table if not exists "+TABLE_NAME+"(" +
//                " "+EXE_ID+" integer not null primary key autoincrement ," +
//                " "+EXE_NAME+" varchar(60) not null , " +
//                " "+EXE_AREA+" varchar(60) not null , " +
//                " "+EXE_STEPS+" varchar(60) not null ," +
//                " "+PLAN_ID+" integer not null , " +
//                " foreign key("+PLAN_ID+") references "+WorkoutPlan.TABLE_NAME+"("+WorkoutPlan.PLAN_ID+") on delete cascade on update cascade ) ";
//
//    }
//
//
//    /*Workout Table*/
//    public class WorkOutReport{
//
//        /*restrict creating objects*/
//        private WorkOutReport(){}
//
//        /*Table name*/
//        public static final String TABLE_NAME = "workout_report";
//
//        /*Table properties*/
//        public static final String REPORT_ID = "id";
//        public static final String REPORT_DATE = "date";
//        public static final String REPORT_MONTH = "month";
//        public static final String REPORT_WEIGHT = "weight";
//        public static final String PATIENT_ID = "pid";
//
//        /*Create String*/
//        public static final String CREATE_TABLE_STRING = "create table if not exists "+TABLE_NAME+"(" +
//                " "+REPORT_ID+" integer not null primary key autoincrement , " +
//                " "+REPORT_DATE+" date not null , " +
//                " "+REPORT_MONTH+" varchar(10) not null , " +
//                " "+REPORT_WEIGHT+" real not null ," +
//                " "+PATIENT_ID+" integer, " +
//                " foreign key("+PATIENT_ID+") references "+Patient.TABLE_NAME+"("+Patient.PATIENT_ID+") on delete set null on update cascade) ";
//
//    }
//
//
//    /*Prescription Table*/
//    public class Prescription{
//
//        /*restrict creating objects*/
//        private Prescription(){}
//
//        /*Table name*/
//        public static final String TABLE_NAME = "prescription";
//
//        /*Table properties*/
//        public static final String PRESC_ID = "id";
//        public static final String PRESC_DOCTOR_NAME = "doctor_name";
//        public static final String PRESC_DOC_MOH_NUMBER = "moh_number";
//        public static final String PRESC_DESCRIPTION = "description";
//        public static final String PRESC_TYPE = "type";
//        public static final String PRESC_ALLOCATED_DATE = "allocated_date";
//        public static final String PRESC_IS_CURRENT = "is_current";
//        public static final String PATIENT_ID = "pid";
//
//
//        /*Create String*/
//        public static final String CREATE_TABLE_STRING = "create table if not exists "+TABLE_NAME+"(" +
//                " "+PRESC_ID+" integer not null primary key autoincrement , " +
//                " "+PRESC_DOCTOR_NAME+" varchar(60) not null , " +
//                " "+PRESC_DOC_MOH_NUMBER+" varchar(10) not null , " +
//                " "+PRESC_DESCRIPTION+" text not null , " +
//                " "+PRESC_TYPE+" varchar(10) not null , " +
//                " "+PRESC_ALLOCATED_DATE+" date not null , " +
//                " "+PRESC_IS_CURRENT+" boolean not null default false , " +
//                " "+PATIENT_ID+" integer , " +
//                " foreign key("+PATIENT_ID+") references "+Patient.TABLE_NAME+"("+Patient.PATIENT_ID+") on delete set null on update cascade )";
//
//    }
//
//
//    /*Drug Table*/
//    public class Drug{
//
//        /*restrict creating objects*/
//        private Drug(){}
//
//        /*Table name*/
//        public static final String TABLE_NAME = "drug";
//
//        /*Table properties*/
//        public static final String DRUG_ID = "drug_id";
//        public static final String DRUG_NAME = "drug_name";
//        public static final String DRUG_BEFORE_MEAL = "before_meal";
//        public static final String PRESCRIPTION_ID = "prescription_id";
//
//        /*Create String*/
//        public static final String CREATE_TABLE_STRING = "create table if not exists "+TABLE_NAME+"(" +
//                " "+DRUG_ID+" integer not null primary key autoincrement , " +
//                " "+DRUG_NAME+" varchar(60) not null , " +
//                " "+DRUG_BEFORE_MEAL+" boolean not null default false , " +
//                " "+PRESCRIPTION_ID+" integer not null , " +
//                " foreign key("+PRESCRIPTION_ID+") references "+Prescription.TABLE_NAME+"("+Prescription.PRESC_ID+") on delete cascade on update cascade )";
//
//    }


}
