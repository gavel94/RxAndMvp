package com.daogenerator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class App
{
    private static Schema schema;

    public static void main(String[] args) throws Exception
    {
        schema = new Schema(1, "cn.jiahua.bean");
        schema.setDefaultJavaPackageDao("cn.jiahua.dao");

        createContacts();

        new DaoGenerator().generateAll(schema, args[0]);
    }

    private static void createContacts()
    {
        Entity contacts = schema.addEntity("Contacts");
        contacts.setTableName("CONTACTS");
        contacts.addStringProperty("id").primaryKey().notNull();
        contacts.addStringProperty("realName");
        contacts.addStringProperty("roleId");
        contacts.addStringProperty("userNo");
        contacts.addStringProperty("mobile");
        contacts.addBooleanProperty("sex");
        contacts.addStringProperty("avatar");
        contacts.addStringProperty("userName");
        contacts.addStringProperty("roleName");
    }
}
