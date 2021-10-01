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
        public static final String ORDER_COMPANY = "order_company";
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
                + ORDER_COMPANY + " varchar(30) not null , "
                + "foreign key("+ORDER_SUPPLER+") references "+User.TABLE_NAME+"("+User.USER_ID+") on delete cascade on update cascade , "
                + "foreign key("+ORDER_PLACEDBY+") references "+User.TABLE_NAME+"("+User.USER_ID+") on delete cascade on update cascade) ";
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
                " "+ITEM_ID+" varchar(60) not null , " +
                " foreign key("+ORDER_ID+") references "+PurchaseOrder.TABLE_NAME+"("+PurchaseOrder.ORDER_ID+") on delete cascade on update cascade, " +
                " primary key("+ORDER_ID+" , "+ITEM_ID+"))";

    }

}
