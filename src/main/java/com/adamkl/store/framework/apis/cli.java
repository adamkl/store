package com.adamkl.store.framework.apis;

import com.adamkl.store.application.StoreImpl;
import com.adamkl.store.framework.repositories.MockCartRepository;
import com.adamkl.store.framework.repositories.MockInventoryRepository;
import org.apache.commons.cli.*;

public class cli {
        public static void main(String[] args) {
                // create the options
                Options options = new Options();
                options.addOption(Option.builder().argName("total").longOpt("total").desc("Get cart total by shopperId").build());

                // create the parser
                CommandLineParser parser = new DefaultParser();
                try {
                        // parse the command line arguments
                        CommandLine line = parser.parse(options, args);
                        var store = new StoreImpl(new MockInventoryRepository(), new MockCartRepository());
                        if(line.hasOption("total")) {
                                var total = store.getCartTotal("1");
                                System.out.println(total);
                        }
                } catch (ParseException exp) {
                        // oops, something went wrong
                        System.err.println("Parsing failed.  Reason: " + exp.getMessage());
                }


        }
}
