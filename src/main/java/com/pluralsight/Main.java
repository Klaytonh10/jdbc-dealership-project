package com.pluralsight;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Main {

    public static void main(String[] args) {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(args[0]);
        dataSource.setUser(args[1]);
        dataSource.setPassword(args[2]);

        //Load Cars into list
        UserInterface ui = new UserInterface();
        ui.display(dataSource);
    }
}