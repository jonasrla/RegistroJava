/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch01;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.CredentialNotFoundException;
import javax.security.auth.login.AccountLockedException;
/**
 *
 * @author jonasrla
 */
public class AccountManager {
    private File accountLog;
    public AccountManager() throws IOException{
        accountLog = new File("/Users/jonasrla/NetBeansProjects/DemoJSP/build/misc/accountLog.txt");
        if (!accountLog.exists()){
            accountLog.createNewFile();
            FileWriter fw = new FileWriter(accountLog.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("login,password\n");
            bw.close();
        }
    }
    public void RegisterAccount(String login, String password) throws IOException, AccountLockedException{
        FileWriter fw = new FileWriter(accountLog.getAbsoluteFile(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        if (FindAccount(login) == null){
            bw.write(login+","+password+"\n");
            bw.close();
        }
        else{
            bw.close();
            throw new AccountLockedException();
        }
    }
    private String FindAccount(String login){
        BufferedReader br = null;
        try{
            String sCurrentLine;
            String[] splitedLine;
            br = new BufferedReader(new FileReader(accountLog.getAbsoluteFile()));
            while ((sCurrentLine = br.readLine()) != null) {
                splitedLine = sCurrentLine.split(",");
                if (splitedLine[0].equals(login)){
                    return splitedLine[1];
                }
            }
            return null;
        }
        catch(IOException e){
            e.printStackTrace();
        } finally {
            try{
                if (br != null)br.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }
    public void checkAccount(String login, String candidate) throws IOException, AccountNotFoundException, CredentialNotFoundException{
        String password = FindAccount(login);
        if (password != null){
            checkPassword(password,candidate);
        }
        else{
            throw new AccountNotFoundException();
        }
    }
    private void checkPassword(String password, String candidate) throws CredentialNotFoundException{
        if (!password.equals(candidate)){
            throw new CredentialNotFoundException();
        }
    }
}
