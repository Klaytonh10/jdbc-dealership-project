package com.yearup.Contracting;

import java.io.*;

public class ContractFileManager {

    private String file = "src/main/resources/contracts.csv";

    public void addContract(Contract c) throws IOException {

        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter writer = new BufferedWriter(fileWriter);

    }
}
