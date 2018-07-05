package com.shaunlu.springexample.microservice.generator;

import com.shaunlu.springexample.microservice.generator.generator.SourceGenerator;
import com.shaunlu.springexample.microservice.generator.model.AppConfiguration;
import com.shaunlu.springexample.microservice.generator.model.ApplicationParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Scanner;

@ShellComponent
public class Command {

    private ApplicationParam applicationParam;

    @Autowired
    SourceGenerator sGenerator;

    @ShellMethod(value = "run code generation program", key = "run")
    public void run() throws Exception{
        applicationParam = this.collectParams();
        if(null != applicationParam) {
            sGenerator.start(new AppConfiguration(applicationParam));
        }
    }

    private ApplicationParam collectParams() {

        ApplicationParam applicationParam = new ApplicationParam();
        Scanner scan = new Scanner(System.in);
        // application name
        System.out.print("What is your application name: ");
        applicationParam.setApplicationName(scan.nextLine());

        // root package name
        System.out.print("What is your default Java package name: ");
        applicationParam.setRootPackageName(scan.nextLine());

        // server port
        System.out.print("On which port would like your server to run: ");
        applicationParam.setPort(scan.nextLine());

        // confirm option
        System.out.printf("--------------------------------\n"
                        + "\tApplication name: %s\n"
                        + "\tDefault package name: %s\n"
                        + "\tServer port: %s\n", applicationParam.getApplicationName(), applicationParam.getRootPackageName(), applicationParam.getPort());
        System.out.printf("\tContinue to create application ? (y/n):");
        String result = scan.nextLine();
        if ("y".equalsIgnoreCase(result)) {
            return applicationParam;
        } else {
            return null;
        }
    }

}
