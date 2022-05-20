package com.mycompany.singletonlogin;

import java.util.Scanner;

public class SingletonLogin {

    public static void main(String[] args) {
        SingletonLogin login = SingletonLogin.getInstance();

        // Primeiro login do usuário, o sistema irá aceitar e entrar no tratamento
        // dos dados
        login.userLogin();

        // O usuário irá tentar se logar outra vez, mas a instância única não permitirá!
        login.userLogin();

    }

    private static SingletonLogin uniqueInstance;
    private String user;
    private String password;
    private boolean logged = false;

    private SingletonLogin() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public static synchronized SingletonLogin getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SingletonLogin();
        }

        return uniqueInstance;
    }

    public void userLogin() {
        if (logged != true) {
            String captcha = getRandomString(6);
            String randomCode;
            Scanner scanLogin = new Scanner(System.in);

            System.out.println("Digite seu nome de usuario:");
            setUser(scanLogin.nextLine());

            System.out.println("\nDigite sua senha:");
            setPassword(scanLogin.nextLine());

            System.out.println("""
                               \nVocê é um robô? 
                               Digite o seguinte código: """ + captcha);
            randomCode = scanLogin.nextLine();

            if (captcha.equals(randomCode)) {
                System.out.println("\nVocê se logou com sucesso!");
                setLogged(true);
            } else {
                System.out.println("\nVocê é um robô, me prove o contrário!");
                userLogin();
            }
        } else {
            System.out.println("\nVocê já está logado!");
            getInstance();
        }

    }

    String getRandomString(int i) {
        String theAlphaNumericS;
        StringBuilder builder;

        theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        //create the StringBuffer
        builder = new StringBuilder(i);

        for (int m = 0; m < i; m++) {

            // generate numeric
            int myindex
                    = (int) (theAlphaNumericS.length()
                    * Math.random());

            // add the characters
            builder.append(theAlphaNumericS
                    .charAt(myindex));
        }

        return builder.toString();
    }

}
