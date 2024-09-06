package com.checkr.interviews;

import java.util.*;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;

public class FundingRaised {
    public static List<Map<String, String>> where(Map<String, String> options) throws IOException {
        List<String[]> csvData = new ArrayList<String[]>();
        CSVReader reader = new CSVReader(new FileReader("startup_funding.csv"));
        String[] row = null;

        while((row = reader.readNext()) != null) {
            csvData.add(row);
        }

        reader.close();
        csvData.remove(0);

        if(options.containsKey("company_name")) {
            csvData = addCSVData(csvData, options, OptionKey.company_name);
        }

        if(options.containsKey("city")) {
            csvData = addCSVData(csvData, options, OptionKey.city);
        }

        if(options.containsKey("state")) {
            csvData = addCSVData(csvData, options, OptionKey.state);
        }

        if(options.containsKey("round")) {
            csvData = addCSVData(csvData, options, OptionKey.round);
        }

        List<Map<String, String>> output = new ArrayList<Map<String, String>>();

        for(int i = 0; i < csvData.size(); i++) {
            Map<String, String> mapped = new HashMap<String, String> ();
            complementCSVMapData(csvData, options, i, mapped);
            output.add(mapped);
        }

        return output;
    }

    public static Map<String, String> findBy(Map<String, String> options) throws IOException, NoSuchEntryException {
        List<String[]> csvData = new ArrayList<String[]>();
        CSVReader reader = new CSVReader(new FileReader("startup_funding.csv"));
        String[] row = null;

        while((row = reader.readNext()) != null) {
            csvData.add(row);
        }

        reader.close();
        csvData.remove(0);
        Map<String, String> mapped = new HashMap<String, String> ();

        for(int i = 0; i < csvData.size(); i++) {
            if(options.containsKey("company_name")) {
                if(csvData.get(i)[OptionKey.company_name.getArrayPosition()].equals(options.get(OptionKey.company_name.getOptionkey()))) {
                    complementCSVMapData(csvData, options, i, mapped);
                } else {
                    continue;
                }
            }
            
            if (options.containsKey("city")) {
                if(csvData.get(i)[OptionKey.city.getArrayPosition()].equals(options.get(OptionKey.city.getOptionkey()))) {
                    complementCSVMapData(csvData, options, i, mapped);
                } else {
                    continue;
                }
            }
            
            if(options.containsKey("state")) {
                if(csvData.get(i)[OptionKey.state.getArrayPosition()].equals(options.get(OptionKey.state.getOptionkey()))) {
                    complementCSVMapData(csvData, options, i, mapped);
                } else {
                    continue;
                }
            } 
            
            if(options.containsKey("round")) {
                if(csvData.get(i)[OptionKey.round.getArrayPosition()].equals(options.get(OptionKey.round.getOptionkey()))) {
                    complementCSVMapData(csvData, options, i, mapped);
                } else {
                    continue;
                }
            }

            return mapped;
        }

        throw new NoSuchEntryException();
    }

    public static void main(String[] args) {
        try {
            Map<String, String> options = new HashMap<String, String> ();
            options.put("company_name", "Facebook");
            options.put("round", "a");
            System.out.print(FundingRaised.where(options).size());
        } catch(IOException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        }
    }

    public static List<String[]> addCSVData(List<String[]> csvData, Map<String, String> options, OptionKey optionKey) {
        List<String[]> results = new ArrayList<String[]> ();
    
        for(int i = 0; i < csvData.size(); i++) {
            if(csvData.get(i)[optionKey.getArrayPosition()].equals(options.get(optionKey.getOptionkey()))) {
                results.add(csvData.get(i));
            }
        }
        
        return results;
    }

    public static void complementCSVMapData(List<String[]> csvData, Map<String, String> options, int i, Map<String, String> mapped) {
        mapped.put("permalink", csvData.get(i)[0]);
        mapped.put("company_name", csvData.get(i)[1]);
        mapped.put("number_employees", csvData.get(i)[2]);
        mapped.put("category", csvData.get(i)[3]);
        mapped.put("city", csvData.get(i)[4]);
        mapped.put("state", csvData.get(i)[5]);
        mapped.put("funded_date", csvData.get(i)[6]);
        mapped.put("raised_amount", csvData.get(i)[7]);
        mapped.put("raised_currency", csvData.get(i)[8]);
        mapped.put("round", csvData.get(i)[9]);
    }
}

class NoSuchEntryException extends Exception {}
