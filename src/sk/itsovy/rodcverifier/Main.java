package sk.itsovy.rodcverifier;

import java.io.*;

//import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args)
    {
        String file ="C:\\Users\\client\\Desktop\\rodcverifier\\data.dat";
        String output ="C:\\Users\\client\\Desktop\\rodcverifier\\testlistout.txt";

        String regex = "[0-9]{2}[0,1,5,6][0-9]{3}[\\/]?[0-9]{3,4}";
        String regex2 = "(0?[1-9]|[1-2][0-9]|3[0-1]).(0?[1-9]|1[0-2]).(19|20)[0-9]{2}";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Pattern pattern4date = Pattern.compile(regex2, Pattern.MULTILINE);
        Matcher matcher;
        String currentLine;
        String rc = "";
        String rctemp;
        String datetemp = "";
        char[] arrayd = new char[10];


        SimpleDateFormat dateformat3 = new SimpleDateFormat("dd-mm-yyyy");
        Date date1 = null;
        try {
            date1 = dateformat3.parse("15-06-2004");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Person person = new Person("Robert", "Ivan", date1, "991230/1234");
        Database db = new Database();
        //db.insertNewPerson(person);

        db.selectPerson(person);
        db.selectPersonByPin("841018/5454");
        db.selectCountWomen();
        List<Person> persons = db.selectAllMen();
        for (Person person1 : persons) {
            System.out.println(person1.getFname()+" "+person1.getLname()+" "+person1.getDob());
        }
        /*
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            FileWriter fw = new FileWriter(output);
            BufferedWriter out = new BufferedWriter(fw);

            while ((currentLine = reader.readLine()) != null)
            {
                currentLine = currentLine.trim();
                System.out.println(currentLine);
                matcher = pattern.matcher(currentLine);
                if (matcher.find())
                {
                    System.out.println("Match found1");
                    rc = matcher.group(0);
                    if((Long.parseLong(matcher.group(0).replace("/",""))%11) == 0)
                    {
                        System.out.println("Match found2");
                        matcher = pattern4date.matcher(currentLine);
                        if (matcher.find())
                        {
                            if(matcher.group(0).length() == 10)
                            {
                                datetemp = ""+matcher.group(0).charAt(8) + matcher.group(0).charAt(9) + matcher.group(0).charAt(3) + matcher.group(0).charAt(4) + matcher.group(0).charAt(0) + matcher.group(0).charAt(1);
                            }
                            System.out.println(datetemp);

                            rctemp = rc.substring(0, rc.indexOf("/"));

                            if(Integer.parseInt(rctemp) == Integer.parseInt(datetemp))
                            {
                                System.out.println("FULLMATCH");
                                out.write(currentLine);
                                out.newLine();

                                WriteToDatabase(currentLine);
                            }
                            else
                                {
                                    if(matcher.group(0).length() == 10)
                                    {
                                        datetemp = ""+matcher.group(0).charAt(8) + matcher.group(0).charAt(9) + '5' + matcher.group(0).charAt(4) + matcher.group(0).charAt(0) + matcher.group(0).charAt(1);
                                        if(Integer.parseInt(rctemp) == Integer.parseInt(datetemp))
                                        {
                                            System.out.println("FULLMATCH");
                                            out.write(currentLine);
                                            out.newLine();

                                            WriteToDatabase(currentLine);
                                        }
                                        else
                                            {
                                                datetemp = ""+matcher.group(0).charAt(8) + matcher.group(0).charAt(9) + '6' + matcher.group(0).charAt(4) + matcher.group(0).charAt(0) + matcher.group(0).charAt(1);
                                                if(Integer.parseInt(rctemp) == Integer.parseInt(datetemp))
                                                {
                                                    System.out.println("FULLMATCH");
                                                    out.write(currentLine);
                                                    out.newLine();

                                                    WriteToDatabase(currentLine);
                                                }
                                            }
                                    }
                                }
                        }
                    }
                }

            }
            reader.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    */
    }
    public static void WriteToDatabase(String text)
    {
        text = text.replace(".", "-");
        //System.out.println(text);
        String[] persondata = text.split(" ");


        SimpleDateFormat dateformat3 = new SimpleDateFormat("dd-mm-yyyy");
        Date date1 = null;
        try {
            date1 = dateformat3.parse(persondata[3]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Person person = new Person(persondata[0], persondata[1], date1, persondata[2]);
        Database db = new Database();
        db.insertNewPerson(person);

    }
}
